package com.theladders.solid.srp;

import com.theladders.solid.srp.jobseeker.Jobseeker;
import com.theladders.solid.srp.resume.MyResumeManager;
import com.theladders.solid.srp.resume.Resume;
import com.theladders.solid.srp.resume.ResumeManager;

public class ResumeHandler
{

  private String EXISTING = "existing";
  private String ACTIVATE_RESUME = "yes";
  
  private static boolean existingResume;
  private static boolean activateResume;
  private ResumeManager resumeManager;
  private MyResumeManager myResumeManager;

  public ResumeHandler(boolean existingResume,
                       boolean activateResume, 
                       ResumeManager resumeManager,
                       MyResumeManager myResumeManager) 
  {
    this.existingResume = existingResume;
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
    return EXISTING.equals(existingResume);
  }

  private boolean makeResumeActive()
  {
    return ACTIVATE_RESUME.equals(activateResume);
  }
}
