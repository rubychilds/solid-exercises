package com.theladders.solid.srp.view;

import java.util.ArrayList;
import java.util.List;

import com.theladders.solid.srp.ApplicationResponseType;

public class ViewContainer
{

  private ArrayList<View> views;

  public ViewContainer()
  {
    views = new ArrayList<View>();

    views.add(new ProvideErrorMessage());
    views.add(new ProvideResumeCompletionView());
    views.add(new ProvideInvalidJobView());
    views.add(new ProvideSuccessView());
  }

  public void add(View view)
  {
    views.add(view);
  }

  public List<View> getViews()
  {
    return views;
  }

  public View getView(ApplicationResponseType type )
  {
    for (View currentView : views)
    {
      if (currentView.isType(type))
      {
        return currentView;
      }

    }
    return null;
  }

  public String toString()
  {
    return views.toString();
  }

}
