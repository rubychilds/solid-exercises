package com.theladders.solid.srp.applicationResult;

import com.theladders.solid.srp.http.HttpResponse;

abstract public class ApplicationResult
{
  private int jobId;
  private String jobTitle;

  public void setJobID(int jobId)
  {
    this.jobId = jobId;
  }

  public int getJobID()
  {
    return jobId;
  }
  
  public void setJobTitle(String title)
  {
    this.jobTitle = title;
  }

  public String getJobTitle()
  {
    return jobTitle;
  }

  public abstract HttpResponse getResult(HttpResponse response);

  public abstract void addError(String erro);
  
}
