package com.theladders.solid.srp;

import com.theladders.solid.srp.job.Job;
import com.theladders.solid.srp.job.application.ApplicationFailureException;
import com.theladders.solid.srp.job.application.JobApplicationResult;
import com.theladders.solid.srp.job.application.JobApplicationSystem;
import com.theladders.solid.srp.job.application.UnprocessedApplication;
import com.theladders.solid.srp.jobseeker.Jobseeker;
import com.theladders.solid.srp.resume.MyResumeManager;
import com.theladders.solid.srp.resume.Resume;
import com.theladders.solid.srp.resume.ResumeManager;

public class ApplicationHandler
{
  
  private ResumeManager resumeManager;
  private JobApplicationSystem jobApplicationSystem;
  private MyResumeManager myResumeManager;
  private RequestManager requestManager;
  
  public ApplicationHandler(ResumeManager resumeManager, 
                            JobApplicationSystem jobApplicationSystem, 
                            MyResumeManager myResumeManager, 
                            RequestManager requestManager)
  {
    this.resumeManager = resumeManager;
    this.jobApplicationSystem = jobApplicationSystem;
    this.myResumeManager = myResumeManager;
    this.requestManager = requestManager;
  }


  public void apply(Jobseeker jobseeker,
                     Job job,
                     String fileName)
  {
    
    ResumeHandler resumeHandler = new ResumeHandler(requestManager, resumeManager, myResumeManager);
    Resume resume = resumeHandler.saveNewOrRetrieveExistingResume(fileName,jobseeker);
    UnprocessedApplication application = new UnprocessedApplication(jobseeker, job, resume);
    JobApplicationResult applicationResult = jobApplicationSystem.apply(application);

    if (applicationResult.failure())
    {
      throw new ApplicationFailureException(applicationResult.toString());
    }
  }
    
}
  


