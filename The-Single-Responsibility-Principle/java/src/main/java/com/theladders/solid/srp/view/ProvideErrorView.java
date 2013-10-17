package com.theladders.solid.srp.view;

import java.util.List;
import java.util.Map;

import com.theladders.solid.srp.Result;
import com.theladders.solid.srp.http.HttpResponse;

public class ProvideErrorView
{
  public HttpResponse response;

  public ProvideErrorView(HttpResponse response,
                               List<String> errList,
                               Map<String, Object> model)
  {
    Result result = new Result("error", model, errList);
    response.setResult(result);
    
  }
  
}
