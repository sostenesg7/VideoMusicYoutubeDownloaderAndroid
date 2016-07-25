package com.gomes.sostenes.videomusicyoutubedownloaderandroid;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.widget.RemoteViews;

/**
 * Created by Sostenes on 25/07/2016.
 */
public class NotificationAV {

    private static Context context;
    private NotificationManager notificationManager;
    private NotificationCompat.Builder notificationBuilder;
    private static NotificationAV notificationAV;

    private NotificationAV() {}

    public static NotificationAV init(Context cont){
        context = cont;
        if(notificationAV == null) {
            notificationAV = new NotificationAV();
        }
        return notificationAV;
    }

    public static NotificationAV getInstance(){
        return notificationAV;
    }

    public void newNotification(int id, String msg, String title){

        notificationBuilder = new NotificationCompat.Builder(context);
        notificationBuilder.setSmallIcon(R.drawable.ic_add_black_24dp);
        notificationBuilder.setContentTitle("TITULO");
        notificationBuilder.setContentText("TEXTO");

        notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        //if(Build.VERSION.CODENAME.equals(Build.VERSION_CODES.GINGERBREAD)) {}

        PendingIntent pi = PendingIntent.getBroadcast(context, 0, new Intent(), PendingIntent.FLAG_UPDATE_CURRENT);
        Notification notification = new NotificationCompat.Builder(context).setAutoCancel(true)
                .setContentTitle(title)
                .setContentText(msg)
                .setContentIntent(pi)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setWhen(System.currentTimeMillis())
                //.setProgress(100, 0, false)

                .setTicker("Download iniciado.")
                .build();

        notificationManager.notify(id, notification);
    }

    public void updateNotification(int id,int progress){
        notificationBuilder.setProgress(100, progress, false);
        notificationManager.notify(id, notificationBuilder.build());


    }

}
