package com.theladders.solid.srp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.theladders.solid.srp.http.HttpRequest;
import com.theladders.solid.srp.http.HttpResponse;
import com.theladders.solid.srp.job.Job;
import com.theladders.solid.srp.job.JobSearchService;
import com.theladders.solid.srp.job.application.ApplicationFailureException;
import com.theladders.solid.srp.job.application.JobApplicationResult;
import com.theladders.solid.srp.job.application.JobApplicationSystem;
import com.theladders.solid.srp.job.application.UnprocessedApplication;
import com.theladders.solid.srp.jobseeker.JobseekerProfile;
import com.theladders.solid.srp.jobseeker.JobseekerProfileManager;
import com.theladders.solid.srp.jobseeker.ProfileStatus;
import com.theladders.solid.srp.jobseeker.Jobseeker;
import com.theladders.solid.srp.resume.MyResumeManager;
import com.theladders.solid.srp.resume.Resume;
import com.theladders.solid.srp.resume.ResumeManager;
import com.theladders.solid.srp.view.ProvideApplySuccessView;
import com.theladders.solid.srp.view.ProvideErrorView;
import com.theladders.solid.srp.view.ProvideInvalidJobView;
import com.theladders.solid.srp.view.ProvideResumeCompletionView;
import com.theladders.solid.srp.view.View;

public class ApplyController
{
  private final JobseekerProfileManager jobseekerProfileManager;
  private final JobSearchService        jobSearchService;
  private final JobApplicationSystem    jobApplicationSystem;
  private final ResumeManager           resumeManager;
  private final MyResumeManager         myResumeManager;
  private final Map<String,Object> model = new HashMap<String,Object>();

  public ApplyController(JobseekerProfileManager jobseekerProfileManager,
                         JobSearchService jobSearchService,
                         JobApplicationSystem jobApplicationSystem,
                         ResumeManager resumeManager,
                         MyResumeManager myResumeManager)
  {
    this.jobseekerProfileManager = jobseekerProfileManager;
    this.jobSearchService = jobSearchService;
    this.jobApplicationSystem = jobApplicationSystem;
    this.resumeManager = resumeManager;
    this.myResumeManager = myResumeManager;
  }

  public HttpResponse handle(HttpRequest request,
                             HttpResponse response,
                             String origFileName)
  {
    Jobseeker jobseeker = getJobSeeker(request);
    JobseekerProfile profile = jobseekerProfileManager.getJobSeekerProfile(jobseeker);

    Job job = getJob(request);
    
    if (job == null)
    {
      int jobId = getJobId(request);
      model.put("jobId", jobId);
      
      ProvideInvalidJobView invalidJobView = new ProvideInvalidJobView();
      return getResponse(invalidJobView, response);
    }

    try
    {
      ApplicationHandler applicationHandler = new ApplicationHandler(resumeManager,
                                                                     jobApplicationSystem, 
                                                                     myResumeManager);
      applicationHandler.apply(request, jobseeker, job, origFileName);
    }
    catch (Exception e)
    {
  
      ProvideErrorView errorView = new ProvideErrorView();
      errorView.addError("We could not process your application.");
      return getResponse(errorView, response);
    }

    model.put("jobId", job.getJobId());
    model.put("jobTitle", job.getTitle());

    if (!jobseeker.isPremium() && (profile.getStatus().equals(ProfileStatus.INCOMPLETE) ||
                                   profile.getStatus().equals(ProfileStatus.NO_PROFILE) ||
                                   profile.getStatus().equals(ProfileStatus.REMOVED)))
    {
      
      ProvideResumeCompletionView resumeCompletionView = new ProvideResumeCompletionView();
      return getResponse(resumeCompletionView, response);
    }

    ProvideApplySuccessView applySuccessView = new ProvideApplySuccessView();
    return getResponse(applySuccessView, response);
  }
  
  private int getJobId(HttpRequest request)
  {

    String jobIdString = request.getParameter("jobId");
    int jobId = Integer.parseInt(jobIdString);
    
    return jobId;
  }
  
  private Job getJob(HttpRequest request)
  {
    return jobSearchService.getJob(getJobId(request));
  }
  
  private Jobseeker getJobSeeker(HttpRequest request)
  {
    return request.getSession().getJobseeker();
  }
  
  private HttpResponse getResponse(View view, HttpResponse response){
    Result result = view.view(model);
    response.setResult(result);
    return response;
  }
 
}
