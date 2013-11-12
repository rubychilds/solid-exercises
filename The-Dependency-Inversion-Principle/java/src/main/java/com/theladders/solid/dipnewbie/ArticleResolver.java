package com.theladders.solid.dipnewbie;

import java.util.List;

import com.theladders.solid.utils.CategoryImageMap;
import com.theladders.solid.utils.ContentUtils;

public class ArticleResolver
{
  private ContentNodeManager contentNodeManager;

  public ArticleResolver(ContentNodeManager contentNodeManager)
  {
    this.contentNodeManager = contentNodeManager;
  }

  public List<SuggestedArticle> resolveArticles(List<SuggestedArticle> dbArticles)
  {
    for (SuggestedArticle article : dbArticles)
    {
      // Attempt to fetch the actual content;
      ContentNodeInfo content = contentNodeManager.getNodeByUuid(article.getArticleExternalIdentifier());
      if (content != null && ContentUtils.isPublishedAndEnabled(content))
      {
        // Override miniImagePath
        overrideMiniImagePath(content);
        article.setContent(content);
      }
    }

    return dbArticles;
  }

  private static void overrideMiniImagePath(ContentNodeInfo node)
  {
    String propertyImagePath = "miniImagePath";
    String path = (String) node.getProperty(propertyImagePath);

    if (path == null || path.length() == 0)
    {
      String category = (String) node.getProperty("primaryTopic");
      node.addProperty(propertyImagePath, ImagePrefix.IMAGE_PREFIX + CategoryImageMap.getImage(category));
    }
  }
}
