package com.theladders.solid.srp;

import com.theladders.solid.srp.applicationResult.ApplicationResult;
import com.theladders.solid.srp.http.HttpRequest;
import com.theladders.solid.srp.http.HttpResponse;
import com.theladders.solid.srp.http.HttpSession;
import com.theladders.solid.srp.job.JobSearchService;
import com.theladders.solid.srp.job.application.JobApplicationSystem;
import com.theladders.solid.srp.jobseeker.Jobseeker;
import com.theladders.solid.srp.jobseeker.JobseekerProfileManager;
import com.theladders.solid.srp.resume.MyResumeManager;
import com.theladders.solid.srp.resume.ResumeManager;

public class ApplyController
{

  private ApplicationBuffer applicationBuffer;

  private static String     WHICH              = "whichResume";
  private static String     MAKE_RESUME_ACTIVE = "makeResumeActive";

  public ApplyController(JobseekerProfileManager jobseekerProfileManager,
                         JobSearchService jobSearchService,
                         JobApplicationSystem jobApplicationSystem,
                         ResumeManager resumeManager,
                         MyResumeManager myResumeManager)
  {

    this.applicationBuffer = new ApplicationBuffer(jobseekerProfileManager,
                                                   jobSearchService,
                                                   jobApplicationSystem,
                                                   resumeManager,
                                                   myResumeManager);

  }

  public HttpResponse handle(HttpRequest request,
                             HttpResponse response,
                             String origFileName)
  {

    int jobId = Integer.parseInt(request.getParameter("jobId"));

    HttpSession session = request.getSession();
    Jobseeker jobseeker = session.getJobseeker();
    String activateResume = request.getParameter(MAKE_RESUME_ACTIVE);
    String whichResume = request.getParameter(WHICH);

    ApplicationResult applicationResult = applicationBuffer.execute(jobId,
                                                                    origFileName,
                                                                    jobseeker,
                                                                    activateResume,
                                                                    whichResume);
    return applicationResult.getResult(response) ;
  }

}
