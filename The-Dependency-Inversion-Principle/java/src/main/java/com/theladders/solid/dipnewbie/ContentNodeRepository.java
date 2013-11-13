package com.theladders.solid.dipnewbie;

public class ContentNodeRepository implements NodeRepository
{
  public NodeProperty getNodeByUuid(String id)
  {
    ContentNodeProperty node = new ContentNodeProperty();
    node.addProperty("uuid", id);
    return node;
  }
}
