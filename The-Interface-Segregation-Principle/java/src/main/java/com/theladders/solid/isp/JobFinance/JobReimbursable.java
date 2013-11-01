package com.theladders.solid.isp.JobFinance;

public class JobReimbursable implements JobFinance
{
  private boolean reimbursable = false;
  
  public boolean isReimbursable()
  {
    return reimbursable;
  }

  public void setReimbursable(boolean reimbursable)
  {
    this.reimbursable = reimbursable;
  }
}
