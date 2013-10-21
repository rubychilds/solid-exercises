package com.theladders.solid.srp;

import com.theladders.solid.srp.http.HttpRequest;
import com.theladders.solid.srp.http.HttpSession;
import com.theladders.solid.srp.job.Job;
import com.theladders.solid.srp.job.JobSearchService;
import com.theladders.solid.srp.jobseeker.Jobseeker;

public class RequestManager
{
  
  private HttpRequest request;
  private HttpSession session;
  
  public static String WHICH = "whichResume";
  public static String JOB_ID = "jobId";
  public static String MAKE_RESUME_ACTIVE = "makeResumeActive";
  public static String EXISTING = "existing";
  public static String ACTIVATE_RESUME = "yes";
  
  public RequestManager(HttpRequest request)
  { 
    this.request = request; 
    this.session = request.getSession();
  }
  
  public String whichResume()
  {
    return request.getParameter(WHICH);
  }
  
  public int getJobId()
  {
    return Integer.parseInt(request.getParameter(JOB_ID));
  }
  
  
  public String makeResumeActive()
  {
    return request.getParameter(MAKE_RESUME_ACTIVE);
  }
  
  public Jobseeker getJobSeeker()
  {
    return session.getJobseeker();
  }
   
  
  public Job getJob(JobSearchService jobSearchService)
  {
    return jobSearchService.getJob(Integer.parseInt(request.getParameter(JOB_ID)));
  }
  
  
}
