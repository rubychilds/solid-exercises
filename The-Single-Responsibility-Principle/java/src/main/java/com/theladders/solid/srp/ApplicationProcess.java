package com.theladders.solid.srp;

import Utils.ErrorFields;

import com.theladders.solid.srp.job.Job;
import com.theladders.solid.srp.job.application.JobApplicationSystem;
import com.theladders.solid.srp.jobseeker.Jobseeker;
import com.theladders.solid.srp.jobseeker.JobseekerProfile;
import com.theladders.solid.srp.jobseeker.JobseekerProfileManager;
import com.theladders.solid.srp.resume.MyResumeManager;
import com.theladders.solid.srp.resume.Resume;
import com.theladders.solid.srp.resume.ResumeManager;
import com.theladders.solid.srp.view.View;

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
  
  public Result execute(Job job, Jobseeker jobseeker, SessionData resumeData) {
    
    if (job == null)
    {
      Result result = new View().provideInvalidJobView(job.getJobId());
      return getResponse(result, response);
    }

    
    ResumeHandler resumeHandler = new ResumeHandler(resumeData.useExistingResume(), 
                                                    resumeData.makeResumeActive(), 
                                                    resumeManager, 
                                                    myResumeManager);

    Resume resume = resumeHandler.saveNewOrRetrieveExistingResume(resumeData.fileName(), jobseeker);
    
    if(resume == null)
    {
    	Result result = new View().provideInvalidJobView(job.getJobId());
    	return getResponse(result, response);
    }
    
    try
    {      ApplicationHandler applicationHandler = new ApplicationHandler(jobApplicationSystem);
      applicationHandler.apply(jobseeker, job, resume);
    }
    catch (Exception e)
    {
      return new View().provideErrorView(ErrorFields.UNABLE_TO_PROCESS_APP);
    }
    
    return finalView(jobseeker, job);
  }
  
  private Result finalView(Jobseeker jobseeker, Job job)
  {
    JobseekerProfile profile = jobseekerProfileManager.getJobSeekerProfile(jobseeker);
    if (!jobseeker.isPremium() && profile.isIncompleteProfile()) 
    {
      return new View().provideResumeCompletionView(job.getJobId(), job.getTitle());
    }
    return new View().provideSuccessView(job.getJobId(), job.getTitle());
  }


}
