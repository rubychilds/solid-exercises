package com.theladders.solid.original;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.theladders.solid.original.EnvSetupFilter;
import com.theladders.solid.original.Environment;

public class EnvironmentTest
{

  /*
  @Test
  public void testWithEmptyParameters()
  {
    Map<String, String> keyMap = new HashMap<String, String>();
    Environment environment = new Environment();

    DynamicEnvironment dynamicEnvironment = new DynamicEnvironment(environment, keyMap);

    // both null objects - checking this
    Set<Map.Entry<Object, Object>> entrySet = dynamicEnvironment.entrySet();
    assertTrue(entrySet.isEmpty());

    Set<Object> keySet = dynamicEnvironment.keySet();
    assertTrue(keySet.isEmpty());
  }

  @Test
  public void testWithOneItemInEnvironment()
  {
    Map<String, String> keyMap = new HashMap<String, String>();
    Environment environment = new Environment();

    String key = "ruby";
    String value = "hello";

    environment.put(key, value);

    DynamicEnvironment dynamicEnvironment = new DynamicEnvironment(environment, keyMap);

    Set<Map.Entry<Object, Object>> entrySet = dynamicEnvironment.entrySet();
    assertEquals(entrySet.size(), 1);

    Set<Object> keySet = dynamicEnvironment.keySet();
    assertEquals(keySet.size(), 1);

    Object returnedValue = dynamicEnvironment.get(key);
    assertEquals(returnedValue, value);
  }

  @Test
  public void testWithOneItemInBase()
  {
    Map<String, String> keyMap = new HashMap<String, String>();
    Environment environment = new Environment();

    String key = "ruby";
    String value = "hello";

    keyMap.put(key, value);

    DynamicEnvironment dynamicEnvironment = new DynamicEnvironment(environment, keyMap);

    Set<Map.Entry<Object, Object>> entrySet = dynamicEnvironment.entrySet();
    assertTrue(entrySet.isEmpty());

    Set<Object> keySet = dynamicEnvironment.keySet();
    assertEquals(keySet.size(), 1);

    Object returnedValue = dynamicEnvironment.get(key);
    assertEquals(returnedValue, null);
  }


  */
  private EnvSetupFilter  filter;
  private Environment environment;
  private static final String hostName = "www.theladders.com/";
  
  public static final String GUEST_SITE_HOME                  = "guestSiteHome";
  public static final String SECURE_GUEST_SITE_HOME           = "secureGuestSiteHome";
  public static final String FALCON_SITE_HOME                 = "falconSiteHome";
  public static final String SECURE_FALCON_SITE_HOME          = "secureFalconSiteHome";
  public static final String MEMBER_SITE_HOME                 = "memberSiteHome";
  public static final String SECURE_MEMBER_SITE_HOME          = "secureMemberSiteHome";
  public static final String MEMBER_PATH_PREFIX               = "member/";
  
  // SSL
  /*
  @Test 
  public void testSSLget()
  {
   Object value = dynamicEnvironment.get("isSSL");
   assertTrue(value.equals("true"));
  }
  
  // HOME
  @Test 
  public void testhome()
  {
   Object value = dynamicEnvironment.get("home");
   assertTrue(value.equals("https://" + hostName)); 
  }
  
  @Test 
  public void testSecureHome()
  {
   Object value = dynamicEnvironment.get("secureHome");
   assertTrue(value.equals("https://" + hostName)); 
  }
  
  // GUEST
  @Test 
  public void testSecureGuestSiteHome()
  {
   Object value = dynamicEnvironment.get(SECURE_GUEST_SITE_HOME);
   assertTrue(value.equals("https://" + hostName)); 
  }
  
  @Test 
  public void testGuestSiteHome()
  {
   Object value = dynamicEnvironment.get(GUEST_SITE_HOME);
   assertTrue(value.equals("https://" + hostName)); 
  }
  
  // FALCON
  @Test 
  public void testFalconSiteHome()
  {
   Object value = dynamicEnvironment.get(FALCON_SITE_HOME);
   assertTrue(value.equals("https://" + hostName)); 
  }
  
  @Test 
  public void testFalconSecureSiteHome()
  {
   Object value = dynamicEnvironment.get(SECURE_FALCON_SITE_HOME);
   assertTrue(value.equals("https://" + hostName)); 
  }
  
  // MEMBER
  @Test 
  public void testMemberSiteHome()
  {
   Object value = dynamicEnvironment.get(MEMBER_SITE_HOME);
   assertTrue(value.equals("https://" + hostName + MEMBER_PATH_PREFIX)); 
  }
  
  @Test 
  public void testMemberSecureSiteHome()
  {
   Object value = dynamicEnvironment.get(SECURE_MEMBER_SITE_HOME);
   assertTrue(value.equals("https://" + hostName + MEMBER_PATH_PREFIX)); 
  }
  */
  
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

    environment = filter.getEnvironment(isSecure, loggedInUser);
    Set<Object> keySet = environment.keySet();
    
    assertEquals(environment.get("autoProtoHome"), "https://www.theladders.com/member/");
    assertEquals(environment.get("secureFalconSiteHome"), "https://www.theladders.com/");
    assertEquals(environment.get("memberHome"), (null));
    assertEquals(environment.get("memberSiteHome"), "http://www.theladders.com/member/");
    assertEquals(environment.get("guestSiteHome"), "http://www.theladders.com/");
    assertEquals(environment.get("secureGuestSiteHome"), "https://www.theladders.com/");
    assertEquals(environment.get("secureHome"), "https://www.theladders.com/member/");
    assertEquals(environment.get("secureMemberSiteHome"), "https://www.theladders.com/member/");
    assertEquals(environment.get("falconSiteHome"), "http://www.theladders.com/");
    assertEquals(environment.get("guestHome"), null);
    
    assertEquals(environment.get("staticBase"), null );
    assertEquals(environment.get("flash"), null);
    assertEquals(environment.get("widgets"), null);
    assertEquals(environment.get("home"), ("http://www.theladders.com/member/"));
    assertEquals(environment.get("js"), null);
    assertEquals(environment.get("opalImages"), (null));

    assertEquals(environment.get("isSSL"), "true");
    assertEquals(environment.get("seoImages"), null);

    assertEquals(environment.get("landingCss"), (null));

    assertEquals(environment.get("landingImages"), (null));
    assertEquals(environment.get("opalCss"), (null));

    assertEquals(environment.get("images"), null);
    assertEquals(environment.get("seoCss"), null);

    assertEquals(environment.get("landingFlash"), null);
    assertEquals(environment.get("css"), null);
  }



  @Test 
  public void isNotSecureisNotLoggedIn(){
    
    boolean isSecure = false;
    boolean loggedInUser = false;
    
    environment = filter.getEnvironment(isSecure, loggedInUser);

    
    assertEquals(environment.get("secureHome"), "https://www.theladders.com/");
    assertEquals(environment.get("home"), "http://www.theladders.com/");
    assertEquals(environment.get("memberHome"), null);
    assertEquals(environment.get("memberSiteHome"), "http://www.theladders.com/member/");
    assertEquals(environment.get("secureMemberSiteHome"), "https://www.theladders.com/member/");
    assertEquals(environment.get("guestSiteHome"), "http://www.theladders.com/");
    assertEquals(environment.get("falconSiteHome"), "http://www.theladders.com/");
    assertEquals(environment.get("isSSL"), "true");
    assertEquals(environment.get("autoProtoHome"), "http://www.theladders.com/");
    assertEquals(environment.get("guestHome"), null);
    assertEquals(environment.get("secureFalconSiteHome"), "https://www.theladders.com/");
    assertEquals(environment.get("secureGuestSiteHome"), "https://www.theladders.com/");
    
  }
  

  
  @Test 
  public void isSecureisNotLoggedIn(){
    
    boolean isSecure = true;
    boolean loggedInUser = false;
    environment = filter.getEnvironment(isSecure, loggedInUser);


    assertEquals(environment.get("autoProtoHome"), "https://www.theladders.com/");
    assertEquals(environment.get("secureFalconSiteHome"), "https://www.theladders.com/");
    assertEquals(environment.get("memberHome"), null);
    assertEquals(environment.get("memberSiteHome"), "http://www.theladders.com/member/");
    assertEquals(environment.get("home"), "http://www.theladders.com/");
    

    assertEquals(environment.get("guestSiteHome"), "http://www.theladders.com/");
    assertEquals(environment.get("guestHome"), null);
    assertEquals(environment.get("secureGuestSiteHome"), "https://www.theladders.com/");
    assertEquals(environment.get("secureHome"), "https://www.theladders.com/");
    assertEquals(environment.get("secureMemberSiteHome"), "https://www.theladders.com/member/");
    assertEquals(environment.get("falconSiteHome"), "http://www.theladders.com/");
    
    assertEquals(environment.get("isSSL"), "true");
    
    assertEquals(environment.get("landingCss"), null);
    assertEquals(environment.get("landingImages"), null);
    assertEquals(environment.get("opalCss"), null);
    assertEquals(environment.get("images"), null);
    assertEquals(environment.get("seoCss"), null);
    assertEquals(environment.get("landingFlash"), null);
    assertEquals(environment.get("css"), null);
    assertEquals(environment.get("staticBase"), null);
    assertEquals(environment.get("flash"), null);
    assertEquals(environment.get("widgets"), null);
    assertEquals(environment.get("seoImages"), null);
    assertEquals(environment.get("js"), null);
    assertEquals(environment.get("opalImages"), null);
  }
  
  @Test 
  public void isNotSecureisLoggedIn(){
    
    boolean isSecure = false;
    boolean loggedInUser = true;
    environment = filter.getEnvironment(isSecure, loggedInUser);

    // SECURE
    assertEquals(environment.get("secureMemberSiteHome"), "https://www.theladders.com/member/");
    assertEquals(environment.get("secureHome"), "https://www.theladders.com/member/");
    assertEquals(environment.get("secureFalconSiteHome"), "https://www.theladders.com/");
    assertEquals(environment.get("secureGuestSiteHome"), "https://www.theladders.com/");
    
    // HOMES
    assertEquals(environment.get("home"), "http://www.theladders.com/member/");
    assertEquals(environment.get("memberSiteHome"), "http://www.theladders.com/member/");
    assertEquals(environment.get("guestSiteHome"), "http://www.theladders.com/");
    assertEquals(environment.get("falconSiteHome"), "http://www.theladders.com/");
    
    assertEquals(environment.get("autoProtoHome"), "http://www.theladders.com/member/");
    assertEquals(environment.get("guestHome"), null);
    assertEquals(environment.get("memberHome"), null);
    
    // SSL
    assertEquals(environment.get("isSSL"), "true");


  }
}
