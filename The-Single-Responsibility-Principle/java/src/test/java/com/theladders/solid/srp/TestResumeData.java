package com.theladders.solid.srp;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestResumeData
{
  
  @Test
  public void testFileName()
  {
    String filename = "ruby";
    ResumeData resumeData = new ResumeData(filename, "existing", "yes");
    
    assertTrue(resumeData.fileName().equals(filename));
  }

  @Test
  public void testUseExistingResume(){
    String filename = "ruby";
    ResumeData resumeData = new ResumeData(null, null, "existing");
    
    assertTrue(resumeData.useExistingResume());

    resumeData = new ResumeData(null, null, "not-existing");
    
    assertTrue(!resumeData.useExistingResume());
    
  }
  
  @Test
  public void testmakeResumeActive(){

    ResumeData resumeData = new ResumeData(null,"yes", null);
    
    assertTrue(resumeData.makeResumeActive());

    resumeData = new ResumeData(null, "no", null);
    
    assertTrue(!resumeData.makeResumeActive());
  }
  

}
