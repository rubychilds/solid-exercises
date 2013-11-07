package com.theladders.solid.dip;

public class App
{

  public static void main(String[] args)
  {

    ArticleDao articleDao = new SuggestedArticleDao();
    
    NodeFinder nodeFinder = new ContentNodeFinder();
    
    SubscriberArticleManager subsArticleManager = new SubscriberArticleManagerImpl(articleDao, nodeFinder);
    

  }

}
