package com.theladders.solid.dipnewbie;

public class ContentNodeManager implements NodeManager
{
  public NodeInfo getNodeByUuid(String id)
  {
    ContentNode node = new ContentNode();
    node.addProperty("uuid", id);
    return node;
  }
}
