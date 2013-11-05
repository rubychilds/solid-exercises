package com.theladders.solid.isp.jobInfo;

import java.util.Collection;
import java.util.List;

import com.theladders.solid.isp.oldjob.stubs.Discipline;
import com.theladders.solid.isp.oldjob.stubs.Industry;
import com.theladders.solid.isp.oldjob.stubs.JobFunction;
import com.theladders.solid.isp.oldjob.stubs.Sector;

public interface JobAreas
{
  /**
   * Retrieves a list of disciplines for this job.
   * 
   * @return List of Disciplines
   */
  List<Discipline> getDisciplines();

  /**
   * Get the Industry for this job.
   * 
   * @return the Industry for this job.
   */
  Industry getIndustry();

  /**
   * Get the sector for this job.
   * 
   * @return the sector for this job.
   */
  Sector getSector();
  
  Collection<JobFunction> getJobFunctions();

}
