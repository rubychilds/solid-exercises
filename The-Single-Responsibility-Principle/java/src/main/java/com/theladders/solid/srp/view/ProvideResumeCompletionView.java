package com.theladders.solid.srp.view;

import java.util.HashMap;
import java.util.Map;

import Utils.ModelFieldNames;

import com.theladders.solid.srp.ApplicationResponse;
import com.theladders.solid.srp.ApplicationResponseType;
import com.theladders.solid.srp.Result;

public class ProvideResumeCompletionView extends View
{
  private ApplicationResponseType type  = ApplicationResponseType.NEEDS_COMPLETION;
  private Map<String, Object>     model = new HashMap<String, Object>();
  private int                     jobId;
  private String                  jobtitle;

  protected void setApplicationResponse(ApplicationResponse response)
  {
    this.jobId = response.getJobId();
    this.jobtitle = response.getjobTitle();
  }

  public Result getResult(ApplicationResponse response)
  {
    System.out.println("complete resume - in view");
    setApplicationResponse(response);
    model.put(ModelFieldNames.JOB_ID, jobId);
    model.put(ModelFieldNames.JOB_TITLE, jobtitle);
    return new Result("completeResumePlease", model);
  }
  
  public ApplicationResponseType getType()
  {
    return this.type;
  }

  @Override
  public boolean isType(ApplicationResponseType type)
  {
    return this.type.equals(type);
  }
  

}
