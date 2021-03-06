package com.theladders.solid.isp.jobInfo;

public class JobIdentifiersInfo implements JobIdentifiers
{
  private int oldJobId    = 0;
  private int jobId       = 0;
  private int parentJobId = 0;

  public int getOldJobId()
  {
    return oldJobId;
  }

  public void setOldJobId(int oldJobId)
  {
    this.oldJobId = oldJobId;
  }

  public Integer getParentJobId()
  {
    return parentJobId;
  }

  public void setParentJobId(int parentJobId)
  {
    this.parentJobId = parentJobId;
  }

  public int getJobId()
  {
    return jobId;
  }

  public void setJobId()
  {
    this.jobId = jobId;
  }

}
