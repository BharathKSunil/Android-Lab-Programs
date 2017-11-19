package com.bharathksunilk.mycalculator;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements View.OnClickListener, ServiceConnection {

    private EditText et_first, et_second;
    private TextView tv_result;
    private IMyCalService myCalService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_first =(EditText)findViewById(R.id.et_first);
        et_second =(EditText)findViewById(R.id.et_second);
        tv_result =(TextView)findViewById(R.id.tv_result);

        setClickListener(R.id.btn_add, R.id.btn_sub, R.id.btn_mul);

        Intent intent = new Intent(this, MyCalService.class);
        bindService(intent, this, BIND_AUTO_CREATE);
    }

    @Override
    public void onClick(View v) {
        int a, b;
        String s1= et_first.getText().toString();
        String s2= et_second.getText().toString();
        try {
            a=Integer.parseInt(s1);
            b=Integer.parseInt(s2);
        }catch (NumberFormatException e){
            Toast.makeText(this, "Please Enter Correct Input", Toast.LENGTH_SHORT).show();
            return;
        }

        switch (v.getId()){
            case R.id.btn_add:
                try {
                    int result=myCalService.add(a,b);
                    String res=a+" + "+b+" = "+result;
                    tv_result.setText(res);
                } catch (Exception e) {
                    Toast.makeText(this, "Unable to connect to service", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
                break;
            case R.id.btn_sub:
                try {
                    int result=myCalService.sub(a,b);
                    String res=a+" - "+b+" = "+result;
                    tv_result.setText(res);
                } catch (Exception e) {
                    Toast.makeText(this, "Unable to connect to service", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
                break;
            case R.id.btn_mul:
                try {
                    int result=myCalService.mul(a,b);
                    String res=a+" * "+b+" = "+result;
                    tv_result.setText(res);
                } catch (Exception e) {
                    Toast.makeText(this, "Unable to connect to service", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
                break;
        }
    }

    //This function is called when the binder service is connected to the activity
    @Override
    public void onServiceConnected(ComponentName name, IBinder iBinder) {
        Toast.makeText(getBaseContext(), "Service Connected", Toast.LENGTH_LONG).show();
        myCalService=IMyCalService.Stub.asInterface(iBinder);
    }

    //This method is called when the service gets disconnected from the activity
    @Override
    public void onServiceDisconnected(ComponentName name) {
    }

    //This function adds onClickListener for all the views with the ids
    private void setClickListener(int...ids){
        for (int id : ids)
            findViewById(id).setOnClickListener(this);
    }
}
