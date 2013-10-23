package com.theladders.solid.srp;

import com.theladders.solid.srp.jobseeker.Jobseeker;

public interface SessionData
{
 int getJobId();
 String activateResume();
 String whichResume();
 Jobseeker getJobseeker();
}
