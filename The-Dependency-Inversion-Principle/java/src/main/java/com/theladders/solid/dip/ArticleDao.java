package com.theladders.solid.dip;

import java.util.List;

public interface ArticleDao
{
  public void updateByPrimaryKeySelective(SuggestedArticleInfo article);

  public int insertReturnId(SuggestedArticleInfo suggestedArticle);

  public List<SuggestedArticleInfo> selectByExampleWithBlobs(SuggestedArticleExample criteria);

  public List<SuggestedArticleInfo> getArticlesbySubscriber(Integer subscriberId);

  public SuggestedArticleInfo getSuggestedArticle();

}
