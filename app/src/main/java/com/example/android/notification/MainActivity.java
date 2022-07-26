package com.example.android.notification;

import static com.example.android.notification.App.CHANNEL_ID;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;


import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;

import android.graphics.BitmapFactory;
import android.graphics.Color;

import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private static int NOTIFY_ID = 2020;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.button).setOnClickListener(view -> {

            NotificationCompat.Builder builder = new NotificationCompat.Builder(this,CHANNEL_ID);


            builder.setSmallIcon(R.drawable.ic_launcher_background);
            builder.setContentTitle("Title");
            builder.setContentText("this is demo notification");

            //set Light
            builder.setLights(Color.YELLOW,200,200);

            //set sound
            Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            builder.setSound(soundUri);

            long[] vibrate = {100,500,100,500};
            builder.setVibrate(vibrate);


            //notification action
            Intent intent = new Intent(this,SecondActivity.class);
            intent.putExtra("key",NOTIFY_ID);
            @SuppressLint("UnspecifiedImmutableFlag") PendingIntent pendingIntent =PendingIntent.getActivity(this,123,intent,PendingIntent.FLAG_UPDATE_CURRENT);
            builder.setContentIntent(pendingIntent);


            //not recommended for action
            builder.addAction(R.mipmap.ic_launcher,"Action 1",pendingIntent);

            //recommended way for action
            NotificationCompat.Action.Builder actionBuilder = new NotificationCompat.Action.Builder(R.mipmap.ic_launcher,"Action 2",pendingIntent);
            NotificationCompat.Action action = actionBuilder.build();
            builder.addAction(action);

            //notification big text style
            NotificationCompat.BigTextStyle bigTextStyle = new NotificationCompat.BigTextStyle();
            bigTextStyle.setBigContentTitle("This is big text title");
            bigTextStyle.bigText("this is the big text that  will be shown in expanded notification view");
            builder.setStyle(bigTextStyle);

            //notification big text style
            NotificationCompat.BigPictureStyle bigPictureStyle = new NotificationCompat.BigPictureStyle();
            bigPictureStyle.bigPicture(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher_round));
            builder.setStyle(bigPictureStyle);



            //set Notification
            Notification notification = builder.build();
            NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            manager.notify(NOTIFY_ID,notification);
            NOTIFY_ID++;

        });
    }
}