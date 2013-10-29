package com.theladders.solid.lsp;

import static org.junit.Assert.assertEquals;

import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.theladders.solid.lsp.EnvSetupFilter;
import com.theladders.solid.lsp.DynamicEnvironment;

public class DynamicEnvironmentTest
{

  /*
   * @Test public void testWithEmptyParameters() { Map<String, String> keyMap = new HashMap<String,
   * String>(); Environment environment = new Environment(); DynamicEnvironment dynamicEnvironment =
   * new DynamicEnvironment(environment, keyMap); // both null objects - checking this
   * Set<Map.Entry<Object, Object>> entrySet = dynamicEnvironment.entrySet();
   * assertTrue(entrySet.isEmpty()); Set<Object> keySet = dynamicEnvironment.keySet();
   * assertTrue(keySet.isEmpty()); }
   * @Test public void testWithOneItemInEnvironment() { Map<String, String> keyMap = new
   * HashMap<String, String>(); Environment environment = new Environment(); String key = "ruby";
   * String value = "hello"; environment.put(key, value); DynamicEnvironment dynamicEnvironment =
   * new DynamicEnvironment(environment, keyMap); Set<Map.Entry<Object, Object>> entrySet =
   * dynamicEnvironment.entrySet(); assertEquals(entrySet.size(), 1); Set<Object> keySet =
   * dynamicEnvironment.keySet(); assertEquals(keySet.size(), 1); Object returnedValue =
   * dynamicEnvironment.get(key); assertEquals(returnedValue, value); }
   * @Test public void testWithOneItemInBase() { Map<String, String> keyMap = new HashMap<String,
   * String>(); Environment environment = new Environment(); String key = "ruby"; String value =
   * "hello"; keyMap.put(key, value); DynamicEnvironment dynamicEnvironment = new
   * DynamicEnvironment(environment, keyMap); Set<Map.Entry<Object, Object>> entrySet =
   * dynamicEnvironment.entrySet(); assertTrue(entrySet.isEmpty()); Set<Object> keySet =
   * dynamicEnvironment.keySet(); assertEquals(keySet.size(), 1); Object returnedValue =
   * dynamicEnvironment.get(key); assertEquals(returnedValue, null); }
   */
  private EnvSetupFilter      filter;
  private DynamicEnvironment  dynamicEnvironment;
  private static final String hostName                = "www.theladders.com/";

  @Before
  public void SetUp()
  {
    this.filter = new EnvSetupFilter(hostName);

  }

  @Test
  public void isSecureisLoggedIn()
  {
    boolean isSecure = true;
    boolean loggedInUser = true;

    dynamicEnvironment = filter.getEnvironment(isSecure, loggedInUser);
    Set<Object> keySet = dynamicEnvironment.keySet();

    assertEquals(dynamicEnvironment.get("autoProtoHome"), "https://www.theladders.com/member/");
    assertEquals(dynamicEnvironment.get("secureFalconSiteHome"), "https://www.theladders.com/");
    assertEquals(dynamicEnvironment.get("memberHome"), (null));
    assertEquals(dynamicEnvironment.get("memberSiteHome"), "http://www.theladders.com/member/");
    assertEquals(dynamicEnvironment.get("guestSiteHome"), "http://www.theladders.com/");
    assertEquals(dynamicEnvironment.get("secureGuestSiteHome"), "https://www.theladders.com/");
    assertEquals(dynamicEnvironment.get("secureHome"), "https://www.theladders.com/member/");
    assertEquals(dynamicEnvironment.get("secureMemberSiteHome"), "https://www.theladders.com/member/");
    assertEquals(dynamicEnvironment.get("falconSiteHome"), "http://www.theladders.com/");
    assertEquals(dynamicEnvironment.get("guestHome"), null);

    assertEquals(dynamicEnvironment.get("staticBase"), null);
    assertEquals(dynamicEnvironment.get("flash"), null);
    assertEquals(dynamicEnvironment.get("widgets"), null);
    assertEquals(dynamicEnvironment.get("home"), ("http://www.theladders.com/member/"));
    assertEquals(dynamicEnvironment.get("js"), null);
    assertEquals(dynamicEnvironment.get("opalImages"), (null));

    assertEquals(dynamicEnvironment.get("isSSL"), "true");
    assertEquals(dynamicEnvironment.get("seoImages"), null);

    assertEquals(dynamicEnvironment.get("landingCss"), (null));

    assertEquals(dynamicEnvironment.get("landingImages"), (null));
    assertEquals(dynamicEnvironment.get("opalCss"), (null));

    assertEquals(dynamicEnvironment.get("images"), null);
    assertEquals(dynamicEnvironment.get("seoCss"), null);

    assertEquals(dynamicEnvironment.get("landingFlash"), null);
    assertEquals(dynamicEnvironment.get("css"), null);
  }

  @Test
  public void isNotSecureisNotLoggedIn()
  {

    boolean isSecure = false;
    boolean loggedInUser = false;

    dynamicEnvironment = filter.getEnvironment(isSecure, loggedInUser);

    assertEquals(dynamicEnvironment.get("secureHome"), "https://www.theladders.com/");
    assertEquals(dynamicEnvironment.get("home"), "http://www.theladders.com/");
    assertEquals(dynamicEnvironment.get("memberHome"), null);
    assertEquals(dynamicEnvironment.get("memberSiteHome"), "http://www.theladders.com/member/");
    assertEquals(dynamicEnvironment.get("secureMemberSiteHome"), "https://www.theladders.com/member/");
    assertEquals(dynamicEnvironment.get("guestSiteHome"), "http://www.theladders.com/");
    assertEquals(dynamicEnvironment.get("falconSiteHome"), "http://www.theladders.com/");
    assertEquals(dynamicEnvironment.get("isSSL"), "true");
    assertEquals(dynamicEnvironment.get("autoProtoHome"), "http://www.theladders.com/");
    assertEquals(dynamicEnvironment.get("guestHome"), null);
    assertEquals(dynamicEnvironment.get("secureFalconSiteHome"), "https://www.theladders.com/");
    assertEquals(dynamicEnvironment.get("secureGuestSiteHome"), "https://www.theladders.com/");

  }

  @Test
  public void isSecureisNotLoggedIn()
  {

    boolean isSecure = true;
    boolean loggedInUser = false;
    dynamicEnvironment = filter.getEnvironment(isSecure, loggedInUser);

    assertEquals(dynamicEnvironment.get("autoProtoHome"), "https://www.theladders.com/");
    assertEquals(dynamicEnvironment.get("secureFalconSiteHome"), "https://www.theladders.com/");
    assertEquals(dynamicEnvironment.get("memberHome"), null);
    assertEquals(dynamicEnvironment.get("memberSiteHome"), "http://www.theladders.com/member/");
    assertEquals(dynamicEnvironment.get("home"), "http://www.theladders.com/");

    assertEquals(dynamicEnvironment.get("guestSiteHome"), "http://www.theladders.com/");
    assertEquals(dynamicEnvironment.get("guestHome"), null);
    assertEquals(dynamicEnvironment.get("secureGuestSiteHome"), "https://www.theladders.com/");
    assertEquals(dynamicEnvironment.get("secureHome"), "https://www.theladders.com/");
    assertEquals(dynamicEnvironment.get("secureMemberSiteHome"), "https://www.theladders.com/member/");
    assertEquals(dynamicEnvironment.get("falconSiteHome"), "http://www.theladders.com/");

    assertEquals(dynamicEnvironment.get("isSSL"), "true");

    assertEquals(dynamicEnvironment.get("landingCss"), null);
    assertEquals(dynamicEnvironment.get("landingImages"), null);
    assertEquals(dynamicEnvironment.get("opalCss"), null);
    assertEquals(dynamicEnvironment.get("images"), null);
    assertEquals(dynamicEnvironment.get("seoCss"), null);
    assertEquals(dynamicEnvironment.get("landingFlash"), null);
    assertEquals(dynamicEnvironment.get("css"), null);
    assertEquals(dynamicEnvironment.get("staticBase"), null);
    assertEquals(dynamicEnvironment.get("flash"), null);
    assertEquals(dynamicEnvironment.get("widgets"), null);
    assertEquals(dynamicEnvironment.get("seoImages"), null);
    assertEquals(dynamicEnvironment.get("js"), null);
    assertEquals(dynamicEnvironment.get("opalImages"), null);
  }

  @Test
  public void isNotSecureisLoggedIn()
  {

    boolean isSecure = false;
    boolean loggedInUser = true;
    dynamicEnvironment = filter.getEnvironment(isSecure, loggedInUser);

    // SECURE
    assertEquals(dynamicEnvironment.get("secureMemberSiteHome"), "https://www.theladders.com/member/");
    assertEquals(dynamicEnvironment.get("secureHome"), "https://www.theladders.com/member/");
    assertEquals(dynamicEnvironment.get("secureFalconSiteHome"), "https://www.theladders.com/");
    assertEquals(dynamicEnvironment.get("secureGuestSiteHome"), "https://www.theladders.com/");

    // HOMES
    assertEquals(dynamicEnvironment.get("home"), "http://www.theladders.com/member/");
    assertEquals(dynamicEnvironment.get("memberSiteHome"), "http://www.theladders.com/member/");
    assertEquals(dynamicEnvironment.get("guestSiteHome"), "http://www.theladders.com/");
    assertEquals(dynamicEnvironment.get("falconSiteHome"), "http://www.theladders.com/");

    assertEquals(dynamicEnvironment.get("autoProtoHome"), "http://www.theladders.com/member/");
    assertEquals(dynamicEnvironment.get("guestHome"), null);
    assertEquals(dynamicEnvironment.get("memberHome"), null);

    // SSL
    assertEquals(dynamicEnvironment.get("isSSL"), "true");

  }
}
