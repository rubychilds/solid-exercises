package com.theladders.solid.lsp;

import java.util.HashMap;

public class NoSSLPropMap extends HashMap<String, String>
{
  
  private static final String              AUTO_HOME   = "autoProtoHome";
  private static final String              MEMBER_HOME = "memberHome";
  private static final String              GUEST_HOME  = "guestHome";
  
  public NoSSLPropMap()
  {
    this.put("secureHome", "home");
    this.put(AUTO_HOME, "home");
    this.put("secureGuestHome", "insecureGuestHome");
    this.put("secureMemberHome", "insecureMemberHome");
    this.put("secureStaticBase", "staticBase");
    this.put(MEMBER_HOME, "insecureMemberHome");
    this.put(GUEST_HOME, "insecureGuestHome");
  }
}
