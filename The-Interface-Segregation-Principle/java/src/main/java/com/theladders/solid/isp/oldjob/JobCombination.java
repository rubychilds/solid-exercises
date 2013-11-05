package com.theladders.solid.isp.oldjob;

import java.util.Collection;
import java.util.Date;

import com.theladders.solid.isp.jobInfo.Company;
import com.theladders.solid.isp.jobInfo.CompanyInfo;
import com.theladders.solid.isp.jobInfo.Compensation;
import com.theladders.solid.isp.jobInfo.CompensationInfo;
import com.theladders.solid.isp.jobInfo.Description;
import com.theladders.solid.isp.jobInfo.DescriptionInfo;
import com.theladders.solid.isp.jobInfo.Geography;
import com.theladders.solid.isp.jobInfo.GeographyInfo;
import com.theladders.solid.isp.jobInfo.Identifiers;
import com.theladders.solid.isp.jobInfo.IdentifiersInfo;
import com.theladders.solid.isp.jobInfo.JobAreasInfo;
import com.theladders.solid.isp.jobInfo.JobPost;
import com.theladders.solid.isp.jobInfo.JobPostInfo;
import com.theladders.solid.isp.jobInfo.Publication;
import com.theladders.solid.isp.jobInfo.PublicationInfo;
import com.theladders.solid.isp.jobInfo.Requirements;
import com.theladders.solid.isp.jobInfo.RequirementsInfo;
import com.theladders.solid.isp.jobInfo.Visibility;
import com.theladders.solid.isp.jobInfo.VisibilityInfo;
import com.theladders.solid.isp.oldjob.stubs.City;
import com.theladders.solid.isp.oldjob.stubs.Experience;
import com.theladders.solid.isp.oldjob.stubs.JobFunction;
import com.theladders.solid.isp.oldjob.stubs.JobStatus;
import com.theladders.solid.isp.oldjob.stubs.PositionLevel;
import com.theladders.solid.isp.oldjob.stubs.Region;
import com.theladders.solid.isp.oldjob.stubs.Sector;

public class JobCombination extends JobImpl implements Company, Geography, Compensation, Visibility, Publication,
    Description, Identifiers, JobPost, Requirements
{
  private CompanyInfo      companyInfo;
  private GeographyInfo    geographyInfo;
  private CompensationInfo compensationInfo;
  private VisibilityInfo   visibilityInfo;
  private PublicationInfo  publicationInfo;
  private DescriptionInfo  descriptionInfo;
  private IdentifiersInfo  identifiersInfo;
  private JobPostInfo      jobPostInfo;
  private RequirementsInfo requirementsInfo;
  private JobAreasInfo     jobAreasInfo;
  private JobRelocationPaidInfo jobRelocationPaidInfo;

  public JobCombination(CompanyInfo companyInfo,
                        GeographyInfo geographyInfo,
                        CompensationInfo compensationInfo,
                        VisibilityInfo visibilityInfo,
                        PublicationInfo publicationInfo,
                        DescriptionInfo descriptionInfo,
                        IdentifiersInfo identifiersInfo,
                        JobPostInfo jobPostInfo,
                        RequirementsInfo requirementsInfo,
                        JobAreasInfo jobAreasInfo,
                        JobRelocationPaidInfo jobRelocationPaidInfo)
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

  public int getJobSiteId()
  {
    return identifiersInfo.getJobSiteId();
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
