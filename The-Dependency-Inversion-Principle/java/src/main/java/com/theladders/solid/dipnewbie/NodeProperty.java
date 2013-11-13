package com.theladders.solid.dipnewbie;

public interface NodeProperty
{
  public Object getProperty(String key);

  public void addProperty(String key,
                          Object value);
  
  public boolean isPublishedAndEnabled();
}
