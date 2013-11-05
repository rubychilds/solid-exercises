package com.theladders.solid.isp.oldjob;

import java.util.Date;

import com.theladders.solid.isp.jobInfo.CompanyInfo;
import com.theladders.solid.isp.jobInfo.CompensationInfo;
import com.theladders.solid.isp.jobInfo.DescriptionInfo;
import com.theladders.solid.isp.jobInfo.GeographyInfo;
import com.theladders.solid.isp.jobInfo.IdentifiersInfo;
import com.theladders.solid.isp.jobInfo.JobAreasInfo;
import com.theladders.solid.isp.jobInfo.JobPostInfo;
import com.theladders.solid.isp.jobInfo.PublicationInfo;
import com.theladders.solid.isp.jobInfo.RequirementsInfo;
import com.theladders.solid.isp.jobInfo.VisibilityInfo;
import com.theladders.solid.isp.oldjob.stubs.City;
import com.theladders.solid.isp.oldjob.stubs.Region;

public class App
{

  public static void main(String[] args)
  {

    CompanyInfo company = new CompanyInfo(100, "TheLadders");
    GeographyInfo location = new GeographyInfo("New York", new Region("NY"), new City("New York") );
    CompensationInfo compensationInfo = new CompensationInfo("1000", "2000", null, null);
    VisibilityInfo visibilityInfo = new VisibilityInfo(true, false, true);
    PublicationInfo publicationInfo = new PublicationInfo("I love this job", new Date(), new Date() );
    DescriptionInfo descriptionInfo = new DescriptionInfo("", "");
    IdentifiersInfo identifiersInfo = new IdentifiersInfo();
    JobPostInfo jobPostInfo = new JobPostInfo();
    RequirementsInfo requirementsInfo = new RequirementsInfo();
    JobAreasInfo jobAreasInfo = new JobAreasInfo();
    JobRelocationPaidInfo jobRelocationPaidInfo = new JobRelocationPaidInfo()       ;

    JobCombination job = new JobCombination(company,
                                            location,
                                            compensationInfo,
                                            visibilityInfo,
                                            publicationInfo,
                                            descriptionInfo,
                                            identifiersInfo,
                                            jobPostInfo,
                                            requirementsInfo,
                                            jobAreasInfo,
                                            jobRelocationPaidInfo);

  }

}
