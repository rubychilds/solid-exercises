package com.theladders.solid.dipnewbie;

import java.util.Arrays;
import java.util.List;

import com.theladders.solid.subscriber.Subscriber;
import com.theladders.solid.subscriber.SubscriberId;

public class SubscriberArticleRepositoryImpl implements SubscriberArticleRepository
{
  private ArticleDao     suggestedArticleDao;
  private NodeRepository NodeRepo;

  public SubscriberArticleRepositoryImpl(ArticleDao suggestedArticleDao,
                                         NodeRepository contentNodeRepo)
  {
    this.suggestedArticleDao = suggestedArticleDao;
    this.NodeRepo = contentNodeRepo;
  }

  public List<SuggestedArticle> getArticlesbySubscriber(Subscriber subscriber)
  {
    SubscriberId subscriberId = subscriber.getsubscriberId();
    
    ArticleResolver articleResolver = new SuggestedArticleResolver(NodeRepo);

    List<Integer> view_or_new = Arrays.asList(SuggestedArticleStatusId.VIEW, SuggestedArticleStatusId.NEW);

    List<SuggestedArticle> articles = suggestedArticleDao.filterArticlesBySubscriber(subscriberId,
                                                                                     SuggestedArticleSourceId.HTP_CONSULTANT,
                                                                                     view_or_new);

    return articleResolver.resolveArticles(articles);

  }

  public SuggestedArticleId addSuggestedArticle(SuggestedArticle suggestedArticle)
  {
    return suggestedArticleDao.addSuggestedArticle(suggestedArticle);
  }

  public void updateNote(SuggestedArticle article)
  {
    suggestedArticleDao.updateByPrimaryKeySelective(article);
  }

  public void markRecomDeleted(SuggestedArticle article)
  {
    article.setSuggestedArticleStatusId(SuggestedArticleStatusId.STATUS_DELETED);
    suggestedArticleDao.updateByPrimaryKeySelective(article);
  }
}
