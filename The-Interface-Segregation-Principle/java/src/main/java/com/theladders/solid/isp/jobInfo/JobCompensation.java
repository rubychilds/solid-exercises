package com.theladders.solid.isp.jobInfo;

public interface JobCompensation
{
  
  String getCompensation();

  /**
   * Get this job's compensationSalary (text).
   *
   * @return compensationSalary for this job.
   */
  String getCompensationSalary();

  /**
   * Get this job's compensationBonus (text).
   *
   * @return compensationBonus for this job.
   */
  String getCompensationBonus();

  /**
   * Get this job's compensationOther (text).
   *
   * @return compensationOther for this job.
   */
  String getCompensationOther();

}
