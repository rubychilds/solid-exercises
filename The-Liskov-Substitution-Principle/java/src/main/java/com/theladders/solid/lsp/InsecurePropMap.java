package com.theladders.solid.lsp;

import java.util.HashMap;

public class InsecurePropMap extends HashMap<String, String>
{
  private static final String              AUTO_HOME   = "autoProtoHome";
  private static final String              MEMBER_HOME = "memberHome";
  private static final String              GUEST_HOME  = "guestHome";
  
  public InsecurePropMap()
  {
    this.put(AUTO_HOME, "home");
    this.put(MEMBER_HOME, "insecureMemberHome");
    this.put(GUEST_HOME, "insecureGuestHome");
  }
}
