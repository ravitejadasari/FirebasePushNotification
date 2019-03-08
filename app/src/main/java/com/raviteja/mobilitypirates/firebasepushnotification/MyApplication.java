package com.raviteja.mobilitypirates.firebasepushnotification;

import android.app.Application;
import android.util.Log;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.FirebaseMessaging;
import com.raviteja.mobilitypirates.firebasepushnotification.utils.MyNotificationHelper;

public class MyApplication extends Application {

    private static final String TAG = MyApplication.class.getSimpleName() + "-->";

    private static MyApplication mInstance;
    private MyNotificationHelper myNotificationHelper;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        FirebaseMessaging.getInstance().setAutoInitEnabled(true);
        myNotificationHelper = MyNotificationHelper.getInstance(this);
        mGenerateFCMId();
    }

    private void mGenerateFCMId() {
        FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(new OnSuccessListener<InstanceIdResult>() {
            @Override
            public void onSuccess(InstanceIdResult instanceIdResult) {
                String newToken = instanceIdResult.getToken();
                Log.d(TAG, "onSuccess: " + newToken);
            }
        });
    }

    public static synchronized MyApplication getInstance() {
        return mInstance;
    }

    public MyNotificationHelper getNotificationHelper() {
        return myNotificationHelper;
    }

}
