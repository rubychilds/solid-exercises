package com.theladders.solid.isp.jobInfo;

import java.util.List;

import com.theladders.solid.isp.oldjob.stubs.Discipline;
import com.theladders.solid.isp.oldjob.stubs.Industry;
import com.theladders.solid.isp.oldjob.stubs.Sector;

public class JobAreasInfo implements JobAreas
{

  private List<Discipline> disciplines;
  private Industry         industry;
  private Sector        sector;

  public List<Discipline> getDisciplines()
  {
    return disciplines;
  }

  public void setDisciplines(List<Discipline> disciplines)
  {
    this.disciplines = disciplines;
  }

  public Industry getIndustry()
  {
    return industry;
  }

  public void setIndustry(Industry industry)
  {
    this.industry = industry;
  }
  
  public Sector getSector()
  {
    return sector;
  }
  
  public void setSector()
  {
    this.sector = sector;
  }
}
