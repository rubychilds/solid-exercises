package com.theladders.solid.srp;

import com.theladders.solid.srp.applicationResult.ApplicationResult;
import com.theladders.solid.srp.applicationResult.ProvideErrorMessage;
import com.theladders.solid.srp.applicationResult.ResultFactory;
import com.theladders.solid.srp.job.Job;
import com.theladders.solid.srp.job.application.JobApplicationResult;
import com.theladders.solid.srp.job.application.JobApplicationSystem;
import com.theladders.solid.srp.job.application.UnprocessedApplication;
import com.theladders.solid.srp.jobseeker.Jobseeker;
import com.theladders.solid.srp.jobseeker.JobseekerProfile;
import com.theladders.solid.srp.jobseeker.JobseekerProfileManager;
import com.theladders.solid.srp.resume.MyResumeManager;
import com.theladders.solid.srp.resume.Resume;
import com.theladders.solid.srp.resume.ResumeManager;
import com.theladders.solid.srp.utils.ErrorFields;

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

  public ApplicationResult execute(Job job,
                                   Jobseeker jobseeker,
                                   ResumeData resumeData)
  {
    if (job == null)
    {
      ApplicationResult result = ResultFactory.getInvalidJobView();
      return result;
    }

    Resume resume = saveNewOrRetrieveExistingResume(resumeData, jobseeker);
    UnprocessedApplication application = new UnprocessedApplication(jobseeker, job, resume);

    if (jobApplicationFailed(application))
    {
      ProvideErrorMessage applicationResult = ResultFactory.getErrorView();
      applicationResult.addError(ErrorFields.UNABLE_TO_PROCESS_APP);
      return applicationResult;
    }

    if (jobseekerNeedsProfileCompletion(jobseeker))
    {
      ApplicationResult applicationResult = ResultFactory.getResumeCompletionView();
      setJobVariblesInResult(applicationResult, job);
      return applicationResult;
    }

    ApplicationResult applicationResult = ResultFactory.getSuccessView();
    setJobVariblesInResult(applicationResult, job);
    return applicationResult;
  }

  private void setJobVariblesInResult(ApplicationResult applicationResult,
                                      Job job)
  {
    int jobId = job.getJobId();
    String jobTitle = job.getTitle();
    applicationResult.setJobID(jobId);
    applicationResult.setJobTitle(jobTitle);
  }

  private Resume saveNewOrRetrieveExistingResume(ResumeData resumeData,
                                                 Jobseeker jobseeker)
  {
    Resume resume;

    String resumeFileName = resumeData.fileName();

    if (resumeFileName == null)
      return null;

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
