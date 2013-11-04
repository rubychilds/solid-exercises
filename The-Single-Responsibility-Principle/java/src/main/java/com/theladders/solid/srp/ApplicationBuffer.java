package com.theladders.solid.srp;

import com.theladders.solid.srp.applicationResult.ApplicationResult;
import com.theladders.solid.srp.job.Job;
import com.theladders.solid.srp.job.JobSearchService;
import com.theladders.solid.srp.job.application.JobApplicationSystem;
import com.theladders.solid.srp.jobseeker.Jobseeker;
import com.theladders.solid.srp.jobseeker.JobseekerProfileManager;
import com.theladders.solid.srp.resume.MyResumeManager;
import com.theladders.solid.srp.resume.ResumeManager;

public class ApplicationBuffer
{
  private JobSearchService   jobSearchService;
  private ApplicationProcess applicationProcessor;

  public ApplicationBuffer(JobseekerProfileManager jobseekerProfileManager,
                           JobSearchService jobSearchService,
                           JobApplicationSystem jobApplicationSystem,
                           ResumeManager resumeManager,
                           MyResumeManager myResumeManager)

  {
    this.applicationProcessor = new ApplicationProcess(jobseekerProfileManager,
                                                       jobApplicationSystem,
                                                       resumeManager,
                                                       myResumeManager);
    this.jobSearchService = jobSearchService;

  }

  public ApplicationResult execute(int jobId,
                                   String origFileName,
                                   Jobseeker jobseeker,
                                   String activateResume,
                                   String whichResume)
  {

    ResumeData resumeData = createResumeData(activateResume, whichResume, origFileName);

    Job job = jobSearchService.getJob(jobId);

    ApplicationResult finalView = applicationProcessor.execute(job, jobseeker, resumeData);

    if (job == null)
      finalView.setJobID(jobId);

    return finalView;
  }

  private ResumeData createResumeData(String activateResume,
                                      String whichResume,
                                      String origFileName)
  {
    return new ResumeData(origFileName, activateResume, whichResume);
  }

}
