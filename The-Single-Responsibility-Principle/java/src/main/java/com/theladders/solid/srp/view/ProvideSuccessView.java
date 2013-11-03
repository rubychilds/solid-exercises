package com.theladders.solid.srp.view;

import java.util.HashMap;
import java.util.Map;

import Utils.ModelFieldNames;

import com.theladders.solid.srp.ApplicationResponse;
import com.theladders.solid.srp.ApplicationResponseType;
import com.theladders.solid.srp.Result;

public class ProvideSuccessView extends View
{

  private ApplicationResponseType type  = ApplicationResponseType.SUCESSFUL;
  private Map<String, Object>     model = new HashMap<String, Object>();
  private int                     jobId;
  private String                  jobtitle;

  public Result getResult(ApplicationResponse response)
  {
    this.jobId = response.getJobId();
    this.jobtitle = response.getjobTitle();
    model.put(ModelFieldNames.JOB_ID, jobId);
    model.put(ModelFieldNames.JOB_TITLE, jobtitle);
    return new Result("success", model);
  }
  public ApplicationResponseType getType()
  {
    return this.type;
  }
  
  public boolean isType(ApplicationResponseType type)
  {
    return this.type.equals(type);
  }

}
