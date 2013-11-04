package com.theladders.solid.srp.applicationResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.theladders.solid.srp.Result;

public class ProvideErrorMessage extends ApplicationResult
{
  private ArrayList<String> errList = new ArrayList<String>();

  private Map<String, Object> model = new  HashMap<String, Object>();

  public void setResult()
  {
    finalResult = new Result("error", model, errList);
  }
  
  public void addError(String error)
  {
    errList.add(error);
  }

}