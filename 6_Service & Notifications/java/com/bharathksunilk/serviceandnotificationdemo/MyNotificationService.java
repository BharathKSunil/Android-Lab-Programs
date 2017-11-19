//Do Not Forget to change the change the package
package com.bharathksunilk.serviceandnotificationdemo;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.widget.Toast;

public class MyNotificationService extends Service {
    public MyNotificationService() {
    }

    boolean running=false;
    MyThread thread;

    @Override
    public void onCreate()
    {
        super.onCreate();
        Toast.makeText(getBaseContext(), "Service Created", Toast.LENGTH_SHORT).show();
        running=true;
        thread=new MyThread();
        thread.start();
    }

    @Override
    public int onStartCommand(Intent intent, int flags,int startId)
    {
        super.onStartCommand(intent, flags, startId);
        Toast.makeText(getBaseContext(), "Service started", Toast.LENGTH_SHORT).show();
        running=intent.getBooleanExtra("running", true);
        if(!thread.isAlive())
        {
            thread=new MyThread();
            thread.start();
        }
        return Service.START_NOT_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy()
    {
        running=false;
        Toast.makeText(getBaseContext(), "Service stopped", Toast.LENGTH_SHORT).show();
        super.onDestroy();
    }

    Handler hand=new Handler()
    {
        public void handleMessage(Message m)
        {
            NotificationManager manager=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
            Notification.Builder builder=new Notification.Builder(getBaseContext());
            builder.setContentTitle("From Service");
            builder.setContentText("Hii " +m.what);
            builder.setSmallIcon(R.mipmap.ic_launcher);
            builder.setContentIntent(PendingIntent.getActivity(getBaseContext(), 1, new Intent(getBaseContext(),MainActivity.class),1));
            Notification nof=builder.build();
            manager.notify(200, nof);
        }
    };

    class MyThread extends Thread {
        public void run() {
            int i = 0;
            while (running) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                hand.sendEmptyMessage(i++);
            }
        }
    }
}
