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
import com.theladders.solid.srp.view.ViewCollection;
import com.theladders.solid.srp.view.ViewContainer;

public class ApplyController
{
  private ApplicationProcess applicationProcessor;
  private JobSearchService   jobSearchService;
  private static String      WHICH              = "whichResume";
  private static String      MAKE_RESUME_ACTIVE = "makeResumeActive";

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
    SessionData resumeData = createResumeData(request, origFileName);

    int jobId = Integer.parseInt(request.getParameter("jobId"));

    HttpSession session = request.getSession();

    Jobseeker jobseeker = session.getJobseeker();
    Job job = jobSearchService.getJob(jobId);

    ViewCollection viewCollection = new ViewCollection();
    View finalView = applicationProcessor.execute(jobId, job, jobseeker, resumeData, viewCollection);

    return finalView.getResult(applicationResponse, response);
  }

  private SessionData createResumeData(HttpRequest request,
                                       String origFileName)
  {
    String activateResume = request.getParameter(MAKE_RESUME_ACTIVE);
    String whichResume = request.getParameter(WHICH);

    return new ResumeData(origFileName, activateResume, whichResume);
  }

}
