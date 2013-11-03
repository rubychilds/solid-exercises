package com.theladders.solid.srp.view;

import java.util.HashMap;
import java.util.Map;

import Utils.ModelFieldNames;

import com.theladders.solid.srp.Result;
import com.theladders.solid.srp.http.HttpResponse;

public class ProvideSuccessResult extends ApplicationResult
{

  private Map<String, Object> model = new HashMap<String, Object>();
  private String              jobTitle;

  public HttpResponse getResult(HttpResponse httpresponse)
  {
    model.put(ModelFieldNames.JOB_ID, getJobID());
    model.put(ModelFieldNames.JOB_TITLE, getJobTitle());

    Result result = new Result("success", model);
    httpresponse.setResult(result);

    return httpresponse;
  }

  public String getJobTitle()
  {
    return jobTitle;
  }

  public void setJobTitle(String jobTitle)
  {
    this.jobTitle = jobTitle;
  }

  public void addError(String error)
  {
    
  }

}
