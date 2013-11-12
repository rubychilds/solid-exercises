package com.theladders.solid.dipnewbie;

import java.util.List;

public interface ArticleResolverInfo
{
  public List<SuggestedArticle> resolveArticles(List<SuggestedArticle> dbArticles);
}
