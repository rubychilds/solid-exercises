package com.theladders.solid.srp.view;

import java.util.HashMap;
import java.util.Map;

import Utils.ModelFieldNames;

import com.theladders.solid.srp.ApplicationResponse;
import com.theladders.solid.srp.ApplicationResponseType;
import com.theladders.solid.srp.Result;
import com.theladders.solid.srp.http.HttpResponse;

public class ProvideResumeCompletionView extends View
{
  private ApplicationResponseType type  = ApplicationResponseType.NEEDS_COMPLETION;
  private Map<String, Object>     model = new HashMap<String, Object>();
  private String                  jobTitle;

  public HttpResponse getResult(HttpResponse httpresponse)
  {
    model.put(ModelFieldNames.JOB_ID, getJobID());
    model.put(ModelFieldNames.JOB_TITLE, getJobTitle());

    Result result = new Result("completeResumePlease", model);
    httpresponse.setResult(result);

    return httpresponse;
  }

  public ApplicationResponseType getType()
  {
    return this.type;
  }

  public boolean isType(ApplicationResponseType type)
  {
    return this.type.equals(type);
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
