package com.theladders.solid.srp.view;

public class View
{
  private int jobId;
  private String jobTitle;

  public View(int jobId)
  {
    this.jobId = jobId;
  }

  public int getJobID()
  {
    return jobId;
  }

  public void setJob(int jobId)
  {
    this.jobId = jobId;
  }

  public String getJobTitle()
  {
    return jobTitle;
  }
  
  public void setJobTitle(String jobTitle)
  {
    this.jobTitle = jobTitle;
  }
  
}
