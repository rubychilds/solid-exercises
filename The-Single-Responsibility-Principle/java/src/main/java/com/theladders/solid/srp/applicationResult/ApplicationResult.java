package com.theladders.solid.srp.applicationResult;

import com.theladders.solid.srp.Result;
import com.theladders.solid.srp.http.HttpResponse;

abstract public class ApplicationResult
{
  private int      jobId;
  private String   jobTitle;
  protected Result finalResult;

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

  public HttpResponse getResponse(HttpResponse response)
  {
    response.setResult(finalResult);
    return response;
  }

  public abstract void setResult();

}
