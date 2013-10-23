package com.theladders.solid.srp.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import Utils.ModelFieldNames;

import com.theladders.solid.srp.Result;

public class View{
  
  private ArrayList<String> errList = new ArrayList<String>();
  

  public Result provideInvalidJobView(int jobId)
  {

    Map<String, Object> model = new  HashMap<String, Object>();
    model.put(ModelFieldNames.JOB_ID, jobId);

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
