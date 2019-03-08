package com.raviteja.mobilitypirates.firebasepushnotification;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.raviteja.mobilitypirates.firebasepushnotification.messagingservice.MyFirebaseMessagingService;

public class MainActivity extends AppCompatActivity {

    TextView tvTitle, tvBody;
    private BroadcastReceiver mRegistrationBroadcastReceiver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvTitle = findViewById(R.id.tvTitle);
        tvBody = findViewById(R.id.tvBody);

        mRegistrationBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                if (intent.getAction().equals("pushNotification")){
                    // new push notification is received
                    String title = intent.getStringExtra("title");
                    String body = intent.getStringExtra("body");
                    Toast.makeText(getApplicationContext(), "Push notification: " + title, Toast.LENGTH_LONG).show();
                    tvTitle.setText(title);
                    tvBody.setText(body);
                }
            }
        };

        tvTitle.setText("");
        tvBody.setText("");


    }
}
