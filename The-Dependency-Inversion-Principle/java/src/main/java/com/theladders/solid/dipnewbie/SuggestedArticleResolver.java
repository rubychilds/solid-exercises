package com.theladders.solid.dipnewbie;

import java.util.List;

import com.theladders.solid.utils.CategoryImageMap;

public class SuggestedArticleResolver implements ArticleResolver
{
  private NodeRepository nodeRepo;

  public SuggestedArticleResolver(NodeRepository nodeRepo)
  {
    this.nodeRepo = nodeRepo;
  }

  public List<SuggestedArticle> resolveArticles(List<SuggestedArticle> articles)
  {
    for (SuggestedArticle article : articles)
    {
      // Attempt to fetch the actual content;
      NodeProperty content = article.retrieveContentFrom(nodeRepo);
      if (content != null && content.isPublishedAndEnabled())
      {
        // Override miniImagePath
        content = overrideMiniImagePath(content);
        article.setContent(content);
      }
    }

    return articles;
  }

  private static NodeProperty overrideMiniImagePath(NodeProperty node)
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
