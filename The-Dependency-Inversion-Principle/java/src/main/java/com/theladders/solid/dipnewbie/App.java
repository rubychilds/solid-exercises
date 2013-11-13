package com.theladders.solid.dipnewbie;

public class App
{

  public static void main(String[] args)
  {
    ArticleDao suggestedArticleDao = new SuggestedArticleDao();
    NodeRepository nodeRepo = new ContentNodeRepository();
    SubscriberArticleRepository suggestedArticleRepository = new SubscriberArticleRepositoryImpl(suggestedArticleDao,
                                                                                                 nodeRepo);

    // ADDING SUGGESTED ARTICLE
    SuggestedArticle suggestedArticle = new SuggestedArticle();

    suggestedArticle.setSuggestedArticleStatusId(SuggestedArticleStatusId.STATUS_UNREAD);
    suggestedArticle.setSuggestedArticleSourceId(SuggestedArticleSourceId.HTP_CONSULTANT);

    suggestedArticleRepository.addSuggestedArticle(suggestedArticle);

    // UPDATE NOTE
    suggestedArticle.setNote("hello, I am a new note");
    suggestedArticleRepository.updateNote(suggestedArticle);

    // DELETE NOTE
    suggestedArticleRepository.markRecomDeleted(suggestedArticle);

  }

}
