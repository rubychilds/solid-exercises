package com.theladders.solid.isp.jobInfo;

public interface JobVisibility
{

  /**
   * @return returns true if this job was posted anonymously
   */
  boolean isAnonymous();

  boolean isConfidential();

  boolean isExclusive();
}
