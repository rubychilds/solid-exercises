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
  private final SessionData             currentSessionData;
  
  private final Map<String,Object> model = new HashMap<String,Object>();
  
  public ApplicationProcessor(SessionData currentSessionData, 
                              JobseekerProfileManager jobseekerProfileManager,
                              JobSearchService jobSearchService,
                              JobApplicationSystem jobApplicationSystem,
                              ResumeManager resumeManager,
                              MyResumeManager myResumeManager)
  {
    this.currentSessionData = currentSessionData;
    this.jobseekerProfileManager = jobseekerProfileManager;
    this.jobSearchService = jobSearchService;
    this.jobApplicationSystem = jobApplicationSystem;
    this.resumeManager = resumeManager;
    this.myResumeManager = myResumeManager;
  }
  
  
  public Result execute(JobSearchService jobSearchService,
                         String origFileName)
  {

    View view = new View();
    int jobId = currentSessionData.getJobId();
    
    if (!jobExists(jobSearchService, jobId))
    {
      model.put(ModelFieldNames.JOB_ID, jobId);

      return view.provideInvalidJobView(model);
    }
    
    Job job = getJob(jobSearchService, jobId);
    Jobseeker jobseeker = currentSessionData.getJobseeker();
    
    try
    {
      apply(origFileName,jobseeker,job);
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
  private Job getJob(JobSearchService jobSearchService, int jobId)
  {
    return jobSearchService.getJob(jobId);
  }
  
  private boolean jobExists(JobSearchService jobSearchService, int jobId)
  {
    Job job = getJob(jobSearchService, jobId);
    return job != null;
  }
  
  
  private void apply(String origFileName,Jobseeker jobseeker, Job job)
  {
    ResumeHandler resumeHandler = new ResumeHandler(currentSessionData.whichResume(), currentSessionData.activateResume(), resumeManager, myResumeManager);
    Resume resume = resumeHandler.saveNewOrRetrieveExistingResume(origFileName,jobseeker);
    
    ApplicationHandler applicationHandler = new ApplicationHandler(jobApplicationSystem);
    applicationHandler.apply(jobseeker, job, resume);
  }
}
