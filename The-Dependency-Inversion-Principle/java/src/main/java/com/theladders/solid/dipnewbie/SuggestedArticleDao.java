package com.theladders.solid.dipnewbie;

import java.util.Collections;
import java.util.List;

public class SuggestedArticleDao
{
  public void updateByPrimaryKeySelective(@SuppressWarnings("unused") SuggestedArticle article) {}

  public SuggestedArticleId insertReturnId(@SuppressWarnings("unused") SuggestedArticle suggestedArticle)
  {
    return suggestedArticle.getSuggestedArticleId();
  }

  public List<SuggestedArticle> selectByExampleWithBlobs(@SuppressWarnings("unused") SuggestedArticleExample criteria)
  {
    return Collections.singletonList(new SuggestedArticle());
  }
}
