package com.theladders.solid.srp.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import Utils.ModelFieldNames;

import com.theladders.solid.srp.ApplicationResponse;
import com.theladders.solid.srp.ApplicationResponseType;
import com.theladders.solid.srp.Result;

public class ProvideErrorMessage extends View
{
  private ArrayList<String> errList = new ArrayList<String>();

  private ApplicationResponseType type  = ApplicationResponseType.ERROR;
  private Map<String, Object> model = new  HashMap<String, Object>();

  protected void setApplicationResponse(ApplicationResponse response)
  {
    String errorMessage = response.getErrorMessage();
    errList.add(errorMessage);
  }

  public Result getResult(ApplicationResponse response)
  {
    setApplicationResponse(response);
    return new Result("error", model, errList);
  }
  public ApplicationResponseType getType()
  {
    return this.type;
  }
  
  public boolean isType(ApplicationResponseType type)
  {
    return this.type.equals(type);
  }
}