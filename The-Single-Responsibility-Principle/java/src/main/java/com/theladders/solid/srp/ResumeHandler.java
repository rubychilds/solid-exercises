package com.theladders.solid.srp;

import Utils.HttpRequestParameters;

import com.theladders.solid.srp.jobseeker.Jobseeker;
import com.theladders.solid.srp.resume.MyResumeManager;
import com.theladders.solid.srp.resume.Resume;
import com.theladders.solid.srp.resume.ResumeManager;

public class ResumeHandler
{
  private String whichResume;
  private String activateResume;
  private ResumeManager resumeManager;
  private MyResumeManager myResumeManager;

  public ResumeHandler(String whichResume,
                       String activateResume, 
                       ResumeManager resumeManager,
                       MyResumeManager myResumeManager) 
  {
    this.whichResume = whichResume;
    this.activateResume = activateResume;
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

  private boolean useExistingResume()
  {
    return HttpRequestParameters.EXISTING.equals(whichResume);
  }

  private boolean makeResumeActive()
  {
    return HttpRequestParameters.ACTIVATE_RESUME.equals(activateResume);
  }
}
