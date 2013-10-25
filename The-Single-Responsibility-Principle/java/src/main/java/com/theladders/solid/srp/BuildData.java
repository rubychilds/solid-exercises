package com.theladders.solid.srp;

import com.theladders.solid.srp.http.HttpRequest;

public class BuildData
{
  public static String WHICH = "whichResume";
  public static String MAKE_RESUME_ACTIVE = "makeResumeActive";
  
  public SessionData createSessionData(HttpRequest request, String origFileName)
  {
    String activateResume = request.getParameter(MAKE_RESUME_ACTIVE);
    String whichResume = request.getParameter(WHICH);

    return new ResumeData(origFileName, activateResume, whichResume);
  }
}
