package com.theladders.solid.dip;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SuggestedArticleDao implements ArticleDao
{
  public void updateByPrimaryKeySelective(@SuppressWarnings("unused") SuggestedArticleInfo article)
  {}

  public int insertReturnId(@SuppressWarnings("unused") SuggestedArticleInfo suggestedArticle)
  {
    return 0;
  }

  public List<SuggestedArticleInfo> selectByExampleWithBlobs(@SuppressWarnings("unused") SuggestedArticleExample criteria)
  {
    List<SuggestedArticleInfo> article = new ArrayList<SuggestedArticleInfo>();
    article.add(new SuggestedArticle());
    return article;
  }


  public List<SuggestedArticleInfo> getArticlesbySubscriber(Integer subscriberId)
  {
    SuggestedArticleExample criteria = new SuggestedArticleExample();
    criteria.createCriteria()
            .andSubscriberIdEqualTo(subscriberId)
            .andSuggestedArticleStatusIdIn(Arrays.asList(1, 2))
            .andSuggestedArticleSourceIdEqualTo(1);

    criteria.setOrderByClause("create_time desc");
    return selectByExampleWithBlobs(criteria);

  }

  public SuggestedArticleExample getSuggestedArticleExample()
  {
    return new SuggestedArticleExample();
  }
  
  public void updateNote(Integer id, String note)
  {
    SuggestedArticleInfo article = new SuggestedArticle();
    article.setSuggestedArticleId(id);
    article.setNote(note);
    updateByPrimaryKeySelective(article);
  }
  
  public void markRecomDeleted(Integer id, Integer status)
  {
    SuggestedArticleInfo article = new SuggestedArticle();
    article.setSuggestedArticleId(id);
    article.setSuggestedArticleStatusId(status);
    updateByPrimaryKeySelective(article);
  }  
  
}
