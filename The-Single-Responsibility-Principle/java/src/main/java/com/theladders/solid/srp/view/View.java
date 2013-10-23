package com.theladders.solid.srp.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import Utils.ModelFieldNames;

import com.theladders.solid.srp.Result;

public class View{
  
  private ArrayList<String> errList = new ArrayList<String>();
  private Map<String, Object> model = new  HashMap<String, Object>();

  public Result provideInvalidJobView(int jobId)
  {
    model.put(ModelFieldNames.JOB_ID, jobId);
    return new Result("invalidJob", model);  
  }

  public Result provideResumeCompletionView(int jobId, String jobTitle)
  {

    model.put(ModelFieldNames.JOB_ID, jobId);
    model.put(ModelFieldNames.JOB_TITLE, jobTitle);

    return new Result("completeResumePlease", model);
  }
  
  public Result provideErrorView(String errorMessage)
  {
    errList.add(errorMessage);
    return new Result("error", model, errList);
  }

  public Result provideSuccessView(int jobId, String jobTitle)
  {
    model.put(ModelFieldNames.JOB_ID, jobId);
    model.put(ModelFieldNames.JOB_TITLE, jobTitle);

    return new Result("success", model);
  }
}
