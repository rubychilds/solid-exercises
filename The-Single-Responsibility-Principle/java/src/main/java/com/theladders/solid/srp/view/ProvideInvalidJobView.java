package com.theladders.solid.srp.view;

import java.util.HashMap;
import java.util.Map;

import Utils.ModelFieldNames;

import com.theladders.solid.srp.ApplicationResponse;
import com.theladders.solid.srp.ApplicationResponseType;
import com.theladders.solid.srp.Result;

public class ProvideInvalidJobView extends View
{
  
  private ApplicationResponseType type = ApplicationResponseType.INVALID_JOB;
  private Map<String, Object> model = new  HashMap<String, Object>();
  private int jobId;

  public boolean isType(ApplicationResponseType type){
    return this.type  == type;
  }
  
  protected void setApplicationResponse(ApplicationResponse response)
  {
    this.jobId = response.getJobId();
  }
    
  public Result getResult(ApplicationResponse response)
  {
    setApplicationResponse(response);
    model.put(ModelFieldNames.JOB_ID, jobId);
    return new Result("invalidJob", model);
 }
  
  public ApplicationResponseType getType()
  {
    return this.type;
  }
  
}
