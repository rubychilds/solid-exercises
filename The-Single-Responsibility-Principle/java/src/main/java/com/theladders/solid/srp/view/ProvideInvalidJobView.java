package com.theladders.solid.srp.view;

import java.util.HashMap;
import java.util.Map;

import Utils.ModelFieldNames;

import com.theladders.solid.srp.Result;
import com.theladders.solid.srp.http.HttpResponse;

public class ProvideInvalidJobView extends View
{

  private Map<String, Object>     model = new HashMap<String, Object>();
  
  public HttpResponse getResult(HttpResponse httpresponse)
  {
    model.put(ModelFieldNames.JOB_ID, getJobID());

    Result result = new Result("invalidJob", model);
    httpresponse.setResult(result);

    return httpresponse;
  }

  @Override
  public void addError(String erro)
  {
    // TODO Auto-generated method stub
    
  }

}
