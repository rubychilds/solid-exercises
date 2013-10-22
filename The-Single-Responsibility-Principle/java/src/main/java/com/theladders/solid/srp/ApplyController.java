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
    int jobId = requestManager.getJobId();
    Jobseeker jobseeker = requestManager.getJobSeeker();
    
    ApplicationProcessor applicationProcessor = new ApplicationProcessor(requestManager, 
                                                                         jobseekerProfileManager,
                                                                         jobSearchService,
                                                                         jobApplicationSystem,
                                                                         resumeManager,
                                                                         myResumeManager);
    
    Result result = applicationProcessor.applyXXX(jobSearchService, jobId, jobseeker, origFileName);
    
    return getResponse(result, response);
  }
 
  private HttpResponse getResponse(Result result, HttpResponse response){
    response.setResult(result);
    return response;
  }

}
