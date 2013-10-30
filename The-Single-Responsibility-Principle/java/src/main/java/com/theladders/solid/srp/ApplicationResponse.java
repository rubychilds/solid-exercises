package com.theladders.solid.srp;

public class ApplicationResponse
{
  private ApplicationResponseType type = ApplicationResponseType.ERROR;
  private int jobid = -1;
  private String jobtitle;
  private String errorMessage;

  public ApplicationResponse(ApplicationResponseType type,
                             int invalid,
                             String jobtitle,
                             String other)
  {
    this.type = type;
    
    if(invalid != -1)
      this.jobid = invalid;
    if(jobtitle != null)
      this.jobtitle = jobtitle;
    if(other != null)
      this.errorMessage = other;
  }
  
  public ApplicationResponse(ApplicationResponseType type, int jobid, String jobTitle)
  {
    this(type, jobid, jobTitle, null);
  }
  
  public ApplicationResponse(ApplicationResponseType type, int jobid)
  {
    this(type, jobid, null, null);
  }
  
  
  public ApplicationResponse(ApplicationResponseType type)
  {
    this(type, -1, null, null);
  }

}
