package com.theladders.solid.isp.oldjob;

public class LocationInfo implements Location
{
  private String location;
  
  public String getLocation()
  {
    return location;
  }

  public void setLocation(String location)
  {
    this.location = location;
  }
}