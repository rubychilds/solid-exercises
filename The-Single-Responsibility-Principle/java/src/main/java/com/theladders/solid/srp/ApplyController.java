package com.theladders.solid.srp;

import com.theladders.solid.srp.http.HttpRequest;
import com.theladders.solid.srp.http.HttpResponse;
import com.theladders.solid.srp.http.HttpSession;
import com.theladders.solid.srp.job.Job;
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
                                                         jobApplicationSystem,
                                                         resumeManager,
                                                         myResumeManager);
    this.jobSearchService = jobSearchService;
  }

  public HttpResponse handle(HttpRequest request,
                             HttpResponse response,
                             String origFileName)
  {
    SessionData currentSession = new BuildData().createSessionData(request, origFileName);

    int jobId = Integer.parseInt(request.getParameter("jobId"));

    HttpSession session = request.getSession();
    
    Jobseeker jobseeker = session.getJobseeker();
    int jobseekerId = jobseeker.getId();

    apply(jobId, jobseekerId, currentSession);

    return getResponse();
  }

  private Result apply(int jobId, int jobseekerId, SessionData resumeData)
  {
    Job job = jobSearchService.getJob(jobId);   
    return applicationProcessor.execute(currentSession, origFileName, job);
  }

  private HttpResponse getResponse(Result result, HttpResponse response)
  {
    response.setResult(result);
    return response;
  }
}
