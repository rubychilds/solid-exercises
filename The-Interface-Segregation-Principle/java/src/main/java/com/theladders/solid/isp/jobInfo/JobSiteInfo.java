package com.theladders.solid.isp.jobInfo;

public class JobSiteInfo implements JobSite
{
  private int jobSiteId;

  public int getJobSiteId()
  {
    return jobSiteId;
  }
  
  public void setJobSiteId(int jobSiteId)
  {
    this.jobSiteId = jobSiteId;
  }
  
  /**
   * Get the URL for this job. This is only valid for external (harvested) jobs (! isJobReq).
   * 
   * @return URL for this job.
   */
  public String getUrl()
  {
    return null;
  }
  
  
  
}
