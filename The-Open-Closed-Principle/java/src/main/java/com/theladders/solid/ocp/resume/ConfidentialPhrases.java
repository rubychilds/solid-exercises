package com.theladders.solid.ocp.resume;

import java.util.List;

public class ConfidentialPhrases {
	
	
	  private static PhraseListContainer phraseList = new PhraseListContainer();

	  public final static ConfidentialPhraseCategory Name           = new ConfidentialPhraseCategory(76, "Name");
	  public final static ConfidentialPhraseCategory MailingAddress = new ConfidentialPhraseCategory(79, "MailingAddress", ConfidentialPhraseCategoryType.CONTACT);
	  public final static ConfidentialPhraseCategory PhoneNumber    = new ConfidentialPhraseCategory(78, "PhoneNumber", ConfidentialPhraseCategoryType.CONTACT);
	  public final static ConfidentialPhraseCategory EmailAddress   = new ConfidentialPhraseCategory(77, "EmailAddress", ConfidentialPhraseCategoryType.CONTACT);
	  public final static ConfidentialPhraseCategory ContactInfo    = new ConfidentialPhraseCategory(80, "ContactInfo", ConfidentialPhraseCategoryType.CONTACT);
	  public final static ConfidentialPhraseCategory CompanyName    = new ConfidentialPhraseCategory(81, "CompanyName");
	  public final static ConfidentialPhraseCategory WorkExperience = new ConfidentialPhraseCategory(82, "WorkExperience");

	  public ConfidentialPhrases()
	  {
		  phraseList.add(Name);
		  
		  
	  }
	  
	  public ConfidentialPhraseCategory getPhrase(String category)
	  {
		  for(ConfidentialPhraseCategory phrase: phraseList.values())
		  {
			  if(phrase.name().equals(category))
				  return phrase;
				  
		  }

		  return null;
		  
	  }
	  
	  public static void add(ConfidentialPhraseCategory category)
	  {
	    phraseList.add(category);
	  }

	  public static List<ConfidentialPhraseCategory> values()
	  {
	    return phraseList.values();
	  }

}
