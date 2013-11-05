package com.theladders.solid.isp.jobInfo;


public class JobDescriptionInfo implements JobDescription
{
  private String shortDescription;
  private String description;
  
  public JobDescriptionInfo(String description, String shortDescription)
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
