package com.theladders.solid.isp.jobInfo;

public class VisibilityInfo
{
  private boolean          anonymous    = false;
  private boolean          confidential = false;
  private boolean          exclusive    = false;
  
  public boolean isAnonymous()
  {
    return anonymous;
  }

  public void setAnonymous(boolean anonymous)
  {
    this.anonymous = anonymous;
  }

  public boolean isConfidential()
  {
    return confidential;
  }

  public void setConfidential(boolean confidential)
  {
    this.confidential = confidential;
  }

  public boolean isExclusive()
  {
    return exclusive;
  }

  public void setExclusive(boolean exclusive)
  {
    this.exclusive = exclusive;
  }
}
