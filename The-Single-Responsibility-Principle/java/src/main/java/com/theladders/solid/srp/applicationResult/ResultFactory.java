package com.theladders.solid.srp.applicationResult;

public class ResultFactory
{

  public static ProvideErrorMessage getErrorView()
  {
    return new ProvideErrorMessage();
  }

  public static ProvideInvalidJobResult getInvalidJobView()
  {
    return new ProvideInvalidJobResult();
  }

  public static ProvideSuccessResult getSuccessView()
  {
    return new ProvideSuccessResult();
  }

  public static ProvideResumeCompletionResult getResumeCompletionView()
  {
    return new ProvideResumeCompletionResult();
  }

}
