package com.theladders.solid.srp.view;

import java.util.Map;

import com.theladders.solid.srp.Result;

public class ProvideInvalidJobView implements View
{
  
  @Override
  public Result view(Map<String, Object> model)
  {
    return new Result("invalidJob", model);  
  }

  
}
  
