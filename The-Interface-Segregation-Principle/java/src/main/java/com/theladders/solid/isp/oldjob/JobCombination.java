package com.theladders.solid.isp.oldjob;

public class JobCombination extends JobImpl implements Company
{
  private CompanyInfo      companyInfo;
  private LocationInfo     locationInfo;
  private CompensationInfo compensationInfo;

  public JobCombination(CompanyInfo companyInfo,
                        LocationInfo locationInfo,
                        CompensationInfo compensationInfo)
  {
    this.locationInfo = locationInfo;
    this.companyInfo = companyInfo;
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

  // LOCATION
  public String getLocation()
  {
    return locationInfo.getLocation();
  }

  public void setLocation(String location)
  {
    locationInfo.setLocation(location);
  }

  // COMPANY
  public String getCompany()
  {
    return companyInfo.getCompany();
  }

  public void setCompany(String company)
  {
    companyInfo.setCompany(company);
  }

  public Integer getCompanySize()
  {
    return companyInfo.getCompanySize();
  }

  public void setCompanySize(Integer companySize)
  {
    companyInfo.setCompanySize(companySize);
  }

  // COMPENSATION
  public String getCompensation()
  {
    return compensationInfo.getCompensation();
  }

  public String getCompensationSalary()
  {
    return compensationInfo.getCompensationSalary();
  }

  public String getCompensationBonus()
  {
    return compensationInfo.getCompensationBonus();
  }

  public String getCompensationOther()
  {
    return compensationInfo.getCompensationOther();
  }

}
