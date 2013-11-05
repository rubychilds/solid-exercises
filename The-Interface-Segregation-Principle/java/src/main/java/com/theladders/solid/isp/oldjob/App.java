package com.theladders.solid.isp.oldjob;

import java.util.Date;

import com.theladders.solid.isp.jobInfo.JobCompanyInfo;
import com.theladders.solid.isp.jobInfo.JobCompensationInfo;
import com.theladders.solid.isp.jobInfo.JobDescriptionInfo;
import com.theladders.solid.isp.jobInfo.JobGeographyInfo;
import com.theladders.solid.isp.jobInfo.JobIdentifiersInfo;
import com.theladders.solid.isp.jobInfo.JobAreasInfo;
import com.theladders.solid.isp.jobInfo.JobPostInfo;
import com.theladders.solid.isp.jobInfo.JobPublicationInfo;
import com.theladders.solid.isp.jobInfo.JobRequirementsInfo;
import com.theladders.solid.isp.jobInfo.JobVisibilityInfo;
import com.theladders.solid.isp.oldjob.stubs.City;
import com.theladders.solid.isp.oldjob.stubs.Region;

public class App
{

  public static void main(String[] args)
  {

    JobCompanyInfo company = new JobCompanyInfo(100, "TheLadders");
    JobGeographyInfo location = new JobGeographyInfo("New York", new Region("NY"), new City("New York") );
    JobCompensationInfo compensationInfo = new JobCompensationInfo("1000", "2000", null, null);
    JobVisibilityInfo visibilityInfo = new JobVisibilityInfo(true, false, true);
    JobPublicationInfo publicationInfo = new JobPublicationInfo("I love this job", new Date(), new Date() );
    JobDescriptionInfo descriptionInfo = new JobDescriptionInfo("", "");
    JobIdentifiersInfo identifiersInfo = new JobIdentifiersInfo();
    JobPostInfo jobPostInfo = new JobPostInfo();
    JobRequirementsInfo requirementsInfo = new JobRequirementsInfo();
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
