package com.theladders.solid.srp;

import java.util.HashMap;
import java.util.Map;

import Utils.ErrorFields;
import Utils.ModelFieldNames;

import com.theladders.solid.srp.job.Job;
import com.theladders.solid.srp.job.JobSearchService;
import com.theladders.solid.srp.job.application.JobApplicationSystem;
import com.theladders.solid.srp.jobseeker.Jobseeker;
import com.theladders.solid.srp.jobseeker.JobseekerProfileManager;
import com.theladders.solid.srp.resume.MyResumeManager;
import com.theladders.solid.srp.resume.Resume;
import com.theladders.solid.srp.resume.ResumeManager;
import com.theladders.solid.srp.view.View;

public class ApplicationProcessor
{
  private final JobseekerProfileManager jobseekerProfileManager;
  private final JobSearchService        jobSearchService;
  private final JobApplicationSystem    jobApplicationSystem;
  private final ResumeManager           resumeManager;
  private final MyResumeManager         myResumeManager;
  
  private final Map<String,Object> model = new HashMap<String,Object>();
  
  public ApplicationProcessor(JobseekerProfileManager jobseekerProfileManager,
                              JobSearchService jobSearchService,
                              JobApplicationSystem jobApplicationSystem,
                              ResumeManager resumeManager,
                              MyResumeManager myResumeManager)
  {
    this.jobseekerProfileManager = jobseekerProfileManager;
    this.jobSearchService = jobSearchService;
    this.jobApplicationSystem = jobApplicationSystem;
    this.resumeManager = resumeManager;
    this.myResumeManager = myResumeManager;
  }
  
  
  public Result execute(SessionData currentSessionData,
                         String origFileName)
  {

    View view = new View();
    int jobId = currentSessionData.getJobId();

    Job job = jobSearchService.getJob(jobId);
    Jobseeker jobseeker = currentSessionData.getJobseeker();
    
    try
    {
      apply(currentSessionData, origFileName, jobseeker, job);
    }
    catch (Exception e)
    {
      view.addError(ErrorFields.UNABLE_TO_PROCESS_APP);
      return view.provideErrorView(model);
    }

    model.put(ModelFieldNames.JOB_ID, job.getJobId());
    model.put(ModelFieldNames.JOB_TITLE, job.getTitle());

    if (JobseekerStatus.needsToCompleteProfile(jobseeker, jobseekerProfileManager)) 
    {
      return view.provideResumeCompletionView(model);
    }
      return view.provideSuccessView(model);
  }
  
   
  /*********************************************************************/  
  private void apply(SessionData currentSessionData, String origFileName, Jobseeker jobseeker, Job job)
  {
    ResumeHandler resumeHandler = new ResumeHandler(currentSessionData.whichResume(), currentSessionData.activateResume(), resumeManager, myResumeManager);
    Resume resume = resumeHandler.saveNewOrRetrieveExistingResume(origFileName, jobseeker);
    
    ApplicationHandler applicationHandler = new ApplicationHandler(jobApplicationSystem);
    applicationHandler.apply(jobseeker, job, resume);
  }
}
