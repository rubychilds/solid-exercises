package com.theladders.solid.dipnewbie;

public class Subscriber
{
  private SubscriberId subscriberId;
  
  public Subscriber(Integer subscriberId)
  {
    this.subscriberId = new SubscriberId(subscriberId);
  }
  
  public SubscriberId getsubscriberId()
  {
    return subscriberId;
  }
  
}
