package com.theladders.solid.isp.oldjob;

public class App
{

  public static void main(String[] args)
  {

    CompanyInfo company = new CompanyInfo();
    LocationInfo location = new LocationInfo();
    CompensationInfo compensationInfo = new CompensationInfo();

    JobCombination job = new JobCombination(company, location, compensationInfo);

  }

}
