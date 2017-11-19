//Do Not Forget to change the package
package com.bharathksunilk.smsreceiver;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tv_number, tv_message;
        tv_number=(TextView)findViewById(R.id.tv_number);
        tv_message=(TextView)findViewById(R.id.tv_message);

        //if this activity is started by our SMSReceiver then show the sms
        Bundle bundle=getIntent().getBundleExtra(MySMSReceiver.EXTRA_MESSAGE_BUNDLE);
        if (bundle!=null){
            String number=bundle.getString(MySMSReceiver.BUNDLE_EXTRA_NUMBER);
            String content=bundle.getString(MySMSReceiver.BUNDLE_EXTRA_CONTENT);

            tv_number.setText(number);
            tv_message.setText(content);
        }

    }
}
