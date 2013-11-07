package com.theladders.solid.dip;

public interface ContentNode
{
  public Object getProperty(String key);
  public void addProperty(String key, Object value);
}
