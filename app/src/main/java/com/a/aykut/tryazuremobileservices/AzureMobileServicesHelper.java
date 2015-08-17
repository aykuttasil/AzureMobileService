package com.a.aykut.tryazuremobileservices;

import android.content.Context;

import com.microsoft.windowsazure.mobileservices.MobileServiceClient;
import com.microsoft.windowsazure.notifications.NotificationsManager;

/**
 * Created by Aykut on 15.08.2015.
 */
public class AzureMobileServicesHelper {
  private static AzureMobileServicesHelper instance;
  private static MobileServiceClient mClient;
  private static Context context;


  private AzureMobileServicesHelper(Context context) {

    try {
      this.context = context;
      mClient = new MobileServiceClient(
              "https://mymobileservicesaykut.azure-mobile.net/",
              "APPID",
              context);
      LogHelper.setLogInfo(context, "Azure Mobile Services Bağlandı");

    } catch (Exception e) {


    }
  }

  public static AzureMobileServicesHelper getInstance(Context context) {
    if (instance == null) {
      instance = new AzureMobileServicesHelper(context);
      LogHelper.setLogInfo(context, "Azure Mobile Services Instance oluşturuldu");
    }
    return instance;

  }

  public static MobileServiceClient getMobileServiceClient(Context context) {
    if (mClient == null) {
      AzureMobileServicesHelper.getInstance(context);
      LogHelper.setLogInfo(context, "getMobileServiceClient() çağrıldı");
    }
    return mClient;
  }


}
