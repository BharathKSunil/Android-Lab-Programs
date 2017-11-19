//Do Not Forget to change your package
package com.bharathksunilk.counterapp;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity implements View.OnClickListener, Runnable{

    int counter;
    private TextView tv_counter;
    private boolean running;//this is a flag which is used to stop the thread

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        running=false;
        tv_counter=(TextView)findViewById(R.id.tv_counter);
        findViewById(R.id.btn_start).setOnClickListener(this);
        findViewById(R.id.btn_stop).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_start:
                running=true;
                Thread thread = new Thread(this);
                thread.start();
                break;
            case R.id.btn_stop:
				tv_counter.setText("Stopped at: "+i);
                running=false;
                break;

        }
    }
    Handler hand=new Handler()
    {
        public void handleMessage(Message m)
        {
            tv_counter.setText(""+m.what);
        }
    };

    @Override
    public void run() {
        while(counter <100 && running)
        {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
			//A thread must not directly update the UI as anytime the main/UI thread can stop or be paused
			//hence we use a Handler
            hand.sendEmptyMessage(counter);
            counter++;
        }
    }
}
