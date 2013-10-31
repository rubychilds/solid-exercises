package com.theladders.solid.srp;

public class ResumeData implements SessionData
{
  private String       activateResume;
  private String       whichResume;
  private String       originalFileName;

  public static String EXISTING           = "existing";
  public static String ACTIVATE_RESUME    = "yes";

  public static String WHICH              = "whichResume";
  public static String MAKE_RESUME_ACTIVE = "makeResumeActive";

  public ResumeData(String originalFileName,
                    String activateResume,
                    String whichResume)
  {
    this.activateResume = activateResume;
    this.whichResume = whichResume;
    this.originalFileName = originalFileName;
  }

  public String fileName()
  {
    return originalFileName;
  }

  public boolean useExistingResume()
  {
    return EXISTING.equals(whichResume);
  }

  public boolean makeResumeActive()
  {
    return ACTIVATE_RESUME.equals(activateResume);
  }
}
