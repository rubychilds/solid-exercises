package com.theladders.solid.isp.oldjob;

import java.util.Date;
import java.util.List;

import com.theladders.solid.isp.oldjob.stubs.Discipline;
import com.theladders.solid.isp.oldjob.stubs.Experience;
import com.theladders.solid.isp.oldjob.stubs.Industry;

public abstract class JobImpl implements Job
{

  private String reportsTo;
  private int    subscriberId = 0;
  private String title;

  private Date   updateTime   = null;

  public String getReportsTo()
  {
    return reportsTo;
  }

  public void setReportsTo(String reportsTo)
  {
    this.reportsTo = reportsTo;
  }

  public int getSubscriberId()
  {
    return subscriberId;
  }

  public void setSubscriberId(Integer subscriberId)
  {
    this.subscriberId = subscriberId;
  }

  public String getTitle()
  {
    return title;
  }

  public void setTitle(String title)
  {
    this.title = title;
  }

  /**
   * WARNING: THIS IS NOT IMPLEMENTED EVERYWHERE. I have not implemented getUpdateTime to be
   * returned from the DAO anywhere except the job search flow. This method is used by search to set
   * the update time which is used for version information.
   * 
   * @return the last time this job was updated.
   */
  public Date getUpdateTime()
  {
    return updateTime;
  }

  public void setUpdateTime(Date updateTime)
  {
    this.updateTime = updateTime;
  }
}
