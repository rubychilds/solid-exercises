package com.theladders.solid.srp;

import Utils.ErrorFields;

import com.theladders.solid.srp.job.Job;
import com.theladders.solid.srp.job.application.ApplicationFailureException;
import com.theladders.solid.srp.job.application.JobApplicationResult;
import com.theladders.solid.srp.job.application.JobApplicationSystem;
import com.theladders.solid.srp.job.application.UnprocessedApplication;
import com.theladders.solid.srp.jobseeker.Jobseeker;
import com.theladders.solid.srp.jobseeker.JobseekerProfile;
import com.theladders.solid.srp.jobseeker.JobseekerProfileManager;
import com.theladders.solid.srp.resume.MyResumeManager;
import com.theladders.solid.srp.resume.Resume;
import com.theladders.solid.srp.resume.ResumeManager;
import com.theladders.solid.srp.view.View;
import com.theladders.solid.srp.view.ViewCollection;

public class ApplicationProcess
{
  private final JobseekerProfileManager jobseekerProfileManager;
  private final JobApplicationSystem    jobApplicationSystem;
  private final ResumeManager           resumeManager;
  private final MyResumeManager         myResumeManager;

  public ApplicationProcess(JobseekerProfileManager jobseekerProfileManager,
                            JobApplicationSystem jobApplicationSystem,
                            ResumeManager resumeManager,
                            MyResumeManager myResumeManager)
  {
    this.jobseekerProfileManager = jobseekerProfileManager;
    this.jobApplicationSystem = jobApplicationSystem;
    this.resumeManager = resumeManager;
    this.myResumeManager = myResumeManager;
  }

  public View execute(int jobId,
                      Job job,
                      Jobseeker jobseeker,
                      SessionData resumeData,
                      ViewCollection viewCollection)
  {
    if (job == null)
    {
      // return new ApplicationResponse(ApplicationResponseType.INVALID_JOB, jobId);
      return viewCollection.getInvalidJobView();
    }

    Resume resume = saveNewOrRetrieveExistingResume(resumeData, jobseeker);

    UnprocessedApplication application = new UnprocessedApplication(jobseeker, job, resume);
    // unprocessed application isVALID only if jobseekeer != null && resume != null && job!= null

    if (jobApplicationFailed(application))
    {
      // must set jobTitle
      return viewCollection.getErrorView();

    }
    if (jobseekerNeedsProfileCompletion(jobseeker))
    {
      // must set jobTitle
      return viewCollection.getResumeCompletionView();
    }
    // must set JobTitle
    // new ApplicationResponse(ApplicationResponseType.SUCESSFUL, job.getJobId(), job.getTitle());
    return viewCollection.getSuccessView();
  }

  private Resume saveNewOrRetrieveExistingResume(SessionData resumeData,
                                                 Jobseeker jobseeker)
  {
    Resume resume;

    String resumeFileName = resumeData.fileName();
    if (resumeFileName == null)
    {
      return null;
    }

    if (!resumeData.useExistingResume())
    {
      resume = resumeManager.saveResume(jobseeker, resumeFileName);

      if (resume != null && resumeData.makeResumeActive())
        myResumeManager.saveAsActive(jobseeker, resume);
    }
    else
      resume = myResumeManager.getActiveResume(jobseeker.getId());

    return resume;
  }

  private boolean jobseekerNeedsProfileCompletion(Jobseeker jobseeker)
  {
    JobseekerProfile profile = jobseekerProfileManager.getJobSeekerProfile(jobseeker);
    return !jobseeker.isPremium() && profile.isIncompleteProfile();
  }

  private boolean jobApplicationFailed(UnprocessedApplication application)
  {
    JobApplicationResult applicationResult = jobApplicationSystem.apply(application);
    return applicationResult.failure();
  }
}
