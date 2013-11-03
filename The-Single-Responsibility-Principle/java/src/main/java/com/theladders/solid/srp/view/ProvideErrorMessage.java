package com.theladders.solid.srp.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import Utils.ModelFieldNames;

import com.theladders.solid.srp.ApplicationResponse;
import com.theladders.solid.srp.ApplicationResponseType;
import com.theladders.solid.srp.Result;
import com.theladders.solid.srp.http.HttpResponse;

public class ProvideErrorMessage extends View
{
  private ArrayList<String> errList = new ArrayList<String>();

  private Map<String, Object> model = new  HashMap<String, Object>();

  public HttpResponse getResult(HttpResponse httpresponse)
  {
    String errorMessage = response.getErrorMessage(); // must add errorMessage
    errList.add(errorMessage);
    
    Result result = new Result("error", model, errList);
    httpresponse.setResult(result);
    
    return httpresponse;
  }

}