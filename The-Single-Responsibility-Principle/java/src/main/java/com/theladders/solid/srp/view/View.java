package com.theladders.solid.srp.view;

import com.theladders.solid.srp.ApplicationResponse;
import com.theladders.solid.srp.ApplicationResponseType;
import com.theladders.solid.srp.Result;

public abstract class View
{
  private ApplicationResponseType type;

  public abstract boolean isType(ApplicationResponseType type);

  public abstract Result getResult(ApplicationResponse response);

  public abstract ApplicationResponseType getType();

}
