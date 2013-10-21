package com.theladders.solid.srp.view;

import java.util.Map;

import com.theladders.solid.srp.Result;

public interface View
{
  public abstract Result viewResult(Map<String, Object> model);

}
