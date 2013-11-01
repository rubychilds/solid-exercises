package com.theladders.solid.isp.oldjob;

import java.util.Date;

public class UpdateTime
{
  private Date    updateTime   = null;
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
