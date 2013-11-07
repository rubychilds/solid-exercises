package com.theladders.solid.dip;

import java.util.Date;
import java.util.List;

public class SubscriberArticleManagerImpl implements SubscriberArticleManager
{
  private static final String     IMAGE_PREFIX = "http://somecdnprodiver.com/static/images/careerAdvice/";

  private ArticleDao              articleDao;
  private NodeFinder              nodeFinder;
  private static CategoryImageMap categoryImageMap;

  public SubscriberArticleManagerImpl(ArticleDao articleDao,
                                      NodeFinder nodeFinder)
  {
    this.articleDao = articleDao;
    this.nodeFinder = nodeFinder;
    this.categoryImageMap = new CategoryImageMap();
  }

  public List<SuggestedArticleInfo> getArticlesbySubscriber(Integer subscriberId)
  {
    List<SuggestedArticleInfo> dbSuggestions = articleDao.getArticlesbySubscriber(subscriberId);
    // Fetch content associated with SuggestedArticle (based on externalArticleId)
    resolveArticles(dbSuggestions);
    return dbSuggestions;
  }

  public int addSuggestedArticle(SuggestedArticleInfo suggestedArticle)
  {
    Integer STATUS_UNREAD = 1;
    Integer HTP_CONSULTANT = 1;
    suggestedArticle.setSuggestedArticleStatusId(STATUS_UNREAD);
    suggestedArticle.setSuggestedArticleSourceId(HTP_CONSULTANT);
    suggestedArticle.setCreateTime(new Date()); // current date
    suggestedArticle.setUpdateTime(new Date()); // current date
    int newId = articleDao.insertReturnId(suggestedArticle);
    return newId;
  }

  private void resolveArticles(List<SuggestedArticleInfo> dbArticles)
  {
    for (SuggestedArticleInfo article : dbArticles)
    {
      // Attempt to fetch the actual content;
      ContentNode content = this.nodeFinder.getNodeByUuid(article.getArticleExternalIdentifier());
      if (content != null && ContentUtils.isPublishedAndEnabled(content))
      {
        // Override miniImagePath
        overrideMiniImagePath(content);
        article.setContent(content);
      }
    }
  }

  private static void overrideMiniImagePath(ContentNode node)
  {
    String path = (String) node.getProperty("miniImagePath");

    if (path == null || path.length() == 0)
    {
      String category = (String) node.getProperty("primaryTopic");
      node.addProperty("miniImagePath", IMAGE_PREFIX + categoryImageMap.getCategory(category));
    }
  }

  public void updateNote(Integer id,
                         String note)
  {
    SuggestedArticleInfo article = articleDao.getSuggestedArticle();
    article.setSuggestedArticleId(id);
    article.setNote(note);
    articleDao.updateByPrimaryKeySelective(article);
  }

  public void markRecomDeleted(Integer id)
  {
    Integer STATUS_DELETED = 4;
    SuggestedArticleInfo article = articleDao.getSuggestedArticle();
    article.setSuggestedArticleId(id);
    article.setSuggestedArticleStatusId(STATUS_DELETED);
    articleDao.updateByPrimaryKeySelective(article);
  }
}
