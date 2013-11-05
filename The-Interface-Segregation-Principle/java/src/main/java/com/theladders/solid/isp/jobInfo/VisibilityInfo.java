package com.theladders.solid.isp.jobInfo;

public class VisibilityInfo
{
  private boolean anonymous    = false;
  private boolean confidential = false;
  private boolean exclusive    = false;

  public VisibilityInfo(boolean anonymous,
                        boolean confidential,
                        boolean exclusive)
  {
    this.anonymous = anonymous;
    this.confidential = confidential;
    this.exclusive = exclusive;
  }

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
