package com.theladders.solid.isp.oldjob;

public class JobComposite extends JobImpl
{
  
  private Location location;
  public JobComposite(Location location){
    this.location = location;
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
