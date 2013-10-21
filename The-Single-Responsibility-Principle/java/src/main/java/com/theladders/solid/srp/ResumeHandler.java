package com.theladders.solid.srp;

import com.theladders.solid.srp.jobseeker.Jobseeker;
import com.theladders.solid.srp.resume.MyResumeManager;
import com.theladders.solid.srp.resume.Resume;
import com.theladders.solid.srp.resume.ResumeManager;

public class ResumeHandler
{
  private RequestManager requestManager;
  private ResumeManager resumeManager;
  private MyResumeManager myResumeManager;
  
  private String EXISTING = "existing";
  private String ACTIVATE_RESUME = "yes";
  
  public ResumeHandler(RequestManager requestManager, ResumeManager resumeManager, MyResumeManager myResumeManager) 
  {
    this.requestManager = requestManager;
    this.resumeManager = resumeManager;
    this.myResumeManager = myResumeManager;
  }
  
  public Resume saveNewOrRetrieveExistingResume(String newResumeFileName,
                                                 Jobseeker jobseeker)
  {
    Resume resume;

    if (!useExistingResume())
    {
      resume = resumeManager.saveResume(jobseeker, newResumeFileName);

      if (resume != null && makeResumeActive())
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
  
  public boolean useExistingResume()
  {
    return RequestManager.EXISTING.equals(requestManager.whichResume());
  }
  
  public boolean makeResumeActive()
  {
    return RequestManager.ACTIVATE_RESUME.equals(requestManager.makeResumeActive());
  }

  
}
