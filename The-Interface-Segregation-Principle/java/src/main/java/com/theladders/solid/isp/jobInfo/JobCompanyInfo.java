package com.theladders.solid.isp.jobInfo;

public class JobCompanyInfo implements JobCompany
{
  private String  companyName;
  private Integer companySize;

  public JobCompanyInfo(int companySize,
                     String companyName)
  {
    this.companySize = companySize;
    this.companyName = companyName;
  }

  public String getCompany()
  {
    return companyName;
  }

  public void setCompany(String company)
  {
    this.companyName = company;
  }

  public Integer getCompanySize()
  {
    return companySize;
  }

  public void setCompanySize(Integer companySize)
  {
    this.companySize = companySize;
  }
}
