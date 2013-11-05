package com.theladders.solid.isp.jobInfo;

import java.util.Date;

public class PublicationInfo implements Publication
{
  private Date   publicationDate;
  private Date   originalPublicationDate;
  private String editorNote;

  public PublicationInfo(String editorNote, Date publicationDate, Date originalPublicationDate)
  {
    this.editorNote = editorNote;
    this.publicationDate = publicationDate;
    this.originalPublicationDate = originalPublicationDate;
  }
  
  
  public Date getPublicationDate()
  {
    return publicationDate;
  }

  public void setPublicationDate(Date publicationDate)
  {
    this.publicationDate = publicationDate;
  }

  public String getEditorNote()
  {
    return editorNote;
  }

  public void setEditorNote(String editorNote)
  {
    this.editorNote = editorNote;
  }
  
  public Date getOriginalPublicationDate()
  {
    return originalPublicationDate;
  }
  
  public void setOriginalPublicationDate(Date originalPublicationDate)
  {
    this.originalPublicationDate = originalPublicationDate;
  }
}
