package com.theladders.solid.isp.jobInfo;

public class CompensationInfo
{
  private String compensation;
  private String compensationBonus;
  private String compensationOther;
  private String compensationSalary;
 
  public CompensationInfo(String compensation, String compensationBonus, String compensationOther, String compensationSalar)
  {
    this.compensation = compensation;
    this.compensationBonus = compensationBonus;
    this.compensationOther = compensationOther;
    this.compensationSalary = compensationSalary;
  }
  
  public String getCompensation()
  {
    return compensation;
  }

  public void setCompensation(String compensation)
  {
    this.compensation = compensation;
  }

  public String getCompensationBonus()
  {
    return compensationBonus;
  }

  public void setCompensationBonus(String compensationBonus)
  {
    this.compensationBonus = compensationBonus;
  }

  public String getCompensationOther()
  {
    return compensationOther;
  }

  public void setCompensationOther(String compensationOther)
  {
    this.compensationOther = compensationOther;
  }

  public String getCompensationSalary()
  {
    return compensationSalary;
  }

  public void setCompensationSalary(String compensationSalary)
  {
    this.compensationSalary = compensationSalary;
  }
}
