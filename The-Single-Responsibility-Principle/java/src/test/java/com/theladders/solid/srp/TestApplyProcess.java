package com.theladders.solid.srp;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.theladders.solid.srp.applicationResult.ApplicationResult;
import com.theladders.solid.srp.applicationResult.ProvideErrorMessage;
import com.theladders.solid.srp.applicationResult.ProvideInvalidJobResult;
import com.theladders.solid.srp.applicationResult.ProvideResumeCompletionResult;
import com.theladders.solid.srp.applicationResult.ProvideSuccessResult;
import com.theladders.solid.srp.job.Job;
import com.theladders.solid.srp.job.JobRepository;
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

public class TestApplyProcess
{

  private static final int           INVALID_JOB_ID        = 555;
  private static final String        SHARED_RESUME_NAME    = "A Resume";
  private static final int           JOBSEEKER_WITH_RESUME = 777;
  private static final int           INCOMPLETE_JOBSEEKER  = 888;
  private static final int           APPROVED_JOBSEEKER    = 1010;

  private ApplicationProcess         applicationProcess;
  private JobRepository              jobRepository;
  private ResumeRepository           resumeRepository;
  private JobApplicationRepository   jobApplicationRepository;
  private JobseekerProfileRepository jobseekerProfileRepository;
  private ActiveResumeRepository     activeResumeRepository;

  private SuccessfulApplication      existingApplication;

  @Test
  public void requestWithNullCVString()
  {
    Job job = new Job(15);
    Jobseeker jobseeker = new Jobseeker(APPROVED_JOBSEEKER, true);
    ResumeData resumeData = new ResumeData(null, null, null);

    ApplicationResult applicationResult = applicationProcess.execute(job, jobseeker, resumeData);

    assertTrue(applicationResult.getClass().isInstance(new ProvideErrorMessage()));
  }

  @Test
  public void requestWithValidJob()
  {
    Jobseeker jobseeker = new Jobseeker(APPROVED_JOBSEEKER, true);
    Job job = new Job(5);

    ResumeData resumeData = new ResumeData(SHARED_RESUME_NAME, null, null);

    ApplicationResult applicationResult = applicationProcess.execute(job, jobseeker, resumeData);

    assertTrue(applicationResult.getClass().isInstance(new ProvideSuccessResult()));
  }

  @Test
  public void requestWithValidJobByBasic()
  {
    Jobseeker jobseeker = new Jobseeker(APPROVED_JOBSEEKER, false);
    Job job = new Job(5);

    ResumeData resumeData = new ResumeData(SHARED_RESUME_NAME, null, null);
    ApplicationResult applicationResult = applicationProcess.execute(job, jobseeker, resumeData);

    assertTrue(applicationResult.getClass().isInstance(new ProvideSuccessResult()));
  }

  @Test
  public void applyUsingExistingResume()
  {
    Jobseeker jobseeker = new Jobseeker(JOBSEEKER_WITH_RESUME, true);
    Job job = new Job(5);

    ResumeData resumeData = new ResumeData(SHARED_RESUME_NAME, "whichResume", "existing");
    ApplicationResult applicationResult = applicationProcess.execute(job, jobseeker, resumeData);

    assertTrue(applicationResult.getClass().isInstance(new ProvideSuccessResult()));
  }

  @Test
  public void requestWithInvalidJob()
  {
    Jobseeker jobseeker = new Jobseeker(APPROVED_JOBSEEKER, true);
    Job job = new Job(INVALID_JOB_ID);

    ResumeData resumeData = new ResumeData(SHARED_RESUME_NAME, null, null);
    ApplicationResult applicationResult = applicationProcess.execute(job, jobseeker, resumeData);

    applicationResult.getClass().isInstance(new ProvideInvalidJobResult());
  }

  @Test
  public void requestWithNoResume()
  {
    Jobseeker jobseeker = new Jobseeker(APPROVED_JOBSEEKER, true);

    Job job = new Job(5);

    ResumeData resumeData = new ResumeData(null, null, null);

    ApplicationResult applicationResult = applicationProcess.execute(job, jobseeker, resumeData);

    applicationResult.getClass().isInstance(new ProvideErrorMessage());
  }

  @Test
  public void reapplyToJob()
  {
    Jobseeker jobseeker = new Jobseeker(APPROVED_JOBSEEKER, true);

    Job job = new Job(15);

    ResumeData resumeData = new ResumeData(SHARED_RESUME_NAME, null, null);

    ApplicationResult applicationResult = applicationProcess.execute(job, jobseeker, resumeData);

    applicationResult.getClass().isInstance(new ProvideErrorMessage());
  }

  @Test
  public void unapprovedBasic()
  {
    Jobseeker jobseeker = new Jobseeker(INCOMPLETE_JOBSEEKER, false);

    Job job = new Job(5);

    ResumeData resumeData = new ResumeData(SHARED_RESUME_NAME, null, null);

    ApplicationResult applicationResult = applicationProcess.execute(job, jobseeker, resumeData);

    applicationResult.getClass().isInstance(new ProvideResumeCompletionResult());
  }

  @Test
  public void resumeIsSaved()
  {
    Jobseeker jobseeker = new Jobseeker(APPROVED_JOBSEEKER, true);

    Job job = new Job(5);

    ResumeData resumeData = new ResumeData(SHARED_RESUME_NAME, null, null);

    applicationProcess.execute(job, jobseeker, resumeData);

    assertTrue(resumeRepository.contains(new Resume(SHARED_RESUME_NAME)));
  }

  @Test
  public void resumeIsMadeActive()
  {
    Jobseeker jobseeker = new Jobseeker(APPROVED_JOBSEEKER, true);

    Job job = new Job(5);

    ResumeData resumeData = new ResumeData("Save Me Seymour", "yes", "makeResumeActive");

    applicationProcess.execute(job, jobseeker, resumeData);

    assertTrue(resumeRepository.contains(new Resume("Save Me Seymour")));
  }

  @Before
  public void setup()
  {
    setupJobseekerProfileRepository();
    setupJobRepository();
    setupResumeRepository();
    setupActiveResumeRepository();
    setupJobApplicationRepository();
    setupApplicationProcess();
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

  public void setupApplicationProcess()
  {
    JobseekerProfileManager jobseekerProfileManager = new JobseekerProfileManager(jobseekerProfileRepository);
    JobApplicationSystem jobApplicationSystem = new JobApplicationSystem(jobApplicationRepository);
    ResumeManager resumeManager = new ResumeManager(resumeRepository);
    MyResumeManager myResumeManager = new MyResumeManager(activeResumeRepository);

    applicationProcess = new ApplicationProcess(jobseekerProfileManager,
                                                jobApplicationSystem,
                                                resumeManager,
                                                myResumeManager);
  }
}
