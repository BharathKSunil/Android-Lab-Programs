package com.bharathksunilk.notes.saver;

import android.app.Activity;
import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity implements View.OnClickListener{

    private EditText et_date, et_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_content=(EditText)findViewById(R.id.et_content);
        et_date=(EditText)findViewById(R.id.et_date);

        findViewById(R.id.btn_add_note).setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        String date=et_date.getText().toString();
        String content=et_content.getText().toString();

        ContentValues values = new ContentValues();
        values.put(DBHandler.COL_DATE, date);
        values.put(DBHandler.COL_CONTENT, content);
        //insert the data to the database via the content provider viz in the same app
        getContentResolver().insert(Uri.parse(NotesProvider.CONTENT_URI), values);
        Toast.makeText(getBaseContext(),"Data Inserted Successfully", Toast.LENGTH_LONG).show();
    }
}
