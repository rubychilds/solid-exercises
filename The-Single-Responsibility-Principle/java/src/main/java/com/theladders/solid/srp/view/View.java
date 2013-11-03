package com.theladders.solid.srp.view;

import com.theladders.solid.srp.ApplicationResponse;
import com.theladders.solid.srp.ApplicationResponseType;
import com.theladders.solid.srp.Result;
import com.theladders.solid.srp.http.HttpResponse;

public abstract class View
{
  private ApplicationResponseType type;

  public abstract boolean isType(ApplicationResponseType type);

  public abstract HttpResponse getResult(ApplicationResponse response, HttpResponse hhtpresponse);

  public abstract ApplicationResponseType getType();

}
