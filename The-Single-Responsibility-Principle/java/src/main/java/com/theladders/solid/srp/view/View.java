package com.theladders.solid.srp.view;

import com.theladders.solid.srp.http.HttpResponse;

abstract public class View
{
  private int jobId;

  public void setJobID(int jobId)
  {
    this.jobId = jobId;
  }

  public int getJobID()
  {
    return jobId;
  }

  public abstract HttpResponse getResult(HttpResponse response);
  
}
