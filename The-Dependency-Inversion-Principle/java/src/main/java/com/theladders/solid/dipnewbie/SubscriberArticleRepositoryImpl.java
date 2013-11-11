package com.theladders.solid.dipnewbie;

import java.util.Date;
import java.util.List;

public class SubscriberArticleRepositoryImpl implements SubscriberArticleRepository
{
  private SuggestedArticleDao suggestedArticleDao;
  private RepositoryManager   repositoryManager;

  public SubscriberArticleRepositoryImpl(SuggestedArticleDao suggestedArticleDao,
                                         RepositoryManager repositoryManager)
  {
    this.suggestedArticleDao = suggestedArticleDao;
    this.repositoryManager = repositoryManager;
  }

  public List<SuggestedArticle> getArticlesbySubscriber(Subscriber subscriber)
  {
    SuggestedArticleExample criteria = new SuggestedArticleExample();
    SubscriberId subscriberId = subscriber.getsubscriberId();

    criteria.createCriteria()
            .andSubscriberIdEqualTo(subscriberId.getId())
            .andSuggestedArticleStatusIdIn(SuggestedArticleStatusId.VIEW_OR_NEW)
            // must be New or Viewed
            .andSuggestedArticleSourceIdEqualTo(1);

    criteria.setOrderByClause("create_time desc");
    List<SuggestedArticle> dbSuggestions = this.suggestedArticleDao.selectByExampleWithBlobs(criteria);

    // Fetch content associated with SuggestedArticle (based on externalArticleId)
    resolveArticles(dbSuggestions);

    return dbSuggestions;
  }

  public SuggestedArticleId addSuggestedArticle(SuggestedArticle suggestedArticle)
  {
    suggestedArticle.setSuggestedArticleStatusId(SuggestedArticleStatusId.STATUS_UNREAD);
    suggestedArticle.setSuggestedArticleSourceId(SuggestedArticleSourceId.HTP_CONSULTANT);
    suggestedArticle.setCreateTime(new Date()); // current date
    suggestedArticle.setUpdateTime(new Date()); // current date

    return suggestedArticleDao.insertReturnId(suggestedArticle);
  }

  private void resolveArticles(List<SuggestedArticle> dbArticles)
  {
    for (SuggestedArticle article : dbArticles)
    {
      // Attempt to fetch the actual content;
      ContentNodeInfo content = this.repositoryManager.getNodeByUuid(article.getArticleExternalIdentifier());
      if (content != null && ContentUtils.isPublishedAndEnabled(content))
      {
        // Override miniImagePath
        overrideMiniImagePath(content);
        article.setContent(content);
      }
    }
  }

  private static void overrideMiniImagePath(ContentNodeInfo node)
  {
    String path = (String) node.getProperty("miniImagePath");

    if (path == null || path.length() == 0)
    {
      String category = (String) node.getProperty("primaryTopic");
      node.addProperty("miniImagePath", ImagePrefix.IMAGE_PREFIX + CategoryImageMap.getImage(category));
    }
  }

  public void updateNote(SuggestedArticle article,
                         String note)
  {
    article.setNote(note);
    suggestedArticleDao.updateByPrimaryKeySelective(article);
  }

  public void markRecomDeleted(SuggestedArticle article)
  {
    Integer STATUS_DELETED = 4;
    article.setSuggestedArticleStatusId(STATUS_DELETED);
    suggestedArticleDao.updateByPrimaryKeySelective(article);
  }
}
