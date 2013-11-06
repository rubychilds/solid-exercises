package com.theladders.solid.srp.applicationResult;

import java.util.ArrayList;
import java.util.List;

import com.theladders.solid.srp.Result;
import com.theladders.solid.srp.http.HttpResponse;

abstract public class ApplicationResult
{
  private int      jobId;
  private String   jobTitle;
  protected Result finalResult = null;
  protected ArrayList<String> errList = new ArrayList<String>();

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

  public Result getResult()
  {
    return finalResult;
  }
  
  public List<String> getErrList()
  {
    return errList;
  }
  
}
