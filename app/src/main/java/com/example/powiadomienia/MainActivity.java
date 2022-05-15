package com.example.powiadomienia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    protected String CHANNEL_ID = "1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // tworze kanal notyfikacji
        createNotificationChannel();
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getBaseContext(), Druga.class);
                PendingIntent pendingIntent = PendingIntent.getActivity(getBaseContext(),
                        0, intent,
                        PendingIntent.FLAG_UPDATE_CURRENT);

                // akcja
                NotificationCompat.Action action = new NotificationCompat.Action.Builder(
                        android.R.drawable.sym_action_chat, "Otwórz", pendingIntent
                ).build();

                //dzownek (domyslny dzwiek powiadomienia)
                Uri dzownek = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

                // powiadomienie
                Notification notification = new NotificationCompat.Builder(getBaseContext(), CHANNEL_ID)
                        .setSmallIcon(android.R.drawable.ic_dialog_info)
                        .setContentTitle("Powiadomienie")
                        .setContentText("Wiadmość powiadomienia")
                        .setSound(dzownek)
                        .setContentIntent(pendingIntent)
                        .addAction(action)
                        .build();

                NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                int id = 1;
                notificationManager.notify(id, notification);
            }
        });
    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = CHANNEL_ID;
            String description = "kanal notyfikacji";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}