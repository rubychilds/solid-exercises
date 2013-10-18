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
    Resume resume = saveNewOrRetrieveExistingResume(fileName,jobseeker);
    UnprocessedApplication application = new UnprocessedApplication(jobseeker, job, resume);
    JobApplicationResult applicationResult = jobApplicationSystem.apply(application);

    if (applicationResult.failure())
    {
      throw new ApplicationFailureException(applicationResult.toString());
    }
  }
  

  private Resume saveNewOrRetrieveExistingResume(String newResumeFileName,
                                                 Jobseeker jobseeker)
  {
    Resume resume;

    if (!"existing".equals(requestManager.whichResume()))
    {
      resume = resumeManager.saveResume(jobseeker, newResumeFileName);

      if (resume != null && "yes".equals(requestManager.makeResumeActive()))
      {
        myResumeManager.saveAsActive(jobseeker, resume);
      }
    }
    else
    {
      resume = myResumeManager.getActiveResume(jobseeker.getId());
    }

    return resume;
  }
  
}
  


