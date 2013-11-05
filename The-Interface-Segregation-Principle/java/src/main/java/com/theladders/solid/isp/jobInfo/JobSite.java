package com.theladders.solid.isp.jobInfo;

public interface JobSite
{
  /**
   * Return the jobsite id for this job.
   * 
   * @return jobsite id for this job.
   */
  int getJobSiteId();
  
  /**
   * Get the URL for this job. This is only valid for external (harvested) jobs (! isJobReq).
   * 
   * @return URL for this job.
   */
  String getUrl();
}
