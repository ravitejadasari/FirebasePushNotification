package com.raviteja.mobilitypirates.firebasepushnotification.messagingservice;

import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.raviteja.mobilitypirates.firebasepushnotification.MyApplication;
import com.raviteja.mobilitypirates.firebasepushnotification.utils.MyNotificationHelper;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = MyFirebaseMessagingService.class.getName();
    private MyNotificationHelper mMyNotificationHelper;

    @Override
    public void onNewToken(String token) {
        super.onNewToken(token);
        System.out.println("Token" + token);
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        if (remoteMessage!=null){
            Log.e(TAG, "Notification Body: " + remoteMessage.getNotification().getBody());
            handleNotification(remoteMessage.getNotification().getTitle(),remoteMessage.getNotification().getBody());
        }

        mMyNotificationHelper = MyApplication.getInstance().getNotificationHelper();
        mMyNotificationHelper.showNotificationMessage("Sample", "hi how r you");

        Log.d(TAG, "onMessageReceived: " + "Title : " + remoteMessage.getNotification().getTitle() + "Body : " + remoteMessage.getNotification().getBody());


    }

    private void handleNotification(String title, String body) {

            // app is in foreground, broadcast the push message
            Intent pushNotification = new Intent("pushNotification");
            pushNotification.putExtra("title", title);
            pushNotification.putExtra("body", body);
            LocalBroadcastManager.getInstance(this).sendBroadcast(pushNotification);



    }


}
