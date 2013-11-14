package com.theladders.solid.dipnewbie;

import java.util.List;

import com.theladders.solid.subscriber.SubscriberId;

public interface ArticleDao
{
  public void updateByPrimaryKeySelective(SuggestedArticle article);

  public SuggestedArticleId insertReturnId(SuggestedArticle suggestedArticle);

  public List<SuggestedArticle> filterArticlesBySubscriber(SubscriberId subscriberId,
                                                           Integer suggestedArticleSourceId,
                                                           List<Integer> suggestedArticleStatusId);

  public SuggestedArticleId addSuggestedArticle(SuggestedArticle suggestedArticle);
}
