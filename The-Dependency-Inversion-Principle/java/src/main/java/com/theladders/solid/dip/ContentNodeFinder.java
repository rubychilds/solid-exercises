package com.theladders.solid.dip;

public class ContentNodeFinder implements NodeFinder
{
  public ContentNodeProperty getNodeByUuid(String id)
  {
    ContentNodeProperty node = new ContentNodeProperty();
    node.addProperty("uuid", id);
    return node;
  }
}
