package com.raviteja.mobilitypirates.firebasepushnotification.utils;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;

import com.raviteja.mobilitypirates.firebasepushnotification.BuildConfig;
import com.raviteja.mobilitypirates.firebasepushnotification.R;

public class MyNotificationHelper {

    private static MyNotificationHelper myNotificationHelper;
    private static NotificationManager notificationManager;
    private Context mContext;

    public static MyNotificationHelper getInstance(Context mContext){
        if (myNotificationHelper==null){
            myNotificationHelper=new MyNotificationHelper();
            myNotificationHelper.createChannels(mContext);
        }
        return myNotificationHelper;
    }

    private void createChannels(Context mContext){
        notificationManager= (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
        this.mContext = mContext;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = mContext.getString(R.string.channel_name);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(mContext.getString(R.string.channel_id), name, importance);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
//            NotificationManager notificationManager = mContext.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }


    public void showNotificationMessage(String title, String message) {

        // Check for empty push message
        if (TextUtils.isEmpty(message))
            return;

        final NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(
                mContext, mContext.getString(R.string.channel_id));
        showSmallNotification(mBuilder, title, message);
    }

    private void showSmallNotification(NotificationCompat.Builder mBuilder, String title, String message) {
        String mGroupdId = BuildConfig.APPLICATION_ID + ".NOTIFICATION";

        Notification notification = mBuilder
                .setSmallIcon(R.mipmap.ic_launcher)
                .setAutoCancel(true)
                .setContentTitle(title)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.ic_launcher))
                .setContentText(message)
                .setChannelId(mContext.getString(R.string.channel_id))
                .setGroup(mGroupdId)
                .build();


        notificationManager.notify(0, notification);


    }

}
