package com.theladders.solid.dipnewbie;

public class ContentNodeManager
{
  public ContentNodeInfo getNodeByUuid(String id)
  {
    ContentNode node = new ContentNode();
    node.addProperty("uuid", id);
    return node;
  }
}
