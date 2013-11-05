package com.theladders.solid.isp.jobInfo;


public class DescriptionInfo implements Description
{
  private String shortDescription;
  private String description;
  
  public DescriptionInfo(String description, String shortDescription)
  {
    this.description = description;
    this.shortDescription = shortDescription;
  }
  
  
  public String getShortJobDescription()
  {
    return shortDescription;
  }

  public String getJobDescription()
  {
    return description;
  }
  
}
