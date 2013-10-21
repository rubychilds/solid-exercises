package com.theladders.solid.srp.view;

import java.util.Map;

import com.theladders.solid.srp.Result;

public class ProvideResumeCompletionView implements View
{
  public Result viewResult(Map<String, Object> model)
  {
    return new Result("completeResumePlease", model);
  }
  
}
