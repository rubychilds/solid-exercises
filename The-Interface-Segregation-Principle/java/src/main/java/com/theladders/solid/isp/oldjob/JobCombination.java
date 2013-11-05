package com.theladders.solid.isp.oldjob;

public class JobCombination extends JobImpl
{
  private Location location;
  private Company company;
  
  public JobCombination(Location location, Company company){
    this.location = location;
    this.company = company;
  }

  public boolean isJobReq()
  {
    // TODO Auto-generated method stub
    return false;
  }

  public String getDescription()
  {
    // TODO Auto-generated method stub
    return null;
  }
}
