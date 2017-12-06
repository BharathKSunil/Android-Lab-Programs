package com.bharathksunilk.notes.viewer;

import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity implements View.OnClickListener{

    private TextView tv_result;
    private final String CONTENT_URI="content://com.bharathksunilk.notes";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_result = (TextView)findViewById(R.id.tv_result);

        findViewById(R.id.btn_get).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        //get the content from a database viz in another app using content provider of that app
        Cursor cursor = getContentResolver().query(Uri.parse(CONTENT_URI),
                null,
                null,
                null,
                null);

        if (cursor == null){
            tv_result.setText("Unable to connect to Notes Saver App");
            return;
        }

        StringBuilder result = new StringBuilder();
        while (cursor.moveToNext()){
            //append date
            result.append("Date: ");
            result.append(cursor.getString(0));
            //append content
            result.append("\n");
            result.append(cursor.getString(1));
            result.append("\n");
        }
        cursor.close();

        String res= result.toString().isEmpty() ? "No Notes found for today": result.toString();
        tv_result.setText(res);
    }
}
