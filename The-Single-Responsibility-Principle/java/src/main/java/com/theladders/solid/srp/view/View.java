package com.theladders.solid.srp.view;

import com.theladders.solid.srp.ApplicationResponse;
import com.theladders.solid.srp.ApplicationResponseType;
import com.theladders.solid.srp.Result;

public abstract class View
{

  private ApplicationResponseType type;

  public boolean isType(ApplicationResponseType type)
  {
    return this.type == type;
  }

  public abstract Result getResult(ApplicationResponse response);

  protected abstract void setApplicationResponse(ApplicationResponse response);

}
