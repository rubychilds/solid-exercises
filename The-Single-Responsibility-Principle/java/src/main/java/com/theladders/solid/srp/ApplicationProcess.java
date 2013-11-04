package com.theladders.solid.srp;

import com.theladders.solid.srp.applicationResult.ApplicationResult;
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
      return ResultFactory.getInvalidJobView();

    int jobId = job.getJobId();
    String jobTitle = job.getTitle();
    Resume resume = saveNewOrRetrieveExistingResume(resumeData, jobseeker);
    UnprocessedApplication application = new UnprocessedApplication(jobseeker, job, resume);

    if (jobApplicationFailed(application))
    {
      ApplicationResult view = ResultFactory.getErrorView();
      view.addError(ErrorFields.UNABLE_TO_PROCESS_APP);
      return view;
    }

    if (jobseekerNeedsProfileCompletion(jobseeker)){
      ApplicationResult view = ResultFactory.getResumeCompletionView();
      view.setJobID(jobId);
      view.setJobTitle(jobTitle);
      return view;    
    }
    
    ApplicationResult view = ResultFactory.getSuccessView();
    view.setJobID(jobId);
    view.setJobTitle(jobTitle);
    return view;
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
