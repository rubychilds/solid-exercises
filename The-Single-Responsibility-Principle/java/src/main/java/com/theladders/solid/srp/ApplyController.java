package com.theladders.solid.srp;

import java.util.HashMap;
import java.util.Map;

import com.theladders.solid.srp.http.HttpRequest;
import com.theladders.solid.srp.http.HttpResponse;
import com.theladders.solid.srp.job.Job;
import com.theladders.solid.srp.job.JobSearchService;
import com.theladders.solid.srp.job.application.JobApplicationSystem;
import com.theladders.solid.srp.jobseeker.Jobseeker;
import com.theladders.solid.srp.jobseeker.JobseekerProfile;
import com.theladders.solid.srp.jobseeker.JobseekerProfileManager;
import com.theladders.solid.srp.jobseeker.ProfileStatus;
import com.theladders.solid.srp.resume.MyResumeManager;
import com.theladders.solid.srp.resume.ResumeManager;
import com.theladders.solid.srp.view.View;

public class ApplyController
{
  private final JobseekerProfileManager jobseekerProfileManager;
  private final JobSearchService        jobSearchService;
  private final JobApplicationSystem    jobApplicationSystem;
  private final ResumeManager           resumeManager;
  private final MyResumeManager         myResumeManager;
  
  private final Map<String,Object> model = new HashMap<String,Object>();
  
  private static String JOB_ID = "jobId";
  private static String JOB_TITLE = "jobTitle";

  public ApplyController(JobseekerProfileManager jobseekerProfileManager,
                         JobSearchService jobSearchService,
                         JobApplicationSystem jobApplicationSystem,
                         ResumeManager resumeManager,
                         MyResumeManager myResumeManager)
  {
    this.jobseekerProfileManager = jobseekerProfileManager;
    this.jobSearchService = jobSearchService;
    this.jobApplicationSystem = jobApplicationSystem;
    this.resumeManager = resumeManager;
    this.myResumeManager = myResumeManager;
  }

  public HttpResponse handle(HttpRequest request,
                             HttpResponse response,
                             String origFileName)
  {
    RequestManager requestManager = new RequestManager(request);
    
    Jobseeker jobseeker = requestManager.getJobSeeker();

    Job job = requestManager.getJob(jobSearchService);
    
    View view = new View();
    
    if (job == null)
    {
      int jobId = requestManager.getJobId();
      model.put(JOB_ID, jobId);
      
      return getResponse(view.provideInvalidJobView(model), response);
    }

    try
    {
      ApplicationHandler applicationHandler = new ApplicationHandler(resumeManager,
                                                                     jobApplicationSystem, 
                                                                     myResumeManager, requestManager
                                                                     );
      
      applicationHandler.apply(jobseeker, job, origFileName);
    }
    catch (Exception e)
    {
      view.addError("We could not process your application.");
      return getResponse(view.provideErrorView(model), response);
    }

    model.put(JOB_ID, job.getJobId());
    model.put(JOB_TITLE, job.getTitle());
    
    if (needsToCompleteProfile(jobseeker)) 
    {
      return getResponse(view.provideResumeCompletionView(model), response);
    }

    return getResponse(view.provideSuccessView(model), response);
  }
 
  private HttpResponse getResponse(Result result, HttpResponse response){
    response.setResult(result);
    return response;
  }
  
  private boolean needsToCompleteProfile(Jobseeker jobseeker)
  {
    JobseekerProfile profile = jobseekerProfileManager.getJobSeekerProfile(jobseeker);
    return  !jobseeker.isPremium() && incompleteJobSeekerProfile(profile);
    
  }
  
  private boolean incompleteJobSeekerProfile(JobseekerProfile profile)
  {
    return (profile.getStatus().equals(ProfileStatus.INCOMPLETE) ||
        profile.getStatus().equals(ProfileStatus.NO_PROFILE) ||
        profile.getStatus().equals(ProfileStatus.REMOVED));
  }
  
  
  
 
}
