package com.theladders.solid.dip;

public class RepositoryManager
{
  public ContentNode getNodeByUuid(String id)
  {
    ContentNode node = new ContentNodeProperty();

    node.addProperty("uuid", id);

    return node;
  }
}
