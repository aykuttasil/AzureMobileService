package com.a.aykut.tryazuremobileservices;

import android.content.Context;
import android.util.Log;

import com.microsoft.windowsazure.mobileservices.MobileServiceClient;

/**
 * Created by Aykut on 17.08.2015.
 */
public class AzureMobileServiceTableHelper<T> {

  AzureMobileServicesHelper amsh;
  MobileServiceClient mClient;

  public AzureMobileServiceTableHelper(Context context) {
    amsh = AzureMobileServicesHelper.getInstance(context);
    mClient = amsh.getMobileServiceClient(context);
  }

  public void insertTable(Class<T> obj, T value) {
    mClient.getTable(obj).insert(value);
    Log.i(obj.getName(), "Nesne Eklendi");
  }
}
