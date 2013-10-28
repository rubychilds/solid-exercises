package com.theladders.solid.lsp;

import java.util.HashMap;

public class SecurePropMap extends HashMap<String, String>
{
  
  private static final String              AUTO_HOME   = "autoProtoHome";
  private static final String              MEMBER_HOME = "memberHome";
  private static final String              GUEST_HOME  = "guestHome";

  public SecurePropMap()
  {
    this.put("flash", "secureFlash");
    this.put("images", "secureImages");
    this.put("css", "secureCss");
    this.put("js", "secureJs");
    this.put("widgets", "secureWidgets");

    this.put("landingFlash", "secureLandingFlash");
    this.put("landingImages", "secureLandingImages");
    this.put("landingCss", "secureLandingCss");

    this.put("opalImages", "secureOpalImages");
    this.put("opalCss", "secureOpalCss");

    this.put("seoImages", "secureSeoImages");
    this.put("seoCss", "secureSeoCss");
    this.put("staticBase", "secureStaticBase");

    this.put(AUTO_HOME, "secureHome");
    this.put(MEMBER_HOME, "secureMemberHome");
    this.put(GUEST_HOME, "secureGuestHome");
  }
}
