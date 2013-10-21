package com.theladders.solid.srp.view;

import java.util.ArrayList;
import java.util.Map;

import com.theladders.solid.srp.Result;

public class View{
  
  private ArrayList<String> errList = new ArrayList<String>();
  
  
  public Result provideInvalidJobView(Map<String, Object> model)
  {
      return new Result("invalidJob", model);  
  }

  public Result provideResumeCompletionView(Map<String, Object> model)
  {
    return new Result("completeResumePlease", model);
  }
  
  public Result provideErrorView(Map<String, Object> model)
  {
    return new Result("error", model, errList);
  }

  public Result provideSuccessView(Map<String, Object> model)
  {
    return new Result("success", model);
  }
  
  public void addError(String errorMessage)
  {
    errList.add(errorMessage);
  }

}
