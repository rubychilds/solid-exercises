package com.theladders.solid.srp.view;

import java.util.Map;

import com.theladders.solid.srp.Result;
import com.theladders.solid.srp.http.HttpResponse;

public class ProvideResumeCompletionView
{
  public ProvideResumeCompletionView(HttpResponse response, Map<String, Object> model)
  {
    Result result = new Result("completeResumePlease", model);
    response.setResult(result);
  }
  
  
}
