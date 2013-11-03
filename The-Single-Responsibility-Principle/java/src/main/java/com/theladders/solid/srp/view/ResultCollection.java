package com.theladders.solid.srp.view;

public class ResultCollection
{

  public ProvideErrorMessage getErrorView()
  {
    return new ProvideErrorMessage();
  }

  public ProvideInvalidJobResult getInvalidJobView()
  {
    return new ProvideInvalidJobResult();
  }

  public ProvideSuccessResult getSuccessView()
  {
    return new ProvideSuccessResult();
  }

  public ProvideResumeCompletionResult getResumeCompletionView()
  {
    return new ProvideResumeCompletionResult();
  }

}
