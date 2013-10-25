package com.theladders.solid.ocp.resume;

import java.util.ArrayList;

import com.theladders.solid.ocp.user.User;

public class ResumeConfidentialityManager
{
  private final ConfidentialResumeHandler confidentialResumeHandler;

  public ResumeConfidentialityManager(ConfidentialResumeHandler confidentialResumeHandler)
  {
    this.confidentialResumeHandler = confidentialResumeHandler;
  }

  public void makeAllContactInfoNonConfidential(User user, ArrayList<ConfidentialPhraseCategory> contactCategories)
  {
    confidentialResumeHandler.makeAllContactInfoNonConfidential(user, contactCategories);
  }

  public void makeAllCategoriesNonConfidential(User user)
  {
    confidentialResumeHandler.makeAllCategoriesNonConfidential(user);
  }
}
