package com.theladders.solid.isp.oldjob;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import com.theladders.solid.isp.oldjob.stubs.JobFunction;
import com.theladders.solid.isp.oldjob.stubs.PositionLevel;

/**
 * Job Interface.
 */
public interface Job extends Serializable
{

  /**
   * Get the "reportsTo" field.
   * 
   * @return reportsTo
   */
  String getReportsTo();

  int getSubscriberId();

  /**
   * Get this job's title.
   * 
   * @return the title for this job.
   */
  String getTitle();

  /**
   * Is this job a JobReq? JobReqs are jobs entered into our site directly by recruiters.
   * 
   * @return true if job is a JobReq, false otherwise.
   */
  boolean isJobReq();

  boolean isReimbursable();

  /**
   * @return The last time this job was updated
   */
  Date getUpdateTime();

  PositionLevel getPositionLevel();

  /**
   * Get the URL for this job. This is only valid for external (harvested) jobs (! isJobReq).
   * 
   * @return URL for this job.
   */
  String getUrl();

  /**
   * Is this job a Marketing job? If this flag is set, basic access is allowed to this job (where
   * otherwise it would be premium) from certain landing pages.
   * 
   * @return true if this is marked for marketing, false otherwise.
   */
  // TODO: This should only ever be true for JobReq, refactor into the JobReq interface
  boolean isMarketing();

  Collection<JobFunction> getJobFunctions();
}
