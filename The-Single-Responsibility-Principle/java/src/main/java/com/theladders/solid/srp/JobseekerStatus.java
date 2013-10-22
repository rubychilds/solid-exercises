package com.theladders.solid.srp;

import com.theladders.solid.srp.jobseeker.Jobseeker;
import com.theladders.solid.srp.jobseeker.JobseekerProfile;
import com.theladders.solid.srp.jobseeker.JobseekerProfileManager;
import com.theladders.solid.srp.jobseeker.ProfileStatus;

public class JobseekerStatus
{
  public static boolean needsToCompleteProfile(Jobseeker jobseeker , 
                                         JobseekerProfileManager jobseekerProfileManager)
  {
    JobseekerProfile profile = jobseekerProfileManager.getJobSeekerProfile(jobseeker);
    return  !jobseeker.isPremium() && incompleteJobSeekerProfile(profile);
    
  }
  
  private static boolean incompleteJobSeekerProfile(JobseekerProfile profile)
  {
    return (profile.getStatus().equals(ProfileStatus.INCOMPLETE) ||
        profile.getStatus().equals(ProfileStatus.NO_PROFILE) ||
        profile.getStatus().equals(ProfileStatus.REMOVED));
  }
}
