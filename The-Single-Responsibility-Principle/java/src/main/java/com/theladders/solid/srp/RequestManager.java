package com.theladders.solid.srp;

import com.theladders.solid.srp.http.HttpRequest;
import com.theladders.solid.srp.job.Job;
import com.theladders.solid.srp.job.JobSearchService;
import com.theladders.solid.srp.jobseeker.Jobseeker;

public class RequestManager
{
  
  private HttpRequest request;
  
  private String WHICH = "which";
  private String JOB_ID = "jobId";
  private String MAKE_RESUME_ACTIVE = "makeResumeActive";

  
  public RequestManager(HttpRequest request)
  { 
    this.request = request; 
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
    return request.getSession().getJobseeker();
  }
   
  
  public Job getJob(JobSearchService jobSearchService)
  {
    return jobSearchService.getJob(getJobId());
  }
  
  
}
