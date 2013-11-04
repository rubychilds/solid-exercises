package com.theladders.solid.srp.applicationResult;

import java.util.HashMap;
import java.util.Map;

import com.theladders.solid.srp.Result;
import com.theladders.solid.srp.http.HttpResponse;
import com.theladders.solid.srp.utils.ModelFieldNames;

public class ProvideInvalidJobResult extends ApplicationResult
{

  private Map<String, Object> model = new HashMap<String, Object>();

  public void setResult()
  {
    model.put(ModelFieldNames.JOB_ID, getJobID());
    finalResult = new Result("invalidJob", model);
  }


}
