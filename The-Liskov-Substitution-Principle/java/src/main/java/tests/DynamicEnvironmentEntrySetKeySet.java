package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

import com.theladders.solid.lsp.DynamicEnvironment;
import com.theladders.solid.lsp.Environment;

public class DynamicEnvironmentEntrySetKeySet
{

  @Test
  public void testWithEmptyParameters()
  {
    Map<String, String> keyMap = new HashMap<String, String>();
    Environment environment = new Environment();
    DynamicEnvironment dynamicEnvironment = new DynamicEnvironment(environment, keyMap);
    
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
  
  @Test
  public void testWithItemsInBaseandKeyMap()
  {
    Map<String, String> keyMap = new HashMap<String, String>();
    Environment environment = new Environment();
    
    String key1 = "ruby";
    String value1 = "hello";
    keyMap.put(key1, value1);
    
    String key2 = "boo";
    String value2 = "brooklyn";
    environment.put(key2, value2);
    
    DynamicEnvironment dynamicEnvironment = new DynamicEnvironment(environment, keyMap);
   
    Set<Map.Entry<Object, Object>> entrySet = dynamicEnvironment.entrySet();
    assertEquals(entrySet.size(), 1);
    
    Set<Object> keySet = dynamicEnvironment.keySet();
    assertEquals(keySet.size(), 2);
    
    Object returnedValue = dynamicEnvironment.get(key1);
    System.out.println(returnedValue);
    assertEquals(returnedValue, null);
    
    returnedValue = dynamicEnvironment.get(key2);
    assertEquals(returnedValue, value2);

  }
  
  @Test
  public void testWithNoElementsinBaseKeyMap()
  {
    Map<String, String> keyMap = new HashMap<String, String>();
    Environment environment = new Environment();
    
    
    DynamicEnvironment dynamicEnvironment = new DynamicEnvironment(environment, keyMap);
    
    String key1 = "ruby";
    String value1 = "hello";
    dynamicEnvironment.put(key1, value1);
    
    String key2 = "boo";
    String value2 = "brooklyn";
    dynamicEnvironment.put(key2, value2);

    Set<Map.Entry<Object, Object>> entrySet = dynamicEnvironment.entrySet();
    assertEquals(entrySet.size(), 2);
    
    Set<Object> keySet = dynamicEnvironment.keySet();
    assertEquals(keySet.size(), 2);
    
    Object returnedValue = dynamicEnvironment.get(key1);
    assertEquals(returnedValue, value1);
    
    returnedValue = dynamicEnvironment.get(key2);
    assertEquals(returnedValue, value2);
  }
  

}
