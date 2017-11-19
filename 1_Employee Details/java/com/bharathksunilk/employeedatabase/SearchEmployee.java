package com.bharathksunilk.employeedatabase;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SearchEmployee extends Activity implements View.OnClickListener{

    private EditText et_id;
    private TextView tv_searchResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_employee);

        et_id=(EditText)findViewById(R.id.et_id);
        tv_searchResult=(TextView)findViewById(R.id.tv_search_result);

        findViewById(R.id.btn_search).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (!MainActivity.assertIfEmpty(et_id)){
            MyDatabase database=new MyDatabase(getBaseContext());
            tv_searchResult.setText(database.getEmployeeById(et_id.getText().toString()));
        }else Toast.makeText(
                getBaseContext(),
                "Kindly Enter a Employee ID",
                Toast.LENGTH_LONG
        ).show();
    }
}
