package com.theladders.solid.srp.view;

public class ViewCollection
{

  public ProvideErrorMessage getErrorView()
  {
    return new ProvideErrorMessage();
  }

  public ProvideInvalidJobView getInvalidJobView()
  {
    return new ProvideInvalidJobView();
  }

  public ProvideSuccessView getSuccessView()
  {
    return new ProvideSuccessView();
  }

  public ProvideResumeCompletionView getResumeCompletionView()
  {
    return new ProvideResumeCompletionView();
  }

}
