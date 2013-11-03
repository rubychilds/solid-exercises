package com.theladders.solid.srp.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.theladders.solid.srp.Result;
import com.theladders.solid.srp.http.HttpResponse;

public class ProvideErrorMessage extends ApplicationResult
{
  private ArrayList<String> errList = new ArrayList<String>();

  private Map<String, Object> model = new  HashMap<String, Object>();

  public HttpResponse getResult(HttpResponse httpresponse)
  {
    Result result = new Result("error", model, errList);
    httpresponse.setResult(result);
    
    return httpresponse;
  }
  
  public void addError(String error)
  {
    errList.add(error);
  }

}