package com.theladders.solid.dipnewbie;

import java.util.List;

public interface ArticleResolver
{
  public List<SuggestedArticle> resolveArticles(List<SuggestedArticle> dbArticles);
}
