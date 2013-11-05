package com.theladders.solid.isp.jobInfo;

import com.theladders.solid.isp.oldjob.stubs.City;
import com.theladders.solid.isp.oldjob.stubs.Region;

public class JobGeographyInfo implements JobGeography
{
  private String location;
  private Region region;
  private City city;
  
  
  public JobGeographyInfo(String location, Region region, City city)
  {
    this.location = location;
    this.region = region;
    this.city = city;
  }

  public String getLocation()
  {
    return location;
  }

  public void setLocation(String location)
  {
    this.location = location;
  }

  public void setRegion(Region region)
  {
    this.region = region;
  }

  public Region getRegion()
  {
    return region;
  }

  public City getCity()
  {
    return city;
  }
  
  public void setCity(City city)
  {
    this.city = city;
  }
}
