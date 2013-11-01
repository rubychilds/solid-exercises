package com.theladders.solid.isp.oldjob;

import java.util.Date;
import java.util.List;

import com.theladders.solid.isp.oldjob.stubs.Discipline;
import com.theladders.solid.isp.oldjob.stubs.Experience;
import com.theladders.solid.isp.oldjob.stubs.Industry;
import com.theladders.solid.isp.oldjob.stubs.Region;

public abstract class JobImpl implements JobCommon
{

  private Date   entryDate;
  private String reportsTo;

  public Date getEntryDate()
  {
    return entryDate;
  }

  public void setEntryDate(Date entryDate)
  {
    this.entryDate = entryDate;
  }

  public String getReportsTo()
  {
    return reportsTo;
  }

  public void setReportsTo(String reportsTo)
  {
    this.reportsTo = reportsTo;
  }

}
