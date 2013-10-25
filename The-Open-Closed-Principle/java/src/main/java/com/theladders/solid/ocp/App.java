package com.theladders.solid.ocp;

import java.util.ArrayList;

import com.theladders.solid.ocp.jobseeker.JobseekerConfidentialityProfileDao;
import com.theladders.solid.ocp.resume.ConfidentialPhraseCategory;
import com.theladders.solid.ocp.resume.ConfidentialResumeHandler;
import com.theladders.solid.ocp.resume.JobseekerProfileManager;
import com.theladders.solid.ocp.resume.ResumeConfidentialityManager;
import com.theladders.solid.ocp.user.User;

public class App
{
  public static void main(String[] args)
  {
    JobseekerProfileManager jobseekerProfileManager = new JobseekerProfileManager();
    JobseekerConfidentialityProfileDao jobseekerConfidentialityProfileDao = new JobseekerConfidentialityProfileDao();
    ConfidentialResumeHandler confidentialResumeHandler = new ConfidentialResumeHandler(jobseekerProfileManager, jobseekerConfidentialityProfileDao);
    ResumeConfidentialityManager resumeConfidentialityManager = new ResumeConfidentialityManager(confidentialResumeHandler);

    int id = 1; // get from command line?
    User user = new User(id);
    
    ArrayList<ConfidentialPhraseCategory> contactCategories = new ArrayList<ConfidentialPhraseCategory>();
    contactCategories.add(ConfidentialPhraseCategory.PhoneNumber);
    contactCategories.add(ConfidentialPhraseCategory.EmailAddress);
    contactCategories.add(ConfidentialPhraseCategory.MailingAddress);
    contactCategories.add(ConfidentialPhraseCategory.ContactInfo);
    
    resumeConfidentialityManager.makeAllContactInfoNonConfidential(user, contactCategories);
    resumeConfidentialityManager.makeAllCategoriesNonConfidential(user);
  }
}
