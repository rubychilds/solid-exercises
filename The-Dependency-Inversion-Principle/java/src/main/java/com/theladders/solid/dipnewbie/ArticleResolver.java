package com.theladders.solid.dipnewbie;

import java.util.List;

import com.theladders.solid.utils.CategoryImageMap;
import com.theladders.solid.utils.ContentUtils;

public class ArticleResolver implements ArticleResolverInfo
{
  private NodeRepository nodeManager;

  public ArticleResolver(NodeRepository nodeManager)
  {
    this.nodeManager = nodeManager;
  }

  public List<SuggestedArticle> resolveArticles(List<SuggestedArticle> articles)
  {
    for (SuggestedArticle article : articles)
    {
      // Attempt to fetch the actual content;
      NodeInfo content = nodeManager.getNodeByUuid(article.getArticleExternalIdentifier());
      if (content != null && ContentUtils.isPublishedAndEnabled(content))
      {
        // Override miniImagePath
        content = overrideMiniImagePath(content);
        article.setContent(content);
      }
    }

    return articles;
  }

  private static NodeInfo overrideMiniImagePath(NodeInfo node)
  {
    String propertyImagePath = "miniImagePath";
    String path = (String) node.getProperty(propertyImagePath);

    if (path == null || path.length() == 0)
    {
      String category = (String) node.getProperty("primaryTopic");
      node.addProperty(propertyImagePath, ImagePrefix.IMAGE_PREFIX + CategoryImageMap.getImage(category));
    }
    return node;
  }
}
