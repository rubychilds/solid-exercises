package com.theladders.solid.srp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.theladders.solid.srp.applicationResult.ApplicationResult;
import com.theladders.solid.srp.applicationResult.ProvideErrorMessage;
import com.theladders.solid.srp.applicationResult.ProvideInvalidJobResult;
import com.theladders.solid.srp.applicationResult.ProvideResumeCompletionResult;
import com.theladders.solid.srp.applicationResult.ProvideSuccessResult;
import com.theladders.solid.srp.http.HttpRequest;
import com.theladders.solid.srp.http.HttpResponse;
import com.theladders.solid.srp.http.HttpSession;
import com.theladders.solid.srp.job.Job;
import com.theladders.solid.srp.job.JobRepository;
import com.theladders.solid.srp.job.JobSearchService;
import com.theladders.solid.srp.job.application.JobApplicationRepository;
import com.theladders.solid.srp.job.application.JobApplicationSystem;
import com.theladders.solid.srp.job.application.SuccessfulApplication;
import com.theladders.solid.srp.jobseeker.Jobseeker;
import com.theladders.solid.srp.jobseeker.JobseekerProfile;
import com.theladders.solid.srp.jobseeker.JobseekerProfileManager;
import com.theladders.solid.srp.jobseeker.JobseekerProfileRepository;
import com.theladders.solid.srp.jobseeker.ProfileStatus;
import com.theladders.solid.srp.resume.ActiveResumeRepository;
import com.theladders.solid.srp.resume.MyResumeManager;
import com.theladders.solid.srp.resume.Resume;
import com.theladders.solid.srp.resume.ResumeManager;
import com.theladders.solid.srp.resume.ResumeRepository;

public class TestApplicationBuffer
{

  private static final int           INVALID_JOB_ID        = 555;
  private static final String        SHARED_RESUME_NAME    = "A Resume";
  private static final int           JOBSEEKER_WITH_RESUME = 777;
  private static final int           INCOMPLETE_JOBSEEKER  = 888;
  private static final int           APPROVED_JOBSEEKER    = 1010;

  private ApplicationBuffer          applicationBuffer;
  private JobRepository              jobRepository;
  private ResumeRepository           resumeRepository;
  private JobApplicationRepository   jobApplicationRepository;
  private JobseekerProfileRepository jobseekerProfileRepository;
  private ActiveResumeRepository     activeResumeRepository;

  private SuccessfulApplication      existingApplication;

  @Test
  public void requestWithValidJob()
  {
    int jobId = 5;
    String origFileName = SHARED_RESUME_NAME;
    Jobseeker jobseeker = new Jobseeker(APPROVED_JOBSEEKER, true);

    ApplicationResult finalResult = applicationBuffer.execute(jobId, origFileName, jobseeker, null, null);

    assertEquals("success", finalResult.getResult().getType());

  }

  @Test
  public void requestWithValidJobByBasic()
  {
    int jobId = 5;
    String origFileName = SHARED_RESUME_NAME;
    Jobseeker jobseeker = new Jobseeker(APPROVED_JOBSEEKER, false);

    ApplicationResult finalResult = applicationBuffer.execute(jobId, origFileName, jobseeker, null, null);

    assertEquals("success", finalResult.getResult().getType());
  }

  @Test
  public void applyUsingExistingResume()
  {
    int jobId = 5;
    String origFileName = SHARED_RESUME_NAME;
    Jobseeker jobseeker = new Jobseeker(JOBSEEKER_WITH_RESUME, true);

    ApplicationResult finalResult = applicationBuffer.execute(jobId, origFileName, jobseeker, "existing", "whichResume");

    assertEquals("success", finalResult.getResult().getType());
  }

  @Test
  public void requestWithInvalidJob()
  {
    int jobId = INVALID_JOB_ID;
    String origFileName = SHARED_RESUME_NAME;
    Jobseeker jobseeker = new Jobseeker(APPROVED_JOBSEEKER, true);

    ApplicationResult finalResult = applicationBuffer.execute(jobId, origFileName, jobseeker, "existing", "whichResume");

    assertEquals("invalidJob", finalResult.getResult().getType());
  }

  @Test
  public void requestWithNoResume()
  {
    int jobId = 5;
    String origFileName = null;
    Jobseeker jobseeker = new Jobseeker(APPROVED_JOBSEEKER, true);

    ApplicationResult finalResult = applicationBuffer.execute(jobId, origFileName, jobseeker, null, null);
    
    assertEquals("error", finalResult.getResult().getType());
  }

  @Test
  public void reapplyToJob()
  {
    int jobId = 15;
    String origFileName = SHARED_RESUME_NAME;
    Jobseeker jobseeker = new Jobseeker(APPROVED_JOBSEEKER, true);

    ApplicationResult finalResult = applicationBuffer.execute(jobId, origFileName, jobseeker, null, null);
    
    assertEquals("error", finalResult.getResult().getType());
  }

  @Test
  public void unapprovedBasic()
  {
    int jobId = 5;
    String origFileName = SHARED_RESUME_NAME;
    Jobseeker jobseeker = new Jobseeker(INCOMPLETE_JOBSEEKER, false);

    ApplicationResult finalResult = applicationBuffer.execute(jobId, origFileName, jobseeker, null, null);

    assertEquals("completeResumePlease", finalResult.getResult().getType());
  }

  @Test
  public void resumeIsSaved()
  {
    int jobId = 5;
    String origFileName = SHARED_RESUME_NAME;
    Jobseeker jobseeker = new Jobseeker(APPROVED_JOBSEEKER, true);

    applicationBuffer.execute(jobId, origFileName, jobseeker, null, null);

    assertTrue(resumeRepository.contains(new Resume(SHARED_RESUME_NAME)));
  }

  @Test
  public void resumeIsMadeActive()
  {

    int jobId = 5;
    String origFileName = "Save Me Seymour";
    Jobseeker jobseeker = new Jobseeker(APPROVED_JOBSEEKER, true);

    applicationBuffer.execute(jobId, origFileName, jobseeker, "yes", "makeResumeActive");

    assertEquals(new Resume("Save Me Seymour"), activeResumeRepository.activeResumeFor(APPROVED_JOBSEEKER));
  }

  @Before
  public void setup()
  {
    setupJobseekerProfileRepository();
    setupJobRepository();
    setupResumeRepository();
    setupActiveResumeRepository();
    setupJobApplicationRepository();
    setupApplicationBuffer();
  }

  private void setupJobseekerProfileRepository()
  {
    jobseekerProfileRepository = new JobseekerProfileRepository();

    addToJobseekerProfileRepository(APPROVED_JOBSEEKER, ProfileStatus.APPROVED);
    addToJobseekerProfileRepository(INCOMPLETE_JOBSEEKER, ProfileStatus.INCOMPLETE);
    addToJobseekerProfileRepository(JOBSEEKER_WITH_RESUME, ProfileStatus.APPROVED);
  }

  private void addToJobseekerProfileRepository(int id,
                                               ProfileStatus status)
  {
    JobseekerProfile profile = new JobseekerProfile(id, status);
    jobseekerProfileRepository.addProfile(profile);
  }

  private void setupJobRepository()
  {
    jobRepository = new JobRepository();

    addJobToRepository(5);
    addJobToRepository(15);
    addJobToRepository(51);
    addJobToRepository(57);
    addJobToRepository(501);
    addJobToRepository(1555);
    addJobToRepository(5012);
    addJobToRepository(50111);
  }

  private void addJobToRepository(int jobId)
  {
    if (jobId != INVALID_JOB_ID)
    {
      jobRepository.addJob(new Job(jobId));
    }
  }

  private void setupResumeRepository()
  {
    resumeRepository = new ResumeRepository();
  }

  private void setupActiveResumeRepository()
  {
    activeResumeRepository = new ActiveResumeRepository();

    activeResumeRepository.makeActive(JOBSEEKER_WITH_RESUME, new Resume("Blammo"));
  }

  private void setupJobApplicationRepository()
  {
    jobApplicationRepository = new JobApplicationRepository();

    addToJobApplicationRepository();
  }

  private void addToJobApplicationRepository()
  {
    Jobseeker JOBSEEKER = new Jobseeker(APPROVED_JOBSEEKER, true);
    Job job = new Job(15);
    Resume resume = new Resume("foo");

    existingApplication = new SuccessfulApplication(JOBSEEKER, job, resume);

    jobApplicationRepository.add(existingApplication);
  }

  public void setupApplicationBuffer()
  {
    JobseekerProfileManager jobseekerProfileManager = new JobseekerProfileManager(jobseekerProfileRepository);
    JobSearchService jobSearchService = new JobSearchService(jobRepository);
    JobApplicationSystem jobApplicationSystem = new JobApplicationSystem(jobApplicationRepository);
    ResumeManager resumeManager = new ResumeManager(resumeRepository);
    MyResumeManager myResumeManager = new MyResumeManager(activeResumeRepository);

    applicationBuffer = new ApplicationBuffer(jobseekerProfileManager,
                                              jobSearchService,
                                              jobApplicationSystem,
                                              resumeManager,
                                              myResumeManager);
  }
}
