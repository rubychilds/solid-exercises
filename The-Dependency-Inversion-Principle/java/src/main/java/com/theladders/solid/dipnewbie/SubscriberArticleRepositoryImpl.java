package com.theladders.solid.dipnewbie;

import java.util.List;

import com.theladders.solid.subscriber.Subscriber;
import com.theladders.solid.subscriber.SubscriberId;

public class SubscriberArticleRepositoryImpl implements SubscriberArticleRepository
{
  private SuggestedArticleDaoInfo suggestedArticleDao;
  private NodeRepository          NodeRepo;

  public SubscriberArticleRepositoryImpl(SuggestedArticleDaoInfo suggestedArticleDao,
                                         NodeRepository contentNodeRepo)
  {
    this.suggestedArticleDao = suggestedArticleDao;
    this.NodeRepo = contentNodeRepo;
  }

  public List<SuggestedArticle> getArticlesbySubscriber(Subscriber subscriber)
  {
    SubscriberId subscriberId = subscriber.getsubscriberId();
    ArticleResolverInfo articleResolver = new ArticleResolver(NodeRepo);

    return suggestedArticleDao.filterArticlesBySubscriber(subscriberId,
                                                          SuggestedArticleSourceId.HTP_CONSULTANT,
                                                          SuggestedArticleStatusId.VIEW_OR_NEW,
                                                          articleResolver);
  }

  public SuggestedArticleId addSuggestedArticle(SuggestedArticle suggestedArticle)
  {
    return suggestedArticleDao.addSuggestedArticle(suggestedArticle);
  }

  public void updateNote(SuggestedArticle article,
                         String note)
  {
    article.setNote(note);
    suggestedArticleDao.updateByPrimaryKeySelective(article);
  }

  public void markRecomDeleted(SuggestedArticle article)
  {
    article.setSuggestedArticleStatusId(SuggestedArticleStatusId.STATUS_DELETED);
    suggestedArticleDao.updateByPrimaryKeySelective(article);
  }
}
