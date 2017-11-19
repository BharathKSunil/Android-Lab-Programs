//Do Not Forget to change the package
package com.bharathksunilk.mydailerapp;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity implements View.OnClickListener{

    private EditText et_number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_number=(EditText)findViewById(R.id.et_number);

        setClickListener(
                R.id.btn_backspace,
                R.id.btn_add,
                R.id.btn_call,
                R.id.btn_zero,
                R.id.btn_one,
                R.id.btn_two,
                R.id.btn_three,
                R.id.btn_four,
                R.id.btn_five,
                R.id.btn_six,
                R.id.btn_seven,
                R.id.btn_eight,
                R.id.btn_nine,
                R.id.btn_star,
                R.id.btn_hash)
        );
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_add: addContact();
                break;
            case R.id.btn_backspace: backspace();
                break;
            case R.id.btn_call: makeCall();
                break;
            case R.id.btn_zero: et_number.append("0");
                break;
            case R.id.btn_one: et_number.append("1");
                break;
            case R.id.btn_two: et_number.append("2");
                break;
            case R.id.btn_three: et_number.append("3");
                break;
            case R.id.btn_four: et_number.append("4");
                break;
            case R.id.btn_five: et_number.append("5");
                break;
            case R.id.btn_six: et_number.append("6");
                break;
            case R.id.btn_seven: et_number.append("7");
                break;
            case R.id.btn_eight: et_number.append("8");
                break;
            case R.id.btn_nine: et_number.append("9");
                break;
            case R.id.btn_star: et_number.append("*");
                break;
            case R.id.btn_hash: et_number.append("#");
                break;
        }
    }

    //this function adds a contact to phone contacts
    private void addContact(){
        String num=et_number.getText().toString();
		//TODO: Check if the text is empty or invalid
        Intent intent = new Intent(Intent.ACTION_INSERT, ContactsContract.Contacts.CONTENT_URI);
        intent.putExtra(ContactsContract.Intents.Insert.PHONE,num); //pass the contact to the contacts Application in the intent
        startActivity(intent);
    }

    //this function deletes one entry
    private void backspace(){
        String num=et_number.getText().toString();
        if(!num.isEmpty())
        {
            num=num.substring(0,num.length()-1);
            et_number.setText(num);
        }

    }
    //this function makes the call
    private void makeCall(){
        String num=et_number.getText().toString();
		//TODO: Check if the text is empty or invalid
        if (num.isEmpty())return;
        Intent it=new Intent(Intent.ACTION_CALL);
        it.setData(Uri.parse("tel:"+num));
        try {
            startActivity(it);
        }catch (SecurityException e){
            Toast.makeText(getBaseContext(), "Permission not granted ",Toast.LENGTH_LONG).show();
        }
    }
    //This function adds onClickListener for all the views with the ids
    private void setClickListener(int...ids){
        for (int id : ids)
            findViewById(id).setOnClickListener(this);
    }
}
