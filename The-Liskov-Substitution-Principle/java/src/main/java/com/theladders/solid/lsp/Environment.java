package com.theladders.solid.lsp;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

public class Environment
{
  public static final String KEY_EMAIL_DOMAIN = "emaildomain";
  private static HashMap<String, String> environment;
  
  public Environment()
  {
    this.environment = new HashMap<String,String>();
  }

  /**
   * Convenience method that returns the admin email address for this ladder.
   *
   * @return email address or "" if either the user or domain is not defined
   */
  
  public void put(String key, String value)
  {
    environment.put(key, value);
  }
  
  public String get(Object object)
  {
    return environment.get(object);

  }
  
  public Set<Entry<String, String>> entrySet()
  {
    return environment.entrySet();
  }
  
  public Set<String> keySet()
  {
    return environment.keySet();
  }

  public String getAdminEmail()
  {
    String user = getString("admin");
    String domain = getString(KEY_EMAIL_DOMAIN);

    return user.length() > 0 && domain.length() > 0 ? user + "@" + domain : "";
  }

  public String getString(String key)
  {
    Object val = get(key);
    return (val != null) ? val.toString().trim() : "";
  }

  public Collection<String> values()
  {
    return environment.values();
  }
}
