package com.theladders.solid.srp.applicationResult;

import java.util.HashMap;
import java.util.Map;

import com.theladders.solid.srp.Result;
import com.theladders.solid.srp.http.HttpResponse;
import com.theladders.solid.srp.utils.ModelFieldNames;

public class ProvideResumeCompletionResult extends ApplicationResult
{
  private Map<String, Object> model = new HashMap<String, Object>();

  public void setResult()
  {
    model.put(ModelFieldNames.JOB_ID, getJobID());
    model.put(ModelFieldNames.JOB_TITLE, getJobTitle());

    finalResult = new Result("completeResumePlease", model);
  }

}
