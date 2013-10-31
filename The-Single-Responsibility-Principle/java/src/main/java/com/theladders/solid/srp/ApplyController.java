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
import com.theladders.solid.srp.view.ViewContainer;

public class ApplyController
{
  private ApplicationProcess applicationProcessor;
  private JobSearchService   jobSearchService;

  public ApplyController(JobseekerProfileManager jobseekerProfileManager,
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

  public HttpResponse handle(HttpRequest request,
                             HttpResponse response,
                             String origFileName)
  {
    SessionData resumeData = new BuildData().createSessionData(request, origFileName);
    
    System.out.println("resume name" + origFileName);
    int jobId = Integer.parseInt(request.getParameter("jobId"));

    HttpSession session = request.getSession();

    // Jobseeker
    Jobseeker jobseeker = session.getJobseeker();

    int jobseekerId = jobseeker.getId();
    // Job
    Job job = jobSearchService.getJob(jobId);
    // ApplyProcessor

    ApplicationResponse applicationResponse = applicationProcessor.execute(job, jobseeker, resumeData);
    ViewContainer views = new ViewContainer();

    View finalView = views.getView(applicationResponse.getType());

    Result result = finalView.getResult(applicationResponse);

    return getResponse(result, response);
  }

  private HttpResponse getResponse(Result result,
                                   HttpResponse response)
  {
    response.setResult(result);
    return response;
  }
}
