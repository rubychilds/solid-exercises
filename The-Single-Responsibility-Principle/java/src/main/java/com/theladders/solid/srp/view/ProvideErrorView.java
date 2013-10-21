package com.theladders.solid.srp.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.theladders.solid.srp.Result;

public class ProvideErrorView implements View
{
  private List<String>errList;

  public ProvideErrorView()
  {
    this.errList = new  ArrayList<String>();
  }

  @Override
  public Result viewResult(Map<String, Object> model)
  {
    return new Result("error", model, errList);
  }

  
  public void addError(String errorMessage)
  {
    errList.add(errorMessage);
  }
  
}
