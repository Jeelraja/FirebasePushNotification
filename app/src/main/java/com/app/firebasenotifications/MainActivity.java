package com.app.firebasenotifications;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnGetNotification;
    MyBroadcastReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnGetNotification = findViewById(R.id.btnGetNotification);
        Intent intent = getIntent();
        if (getIntent().getExtras() != null) {
            for (String key : getIntent().getExtras().keySet()) {
                Object value = getIntent().getExtras().get(key);
                Log.d("MainActivity: ", "Key: " + key + " Value: " + value);
                if (intent != null) {
                    String strMsg = intent.getStringExtra("data");
                    Log.d("MainActivity: ", "strMsg: " + strMsg);
                    btnGetNotification.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                    btnGetNotification.setText(strMsg);
                }
            }
        }
    }

    public void changeButton(String strMsg) {
        btnGetNotification.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        btnGetNotification.setText(strMsg);
    }


    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.app.firebasenotifications");
        receiver = new MyBroadcastReceiver();
        registerReceiver(receiver, intentFilter);
    }

    @Override
    public void onPause() {
        super.onPause();
        unregisterReceiver(receiver);
    }

    public class MyBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle extras = intent.getExtras();
            String msg = extras.getString("MSG");
            changeButton(msg);// update your textView in the main layout
        }
    }
}
