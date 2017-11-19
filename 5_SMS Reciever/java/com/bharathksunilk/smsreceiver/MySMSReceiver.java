package com.bharathksunilk.smsreceiver;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;

import static android.content.Context.NOTIFICATION_SERVICE;

public class MySMSReceiver extends BroadcastReceiver {

    public static final String BUNDLE_EXTRA_NUMBER = "number";
    public static final String BUNDLE_EXTRA_CONTENT = "content";
    public static final String EXTRA_MESSAGE_BUNDLE = "message";

    @Override
    public void onReceive(Context context, Intent intent) {

        //Get bundle from the broadcast sms intent
        Bundle bundle = intent.getExtras();
        if (bundle == null) return;

        //A PDU is “protocol data unit”, which is the industry format for an SMS message.
        Object[] pdus = (Object[])bundle.get("pdus");
        if (pdus==null)return;

        //Extract the sms from pdu
        final SmsMessage[] messages = new SmsMessage[pdus.length];
        for (int i = 0; i < pdus.length; i++)
            messages[i] = SmsMessage.createFromPdu((byte[])pdus[i]);
        if (messages.length > -1)
            sendNotification(context, messages[0].getOriginatingAddress(), messages[0].getMessageBody());
    }

    //This Function sets the Notification
    private void sendNotification(Context context, String sender, String content){

        //parcel the sms into a bundle, ready to be shipped/sent to the activity
        Bundle messageBundle=new Bundle();
        messageBundle.putString(BUNDLE_EXTRA_NUMBER, sender);
        messageBundle.putString(BUNDLE_EXTRA_CONTENT, content);
        Intent intent=new Intent(context, MainActivity.class);
        intent.putExtra(EXTRA_MESSAGE_BUNDLE, messageBundle);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        //Create and show the notification
        NotificationManager manager=(NotificationManager)context.getSystemService(NOTIFICATION_SERVICE);
        Notification.Builder builder=new Notification.Builder(context);
        builder.setContentTitle(sender);
        builder.setContentText(content);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setContentIntent(PendingIntent.getActivity(context, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT));
        Notification nof=builder.build();
        manager.notify(100, nof);
    }
}
