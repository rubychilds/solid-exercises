package com.theladders.solid.isp.jobInfo;

import com.theladders.solid.isp.oldjob.stubs.City;
import com.theladders.solid.isp.oldjob.stubs.Region;

public interface JobGeography
{
  String getLocation();

  /**
   * Get the region for this job.
   * 
   * @return the region for this job.
   */
  Region getRegion();
  
  /**
   * Get the City for this job.
   *
   * @return the City for this job.
   */
  City getCity();
}