package com.theladders.solid.isp.jobInfo;

public interface Description
{
  /**
   * Get this job's short description.
   * 
   * @return a summary description of this job.
   */
  String getShortJobDescription();
  /**
   * Refactored so it can be used by both job and JobReq
   * 
   * @return fullJobDescription()
   */
  String getJobDescription();
}
