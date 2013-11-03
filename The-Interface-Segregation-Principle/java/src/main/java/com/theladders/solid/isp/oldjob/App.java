package com.theladders.solid.isp.oldjob;

public class App
{

  public static void main(String[] args)
  {
    LocationInfo location = new LocationInfo();
    CompanyInfo company = new CompanyInfo();
    JobCombination job = new JobCombination(location, company);

  }

}
