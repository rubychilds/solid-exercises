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
  private int                     jobId;
  private String                  jobtitle;

  public HttpResponse getResult(ApplicationResponse response,
                                HttpResponse httpresponse)
  {
    this.jobId = response.getJobId();
    this.jobtitle = response.getjobTitle();

    model.put(ModelFieldNames.JOB_ID, jobId);
    model.put(ModelFieldNames.JOB_TITLE, jobtitle);

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

}
