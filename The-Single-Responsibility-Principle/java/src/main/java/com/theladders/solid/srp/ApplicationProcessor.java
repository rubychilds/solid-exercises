package com.theladders.solid.srp;

import java.util.HashMap;
import java.util.Map;

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
  private RequestManager requestManager;
  private final JobseekerProfileManager jobseekerProfileManager;
  private final JobSearchService        jobSearchService;
  private final JobApplicationSystem    jobApplicationSystem;
  private final ResumeManager           resumeManager;
  private final MyResumeManager         myResumeManager;
  
  private static String JOB_ID = "jobId";
  private static String JOB_TITLE = "jobTitle";
  
  private final Map<String,Object> model = new HashMap<String,Object>();
  
  public ApplicationProcessor(RequestManager requestManager, 
                              JobseekerProfileManager jobseekerProfileManager,
                              JobSearchService jobSearchService,
                              JobApplicationSystem jobApplicationSystem,
                              ResumeManager resumeManager,
                              MyResumeManager myResumeManager)
  {
    this.requestManager = requestManager;
    this.jobseekerProfileManager = jobseekerProfileManager;
    this.jobSearchService = jobSearchService;
    this.jobApplicationSystem = jobApplicationSystem;
    this.resumeManager = resumeManager;
    this.myResumeManager = myResumeManager;
  }
  
  
  public Result applyXXX(JobSearchService jobSearchService, 
                         int jobId, 
                         Jobseeker jobseeker, 
                         String origFileName)
  {

    View view = new View();

    if (!jobExists(jobSearchService, jobId))
    {
      model.put(JOB_ID, jobId);

      return view.provideInvalidJobView(model);
    }
    
    Job job = getJob(jobSearchService, jobId);
    
    try
    {
      apply(origFileName,jobseeker,job);
    }
    catch (Exception e)
    {
      view.addError("We could not process your application.");
      return view.provideErrorView(model);
    }

    model.put(JOB_ID, job.getJobId());
    model.put(JOB_TITLE, job.getTitle());

    if (JobseekerStatus.needsToCompleteProfile(jobseeker, jobseekerProfileManager)) 
    {
      return view.provideResumeCompletionView(model);
    }      
      return view.provideSuccessView(model);
  }
  
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
    ResumeHandler resumeHandler = new ResumeHandler(requestManager, resumeManager, myResumeManager);
    Resume resume = resumeHandler.saveNewOrRetrieveExistingResume(origFileName,jobseeker);
    
    ApplicationHandler applicationHandler = new ApplicationHandler(jobApplicationSystem);
    applicationHandler.apply(jobseeker, job, resume);
  }
}
