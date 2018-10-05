package com.example.gmcalixto.exemplonotification;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.drawable.Icon;
import android.media.RingtoneManager;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void notify(View view){
        NotificationManager notif=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);

        EditText titulo = findViewById(R.id.txtTitulo);
        EditText texto = findViewById(R.id.txtTexto);

        Notification notify=new Notification.Builder(getApplicationContext())
                .setContentTitle(titulo.getText().toString())
                .setContentText(texto.getText().toString())
                .setAutoCancel(true)
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                .setPriority(Notification.PRIORITY_HIGH)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.drawable.ic_stat_name).build();

        //notify.flags |= Notification.BADGE_ICON_NONE; //API 26
        notif.notify(0, notify);

    }
}
