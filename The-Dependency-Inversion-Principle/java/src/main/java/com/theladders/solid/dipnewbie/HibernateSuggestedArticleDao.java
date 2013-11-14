package com.theladders.solid.dipnewbie;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.theladders.solid.subscriber.SubscriberId;

public class SuggestedArticleDao implements ArticleDao
{
  public void updateByPrimaryKeySelective(@SuppressWarnings("unused") SuggestedArticle article)
  {}

  public SuggestedArticleId insertReturnId(@SuppressWarnings("unused") SuggestedArticle suggestedArticle)
  {
    return suggestedArticle.getSuggestedArticleId();
  }

  private List<SuggestedArticle> selectByExampleWithBlobs(@SuppressWarnings("unused") SuggestedArticleExample criteria)
  {
    return Collections.singletonList(new SuggestedArticle());
  }

  public List<SuggestedArticle> filterArticlesBySubscriber(SubscriberId subscriberId,
                                                           Integer suggestedArticleSourceId,
                                                           List<Integer> suggestedArticleStatusId)
  {

    SuggestedArticleExample criteria = new SuggestedArticleExample();

    criteria.createCriteria()
            .andSubscriberIdEqualTo(subscriberId.getId())
            .andSuggestedArticleStatusIdIn(suggestedArticleStatusId)
            .andSuggestedArticleSourceIdEqualTo(suggestedArticleSourceId);

    criteria.setOrderByClause("create_time desc");

    return selectByExampleWithBlobs(criteria);
  }

  public SuggestedArticleId addSuggestedArticle(SuggestedArticle suggestedArticle)
  {
    suggestedArticle.setCreateTime(new Date()); // current date
    suggestedArticle.setUpdateTime(new Date()); // current date

    return insertReturnId(suggestedArticle);

  }

}
