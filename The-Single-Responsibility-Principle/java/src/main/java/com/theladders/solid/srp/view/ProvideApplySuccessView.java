package com.theladders.solid.srp.view;

import java.util.Map;

import com.theladders.solid.srp.Result;

public class ProvideApplySuccessView implements View
{

  
  @Override
  public Result viewResult(Map<String, Object> model)
  {
    return new Result("success", model);
    

  }
}
