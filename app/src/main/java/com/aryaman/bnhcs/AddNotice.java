package com.aryaman.bnhcs;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;

import java.nio.channels.Channel;

import static android.app.NotificationManager.IMPORTANCE_DEFAULT;

public class AddNotice extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_notice);
        final EditText heading = (EditText) findViewById(R.id.heading);
        final EditText note = (EditText) findViewById(R.id.note);
        Button noticebutton = (Button) findViewById(R.id.noticebutton);
        Button gotonotice = (Button) findViewById(R.id.gotonotice);
        final String mheading = heading.getText().toString().trim();
        final String mnote = note.getText().toString().trim();

        /*if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel("BNHCS Notification", "BNHCS App", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }*/

        noticebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){

               /* NotificationChannel channel = null;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                    channel = new NotificationChannel("BNHCS Notification", "BNHCS App", NotificationManager.IMPORTANCE_DEFAULT);
                    NotificationManager manager = getSystemService(NotificationManager.class);
                    manager.createNotificationChannel(channel);
                }*/

               Notification builder = null;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                    Intent intent = new Intent(AddNotice.this, NoticeBoard.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.putExtra("mheading", heading.getText().toString().trim()+"");
                    intent.putExtra("mnote", note.getText().toString().trim()+"");
                    startActivity(intent);
                    PendingIntent pendingIntent = PendingIntent.getActivity(AddNotice.this,1, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                    NotificationChannel notificationChannel = new NotificationChannel("my_channel_01", "BNHCS", NotificationManager.IMPORTANCE_HIGH);
                    builder = new Notification.Builder(AddNotice.this).setSmallIcon(R.drawable.ic_baseline_message_24).setColor(ContextCompat.getColor(getApplicationContext(), R.color.btcolor)).setColorized(true).setContentTitle(heading.getText().toString().trim()).setContentText(note.getText().toString().trim()).setAutoCancel(true).setContentIntent(pendingIntent).setChannelId("my_channel_01").build();

                    NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                    notificationManager.createNotificationChannel(notificationChannel);
                    notificationManager.notify(1, builder);

                }



                /*NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                notificationManager.notify(1, builder.build());
                notificationManager.deleteNotificationChannel("channel1");*/
                Toast.makeText(AddNotice.this, "Message Sent", Toast.LENGTH_LONG).show();

            }
        });

        gotonotice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddNotice.this, NoticeBoard.class));
            }
        });

    }
}
