package com.theladders.solid.dipnewbie;

import java.util.List;

import com.theladders.solid.subscriber.Subscriber;

public interface SubscriberArticleRepository
{
  /**
   * Get a list of all active suggested articles for a given subscriber.
   *
   *   Sorted reverse chronological by time recommended
   *
   * It also gets the content of the actual articles from the CMS and stores it in
   * the SuggestedArticle object.
   *
   * @param subscriberId
   *          ID of this subscriber
   * @return List of all suggested articles whose status is either New or Viewed
   */
  public List<SuggestedArticle> getArticlesbySubscriber(Subscriber subscriber);

  /**
   * Add a SuggestedArticle to the database.
   *
   * @param suggestedArticle
   */

  public SuggestedArticleId addSuggestedArticle(SuggestedArticle suggestedArticle);

  /**
   * Update the note of the Suggested Article(id)
   *  with the note passed in.
   *
   */
  public void updateNote(SuggestedArticle article);

  /**
   * Mark as deleted the Suggested Article(id)
   *
   */
  public void markRecomDeleted(SuggestedArticle article);
}