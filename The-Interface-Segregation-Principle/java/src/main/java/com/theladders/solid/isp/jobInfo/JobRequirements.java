package com.theladders.solid.isp.jobInfo;

import com.theladders.solid.isp.oldjob.stubs.Experience;

public interface JobRequirements
{

  /**
   * Returns an object that represents the number of years of experience that are required for this
   * job.
   * 
   * @return experience object
   */
  Experience getExperience();
}
