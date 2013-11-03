package com.theladders.solid.isp.oldjob;

public class JobCombination extends JobImpl
{
  private Location location;
  
  public JobCombination(Location location){
    this.location = location;
  }

  @Override
  public String getLocation()
  {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public boolean isJobReq()
  {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public String getDescription()
  {
    // TODO Auto-generated method stub
    return null;
  }
}
