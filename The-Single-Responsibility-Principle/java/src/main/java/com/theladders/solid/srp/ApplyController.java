package com.theladders.solid.srp;

import java.util.HashMap;
import java.util.Map;

import Utils.HttpRequestParameters;
import Utils.ModelFieldNames;

import com.theladders.solid.srp.http.HttpRequest;
import com.theladders.solid.srp.http.HttpResponse;
import com.theladders.solid.srp.http.HttpSession;
import com.theladders.solid.srp.job.JobSearchService;
import com.theladders.solid.srp.job.application.JobApplicationSystem;
import com.theladders.solid.srp.jobseeker.Jobseeker;
import com.theladders.solid.srp.jobseeker.JobseekerProfileManager;
import com.theladders.solid.srp.resume.MyResumeManager;
import com.theladders.solid.srp.resume.ResumeManager;
import com.theladders.solid.srp.view.View;

public class ApplyController
{  
  private ApplicationProcessor applicationProcessor;
  private JobSearchService jobSearchService;
  
  public ApplyController(JobseekerProfileManager jobseekerProfileManager,
                         JobSearchService jobSearchService,
                         JobApplicationSystem jobApplicationSystem,
                         ResumeManager resumeManager,
                         MyResumeManager myResumeManager)
  {
 
    this.applicationProcessor = new ApplicationProcessor(jobseekerProfileManager,
                                                         jobSearchService,
                                                         jobApplicationSystem,
                                                         resumeManager,
                                                         myResumeManager);
    this.jobSearchService = jobSearchService;
  }

  public HttpResponse handle(HttpRequest request,
                             HttpResponse response,
                             String origFileName)
  {
   
    SessionData currentSession = createSessionData(request);
    
    int jobId = currentSession.getJobId();
    if (!jobSearchService.jobExists(jobId))
    {
      Result result = new View().provideInvalidJobView(jobId);
      return getResponse(result, response);
    }

    Result result = applicationProcessor.execute(currentSession, origFileName);
    
    return getResponse(result, response);
  }
 
  private HttpResponse getResponse(Result result, HttpResponse response){
    response.setResult(result);
    return response;
  }
  
  private SessionData createSessionData(HttpRequest request)
  {
    String jobId = request.getParameter(HttpRequestParameters.JOB_ID);
    String activateResume = request.getParameter(HttpRequestParameters.MAKE_RESUME_ACTIVE);
    String whichResume = request.getParameter(HttpRequestParameters.WHICH);
    
    HttpSession currentSession = request.getSession();
    Jobseeker jobseeker = currentSession.getJobseeker();
    
    ApplicationData currentData = new ApplicationData(jobId, activateResume, whichResume, jobseeker);
    
    return currentData;
  }

}
