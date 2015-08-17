package com.a.aykut.tryazuremobileservices;

import android.content.Context;
import android.util.Log;

/**
 * Created by Aykut on 15.08.2015.
 */
public class LogHelper {

  public static void setLogInfo(Context context, String value) {
    Log.i(context.getClass().getName(), value);
  }

}
