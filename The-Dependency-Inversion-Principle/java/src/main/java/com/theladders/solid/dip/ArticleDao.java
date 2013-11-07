package com.theladders.solid.dip;

import java.util.List;

public interface ArticleDao
{
  public void updateByPrimaryKeySelective(SuggestedArticle article);

  public int insertReturnId(SuggestedArticle suggestedArticle);

  public List<SuggestedArticle> selectByExampleWithBlobs(SuggestedArticleExample criteria);
}
