package com.theladders.solid.srp;

import java.util.HashMap;
import java.util.Map;

import Utils.ErrorFields;
import Utils.ModelFieldNames;

import com.theladders.solid.srp.job.Job;
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
  private final JobApplicationSystem    jobApplicationSystem;
  private final ResumeManager           resumeManager;
  private final MyResumeManager         myResumeManager;
  
  private final Map<String,Object> model = new HashMap<String,Object>();
  
  public ApplicationProcessor(JobseekerProfileManager jobseekerProfileManager,
                              JobApplicationSystem jobApplicationSystem,
                              ResumeManager resumeManager,
                              MyResumeManager myResumeManager)
  {
    this.jobseekerProfileManager = jobseekerProfileManager;
    this.jobApplicationSystem = jobApplicationSystem;
    this.resumeManager = resumeManager;
    this.myResumeManager = myResumeManager;
  }
  
  
  public Result execute(SessionData currentSessionData,
                         String origFileName, Job job)
  {

    View view = new View();

    Jobseeker jobseeker = currentSessionData.getJobseeker();
    
    try
    {
      apply(currentSessionData, origFileName, jobseeker, job);
    }
    catch (Exception e)
    {
      return view.provideErrorView(ErrorFields.UNABLE_TO_PROCESS_APP);
    }

    if (JobseekerStatus.needsToCompleteProfile(jobseeker, jobseekerProfileManager)) 
    {
      return view.provideResumeCompletionView(job.getJobId(),job.getTitle());
    }
      return view.provideSuccessView(job.getJobId(),job.getTitle());
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
