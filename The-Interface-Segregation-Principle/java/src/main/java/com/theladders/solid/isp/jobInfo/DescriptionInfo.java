package com.theladders.solid.isp.jobInfo;


public class DescriptionInfo implements Description
{
  private String shortDescription;
  private String description;
  
  public String getShortJobDescription()
  {
    return shortDescription;
  }

  public String getJobDescription()
  {
    return description;
  }
  
}
