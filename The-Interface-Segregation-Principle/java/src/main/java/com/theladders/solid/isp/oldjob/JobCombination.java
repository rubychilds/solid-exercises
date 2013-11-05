package com.theladders.solid.isp.oldjob;

import java.util.Collection;
import java.util.Date;

import com.theladders.solid.isp.jobInfo.JobCompany;
import com.theladders.solid.isp.jobInfo.JobCompanyInfo;
import com.theladders.solid.isp.jobInfo.JobCompensation;
import com.theladders.solid.isp.jobInfo.JobCompensationInfo;
import com.theladders.solid.isp.jobInfo.JobDescription;
import com.theladders.solid.isp.jobInfo.JobDescriptionInfo;
import com.theladders.solid.isp.jobInfo.JobGeography;
import com.theladders.solid.isp.jobInfo.JobGeographyInfo;
import com.theladders.solid.isp.jobInfo.JobIdentifiers;
import com.theladders.solid.isp.jobInfo.JobIdentifiersInfo;
import com.theladders.solid.isp.jobInfo.JobAreasInfo;
import com.theladders.solid.isp.jobInfo.JobPost;
import com.theladders.solid.isp.jobInfo.JobPostInfo;
import com.theladders.solid.isp.jobInfo.JobPublication;
import com.theladders.solid.isp.jobInfo.JobPublicationInfo;
import com.theladders.solid.isp.jobInfo.JobRequirements;
import com.theladders.solid.isp.jobInfo.JobRequirementsInfo;
import com.theladders.solid.isp.jobInfo.JobSite;
import com.theladders.solid.isp.jobInfo.JobSiteInfo;
import com.theladders.solid.isp.jobInfo.JobVisibility;
import com.theladders.solid.isp.jobInfo.JobVisibilityInfo;
import com.theladders.solid.isp.oldjob.stubs.City;
import com.theladders.solid.isp.oldjob.stubs.Experience;
import com.theladders.solid.isp.oldjob.stubs.JobFunction;
import com.theladders.solid.isp.oldjob.stubs.JobStatus;
import com.theladders.solid.isp.oldjob.stubs.PositionLevel;
import com.theladders.solid.isp.oldjob.stubs.Region;
import com.theladders.solid.isp.oldjob.stubs.Sector;

public class JobCombination extends JobImpl implements JobCompany, JobGeography, JobCompensation, JobVisibility, JobPublication,
    JobDescription, JobIdentifiers, JobPost, JobRequirements, JobSite
{
  private JobCompanyInfo      companyInfo;
  private JobGeographyInfo    geographyInfo;
  private JobCompensationInfo compensationInfo;
  private JobVisibilityInfo   visibilityInfo;
  private JobPublicationInfo  publicationInfo;
  private JobDescriptionInfo  descriptionInfo;
  private JobIdentifiersInfo  identifiersInfo;
  private JobPostInfo      jobPostInfo;
  private JobRequirementsInfo requirementsInfo;
  private JobAreasInfo     jobAreasInfo;
  private JobRelocationPaidInfo jobRelocationPaidInfo;
  private JobSiteInfo jobSiteInfo;

  public JobCombination(JobCompanyInfo companyInfo,
                        JobGeographyInfo geographyInfo,
                        JobCompensationInfo compensationInfo,
                        JobVisibilityInfo visibilityInfo,
                        JobPublicationInfo publicationInfo,
                        JobDescriptionInfo descriptionInfo,
                        JobIdentifiersInfo identifiersInfo,
                        JobPostInfo jobPostInfo,
                        JobRequirementsInfo requirementsInfo,
                        JobAreasInfo jobAreasInfo,
                        JobRelocationPaidInfo jobRelocationPaidInfo,
                        JobSiteInfo jobSiteInfo)
  {
    this.geographyInfo = geographyInfo;
    this.companyInfo = companyInfo;
    this.compensationInfo = compensationInfo;
    this.visibilityInfo = visibilityInfo;
    this.publicationInfo = publicationInfo;
    this.descriptionInfo = descriptionInfo;
    this.jobPostInfo = jobPostInfo;
    this.requirementsInfo = requirementsInfo;
    this.jobAreasInfo = jobAreasInfo;
    this.jobRelocationPaidInfo = jobRelocationPaidInfo;
    this.jobSiteInfo = jobSiteInfo;
  }

  public boolean isJobReq()
  {
    // TODO Auto-generated method stub
    return false;
  }

  public String getDescription()
  {
    // TODO Auto-generated method stub
    return null;
  }

  // GEOGRAPHY
  public String getLocation()
  {
    return geographyInfo.getLocation();
  }

  public void setLocation(String location)
  {
    geographyInfo.setLocation(location);
  }

  public void setRegion(Region region)
  {
    geographyInfo.setRegion(region);
  }

  public Region getRegion()
  {
    return geographyInfo.getRegion();
  }

  public City getCity()
  {
    return geographyInfo.getCity();
  }

  // COMPANY
  public String getCompany()
  {
    return companyInfo.getCompany();
  }

  public void setCompany(String company)
  {
    companyInfo.setCompany(company);
  }

  public Integer getCompanySize()
  {
    return companyInfo.getCompanySize();
  }

  public void setCompanySize(Integer companySize)
  {
    companyInfo.setCompanySize(companySize);
  }

  // COMPENSATION
  public String getCompensation()
  {
    return compensationInfo.getCompensation();
  }

  public String getCompensationSalary()
  {
    return compensationInfo.getCompensationSalary();
  }

  public String getCompensationBonus()
  {
    return compensationInfo.getCompensationBonus();
  }

  public String getCompensationOther()
  {
    return compensationInfo.getCompensationOther();
  }

  // VISIBILITY
  public boolean isAnonymous()
  {
    return visibilityInfo.isAnonymous();
  }

  public boolean isConfidential()
  {
    return visibilityInfo.isConfidential();
  }

  public boolean isExclusive()
  {
    return visibilityInfo.isExclusive();
  }

  // PUBLICATION
  public Date getPublicationDate()
  {
    return publicationInfo.getPublicationDate();
  }

  public String getEditorNote()
  {
    return publicationInfo.getEditorNote();
  }

  public Date getOriginalPublicationDate()
  {
    return publicationInfo.getOriginalPublicationDate();
  }

  // DESCRIPTION
  public String getShortJobDescription()
  {
    return descriptionInfo.getShortJobDescription();
  }

  public String getJobDescription()
  {
    return descriptionInfo.getJobDescription();
  }

  // IDENTIFIERS

  public int getJobId()
  {
    return identifiersInfo.getJobId();
  }

  public Integer getParentJobId()
  {
    return identifiersInfo.getParentJobId();
  }

  // JOB POST

  public boolean hasStatus(JobStatus status)
  {
    // TODO Auto-generated method stub
    return false;
  }

  public boolean isDeleted()
  {
    return jobPostInfo.isDeleted();
  }

  public boolean isExpired()
  {
    return jobPostInfo.isExpired();
  }

  public boolean isFilled()
  {
    return jobPostInfo.isFilled();
  }
  

  public Date getEntryDate()
  {
    return jobPostInfo.getEntryDate();
  }

  // REQUIREMENTS

  public Experience getExperience()
  {
    return requirementsInfo.getExperience();
  }
  
  // RELOCATION

  public boolean isReimbursable()
  {
    return jobRelocationPaidInfo.isReimbursable();
  }
  
  // JOBSITE
  public int getJobSiteId()
  {
    return jobSiteInfo.getJobSiteId();
  }
  

  /************* UNIMPLEMENTED *****************/

  public PositionLevel getPositionLevel()
  {
    // TODO Auto-generated method stub
    return null;
  }

  public String getUrl()
  {
    // TODO Auto-generated method stub
    return null;
  }

  public boolean isMarketing()
  {
    // TODO Auto-generated method stub
    return false;
  }

  public Collection<JobFunction> getJobFunctions()
  {
    // TODO Auto-generated method stub
    return null;
  }


}
