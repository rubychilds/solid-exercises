package com.theladders.solid.srp.applicationResult;

import java.util.HashMap;
import java.util.Map;

import com.theladders.solid.srp.Result;
import com.theladders.solid.srp.http.HttpResponse;
import com.theladders.solid.srp.utils.ModelFieldNames;

public class ProvideResumeCompletionResult extends ApplicationResult
{
  private Map<String, Object> model = new HashMap<String, Object>();
  private String              jobTitle;

  public HttpResponse getResult(HttpResponse httpresponse)
  {
    model.put(ModelFieldNames.JOB_ID, getJobID());
    model.put(ModelFieldNames.JOB_TITLE, getJobTitle());

    Result result = new Result("completeResumePlease", model);
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

  @Override
  public void addError(String error)
  {
    // TODO Auto-generated method stub
  }

}
