package com.theladders.solid.isp.jobInfo;

import java.util.Date;

public interface JobPublication
{
  Date getPublicationDate();

  /**
   * Get the (internally set) editor's note.
   * 
   * @return editor's note.
   */

  String getEditorNote();

  /**
   * Get the date this job was originally published
   * 
   * @return the Date the job was originally published
   */
  Date getOriginalPublicationDate();

}
