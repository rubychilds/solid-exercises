package com.theladders.solid.ocp.resume;

public class FavouritePet extends ConfidentialPhraseCategory
{
  public final static ConfidentialPhraseCategory FavouritePet    = new ConfidentialPhraseCategory(80, "FavouritePet", ConfidentialPhraseCategoryType.CONTACT);
  public final static ConfidentialPhraseCategory FavouriteFood   = new ConfidentialPhraseCategory(81, "FavouriteFood");

  FavouritePet(int id, String name)
  {
    super(id, name);
    ConfidentialPhrases.add(this);
  }
}
