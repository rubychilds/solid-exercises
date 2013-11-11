package com.theladders.solid.dipnewbie;

public interface ContentNodeInfo
{
  public Object getProperty(String key);

  public void addProperty(String key,
                          Object value);
}
