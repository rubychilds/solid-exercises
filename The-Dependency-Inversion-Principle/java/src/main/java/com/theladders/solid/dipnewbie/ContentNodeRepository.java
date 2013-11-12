package com.theladders.solid.dipnewbie;

public class ContentNodeRepository implements NodeRepository
{
  public NodeInfo getNodeByUuid(String id)
  {
    ContentNode node = new ContentNode();
    node.addProperty("uuid", id);
    return node;
  }
}
