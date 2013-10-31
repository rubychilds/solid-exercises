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
    this.jobId = ApplicationResponse.getJobId();
    this.jobtitle = ApplicationResponse.getjobTitle();
  }

  public Result getResult(ApplicationResponse response)
  {
    model.put(ModelFieldNames.JOB_ID, jobId);
    model.put(ModelFieldNames.JOB_TITLE, jobtitle);
    return new Result("completeResumePlease", model);
  }

}
