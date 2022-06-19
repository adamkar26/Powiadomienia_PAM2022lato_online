package com.example.powiadomienia;

import androidx.appcompat.app.AppCompatActivity;

import android.app.NotificationManager;
import android.app.RemoteInput;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Druga extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_druga);

        Intent intent = getIntent();
        Bundle bundle = RemoteInput.getResultsFromIntent(intent);

        if(bundle != null){
            TextView textView = findViewById(R.id.textView);
            textView.setText(bundle.getString(MainActivity.klucz));

            NotificationManager notificationManager = (NotificationManager)
                    getSystemService(Context.NOTIFICATION_SERVICE);

            notificationManager.cancel(MainActivity.notifiactionId);
        }
    }
}