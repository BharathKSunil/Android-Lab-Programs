//Do Not Forget to change the package
package com.bharathksunilk.serviceandnotificationdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_start).setOnClickListener(this);
        findViewById(R.id.btn_stop).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_start:
                Intent startServiceIntent=new Intent(this, MyNotificationService.class);
				//set the flag that the service is running
                startServiceIntent.putExtra("running", true);
                startService(startServiceIntent);
                break;
            case R.id.btn_stop:
                Intent stopServiceIntent=new Intent(this, MyNotificationService.class);
                stopService(stopServiceIntent);
                break;
        }
    }
}
