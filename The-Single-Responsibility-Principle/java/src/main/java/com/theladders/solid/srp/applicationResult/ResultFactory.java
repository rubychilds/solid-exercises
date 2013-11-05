package com.theladders.solid.srp.applicationResult;

public class ResultFactory
{

  public static ProvideErrorMessage getErrorView()
  {
    return new ProvideErrorMessage();
  }

  public static ProvideInvalidJobResult getInvalidJobResult()
  {
    return new ProvideInvalidJobResult();
  }

  public static ProvideSuccessResult getSuccessResult()
  {
    return new ProvideSuccessResult();
  }

  public static ProvideResumeCompletionResult getResumeCompletionResult()
  {
    return new ProvideResumeCompletionResult();
  }
}
