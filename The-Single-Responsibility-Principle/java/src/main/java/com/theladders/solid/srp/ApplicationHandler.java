package com.theladders.solid.srp;

import com.theladders.solid.srp.http.HttpRequest;
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
  
  public ApplicationHandler(ResumeManager resumeManager, JobApplicationSystem jobApplicationSystem, MyResumeManager myResumeManager)
  {
    this.resumeManager = resumeManager;
    this.jobApplicationSystem = jobApplicationSystem;
    this.myResumeManager = myResumeManager;
    
  }


  public void apply(HttpRequest request,
                     Jobseeker jobseeker,
                     Job job,
                     String fileName)
  {
    Resume resume = saveNewOrRetrieveExistingResume(fileName,jobseeker, request);
    UnprocessedApplication application = new UnprocessedApplication(jobseeker, job, resume);
    JobApplicationResult applicationResult = jobApplicationSystem.apply(application);

    if (applicationResult.failure())
    {
      throw new ApplicationFailureException(applicationResult.toString());
    }
  }
  


  private Resume saveNewOrRetrieveExistingResume(String newResumeFileName,
                                                 Jobseeker jobseeker,
                                                 HttpRequest request)
  {
    Resume resume;

    if (resumeExists(request))
    {
      resume = resumeManager.saveResume(jobseeker, newResumeFileName);

      if (makeResumeActive( resume, request))
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
  
  private boolean resumeExists(HttpRequest request)
  {
    return !"existing".equals(request.getParameter("whichResume"));
  }
  
  private boolean makeResumeActive(Resume resume, HttpRequest request)
  {
    return resume != null && "yes".equals(request.getParameter("makeResumeActive"));
  }
  
}
