package com.theladders.solid.dipnewbie;

public interface NodeInfo
{
  public Object getProperty(String key);

  public void addProperty(String key,
                          Object value);
}
