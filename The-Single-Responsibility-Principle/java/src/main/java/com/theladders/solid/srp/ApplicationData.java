package com.theladders.solid.srp;

import com.theladders.solid.srp.jobseeker.Jobseeker;

public class ApplicationData implements SessionData
{
  
  private String jobId;
  private String activateResume;
  private String whichResume;
  private Jobseeker jobseeker;
  
  public ApplicationData(String jobId,
                         String activateResume, 
                         String whichResume,
                         Jobseeker jobseeker)
  {
    this.jobId = jobId;
    this.activateResume = activateResume;
    this.whichResume = whichResume;
    this.jobseeker = jobseeker;
  }
  
  public int getJobId()
  {
    return Integer.parseInt(this.jobId);
  };
  public String activateResume()
  {
    return this.activateResume;
  };
  public String whichResume()
  {
    return this.whichResume;
  };
  public Jobseeker getJobseeker()
  {
    return this.jobseeker;
  };



}
