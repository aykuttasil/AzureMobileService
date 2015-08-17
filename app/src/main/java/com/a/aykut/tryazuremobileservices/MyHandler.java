package com.a.aykut.tryazuremobileservices;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;

import com.microsoft.windowsazure.notifications.NotificationsHandler;

/**
 * Created by Aykut on 17.08.2015.
 */
public class MyHandler extends NotificationsHandler {

  public static final int NOTIFICATION_ID = 1;
  private NotificationManager mNotificationManager;
  Context ctx;

  @Override
  public void onRegistered(final Context context, final String gcmRegistrationId) {
    super.onRegistered(context, gcmRegistrationId);

    new AsyncTask<Void, Void, Void>() {

      protected Void doInBackground(Void... params) {
        try {
          AzureMobileServicesHelper.getMobileServiceClient(context).getPush().register(gcmRegistrationId, null);
          //MainActivity.mClient.getPush().register(gcmRegistrationId, null);
          return null;
        } catch (Exception e) {
          // handle error
        }
        return null;
      }
    }.execute();
  }

  @Override
  public void onReceive(Context context, Bundle bundle) {
    ctx = context;
    String nhMessage = bundle.getString("message");
    String nhTitle = bundle.getString("title");
    sendNotification(nhMessage, nhTitle);
  }

  private void sendNotification(String msg, String title) {
    mNotificationManager = (NotificationManager)
            ctx.getSystemService(Context.NOTIFICATION_SERVICE);

    PendingIntent contentIntent = PendingIntent.getActivity(ctx, 0,
            new Intent(ctx, Activity2.class), 0);

    NotificationCompat.Builder mBuilder =
            new NotificationCompat.Builder(ctx)
                    .setSmallIcon(R.drawable.common_signin_btn_icon_dark)
                    .setContentTitle(title)
                    .setStyle(new NotificationCompat.BigTextStyle()
                            .bigText(msg))
                    .setAutoCancel(true)
                    .setContentText(msg);


    mBuilder.setContentIntent(contentIntent);
    mNotificationManager.notify(NOTIFICATION_ID, mBuilder.build());
  }
}
