package com.theladders.solid.dip;

import java.util.HashMap;
import java.util.Map;

public class CategoryImageMap
{
  private static final Map<String, String> CATEGORY_IMAGE_MAP = new HashMap<>();

  public CategoryImageMap()
  {
    CATEGORY_IMAGE_MAP.put("Interviewing", "interviewing_thumb.jpg");
    CATEGORY_IMAGE_MAP.put("Job Search", "job_search_thumb.jpg");
    CATEGORY_IMAGE_MAP.put("Networking", "networking_thumb.jpg");
    CATEGORY_IMAGE_MAP.put("Personal Branding", "personalBranding_thumb.jpg");
    CATEGORY_IMAGE_MAP.put("Resume", "resume_thumb.jpg");
    CATEGORY_IMAGE_MAP.put("Salary", "salary_thumb.jpg");
    CATEGORY_IMAGE_MAP.put("Assessment", "salary_thumb.jpg");
    CATEGORY_IMAGE_MAP.put("On the Job", "salary_thumb.jpg");
  }
  
  public static String getCategory(String category)
  {
    return CATEGORY_IMAGE_MAP.get(category);
  }
  
}
