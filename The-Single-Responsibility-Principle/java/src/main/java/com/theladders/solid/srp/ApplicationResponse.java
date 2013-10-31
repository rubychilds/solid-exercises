package com.theladders.solid.srp;

public class ApplicationResponse
{
  private ApplicationResponseType type  = ApplicationResponseType.ERROR;
  private static int              jobid = -1;
  private static String           jobtitle;
  private static String           errorMessage;

  public ApplicationResponse(ApplicationResponseType type,
                             int invalid,
                             String jobtitle,
                             String other)
  {
    this.type = type;

    if (invalid != -1)
      this.jobid = invalid;
    if (jobtitle != null)
      this.jobtitle = jobtitle;
    if (other != null)
      this.errorMessage = other;
  }

  public ApplicationResponse(ApplicationResponseType type,
                             int jobid,
                             String jobTitle)
  {
    this(type, jobid, jobTitle, null);
  }

  public ApplicationResponse(ApplicationResponseType type,
                             int jobid)
  {
    this(type, jobid, null, null);
  }

  public ApplicationResponse(ApplicationResponseType type)
  {
    this(type, -1, null, null);
  }

  public static String getErrorMessage()
  {
    return errorMessage;
  }

  public static String getjobTitle()
  {
    return errorMessage;
  }

  public static int getJobId()
  {
    return jobid;
  }
  
  public ApplicationResponseType getType()
  {
    return this.type;
  }
}
