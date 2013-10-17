package com.theladders.solid.srp.view;

import java.util.Map;

import com.theladders.solid.srp.Result;
import com.theladders.solid.srp.http.HttpResponse;

public class ProvideApplySuccessView
{
  
  public ProvideApplySuccessView(HttpResponse response, Map<String, Object> model)
  {
    Result result = new Result("success", model);
    response.setResult(result);
  }

}
