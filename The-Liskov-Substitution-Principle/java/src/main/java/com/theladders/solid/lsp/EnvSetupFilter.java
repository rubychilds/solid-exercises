package com.theladders.solid.lsp;

import java.util.Map;

/**
 * This filter puts an Environment object in the request and session. The environment is determined
 * by the host part of the request URL: if the host corresponds to a specific ladder, that ladder's
 * environment is selected; if the host is anything else -- including 'www', a default 'TheLadder'
 * environment is used. This filter also populates the AdminUpdater thread-local var.
 * 
 * @author Zhi-Da Zhong &lt;zz@theladders.com&gt;
 */

public class EnvSetupFilter
{
  public static final String SECURE_HOST = "secure";

  private final String       hostName;

  public EnvSetupFilter(String hostName)
  {
    this.hostName = hostName;
  }

  public DynamicEnvironment getEnvironment(boolean isSecure,
                                    boolean loggedInUser)
  {
    Environment baseEnv = EnvironmentFactory.getEnvironmentFor(hostName);

    boolean sslIsSupported = Boolean.parseBoolean((String) baseEnv.get("isSSL"));
    
    Map<String, String> keyMap = buildKeyMap(sslIsSupported, isSecure);

    DynamicEnvironment dynamicEnv = new DynamicEnvironment(baseEnv, keyMap);

    new SiteConfiguration().seedEnvironment(dynamicEnv); // NEEDS TO BE DEALT WITH - no longer extends environment

    // Adds /member to site URLs if the user is logged in.
    if (loggedInUser)
    {
      /* Ensure site.home is member home */
      dynamicEnv.put("home", dynamicEnv.get("home") + SiteConfiguration.MEMBER_PATH_PREFIX);
      dynamicEnv.put("secureHome", dynamicEnv.get("secureHome") + SiteConfiguration.MEMBER_PATH_PREFIX);
    }

    return dynamicEnv;
  }

  public Map<String, String> buildKeyMap(boolean sslIsSupported,
                                         boolean isSecure)
  {
    Map<String, String> keyMap;
    if (!sslIsSupported)
    {
      keyMap = new NoSSLPropMap();
    }
    else if (isSecure)
    {
      keyMap = new SecurePropMap();
    }
    else
    {
      keyMap = new InsecurePropMap();
    }
    return keyMap;
  }

}
