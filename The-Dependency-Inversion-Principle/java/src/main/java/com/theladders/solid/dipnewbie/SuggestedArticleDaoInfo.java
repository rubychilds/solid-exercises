package com.theladders.solid.dipnewbie;

import java.util.List;

import com.theladders.solid.subscriber.SubscriberId;

public interface SuggestedArticleDaoInfo
{
  public void updateByPrimaryKeySelective(SuggestedArticle article);

  public SuggestedArticleId insertReturnId(SuggestedArticle suggestedArticle);

  public List<SuggestedArticle> filterArticlesBySubscriber(SubscriberId subscriberId,
                                                           Integer suggestedArticleSourceId,
                                                           List<Integer> suggestedArticleStatusId,
                                                           ArticleResolverInfo articleResolver);

  public SuggestedArticleId addSuggestedArticle(SuggestedArticle suggestedArticle);
}
