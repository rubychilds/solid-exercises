package com.theladders.solid.isp.jobInfo;

import java.util.Date;

import com.theladders.solid.isp.oldjob.stubs.JobStatus;

public class JobPostInfo implements JobPost
{
  private boolean deleted = false;
  private boolean expired = false;
  private boolean filled  = false;
  
  private Date entryDate;

  public boolean hasStatus(JobStatus status)
  {
    return false;
  }

  public boolean isDeleted()
  {
    return deleted;
  }

  public void setDeleted(boolean deleted)
  {
    this.deleted = deleted;
  }

  public boolean isExpired()
  {
    return expired;
  }

  public void setExpired(boolean expired)
  {
    this.expired = expired;
  }

  public boolean isFilled()
  {
    return filled;
  }

  public void setFilled(boolean filled)
  {
    this.filled = filled;
  }

  public Date getEntryDate()
  {
    return entryDate;
  }

  public void setEntryDate(Date entryDate)
  {
    this.entryDate = entryDate;
  }

}
