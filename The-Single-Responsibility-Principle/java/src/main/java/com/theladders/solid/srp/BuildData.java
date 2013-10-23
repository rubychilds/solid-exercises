package com.theladders.solid.srp;

import Utils.HttpRequestParameters;

import com.theladders.solid.srp.http.HttpRequest;
import com.theladders.solid.srp.http.HttpSession;
import com.theladders.solid.srp.jobseeker.Jobseeker;


public class BuildData
{
  public SessionData createSessionData(HttpRequest request)
  {
    String jobId = request.getParameter(HttpRequestParameters.JOB_ID);
    String activateResume = request.getParameter(HttpRequestParameters.MAKE_RESUME_ACTIVE);
    String whichResume = request.getParameter(HttpRequestParameters.WHICH);

    HttpSession currentSession = request.getSession();
    Jobseeker jobseeker = currentSession.getJobseeker();

    return new ApplicationData(jobId, activateResume, whichResume, jobseeker);
  }
}
