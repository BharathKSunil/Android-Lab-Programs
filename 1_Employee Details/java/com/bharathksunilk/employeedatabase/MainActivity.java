//Change Package when pasting
package com.bharathksunilk.employeedatabase;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

public class MainActivity extends Activity implements View.OnClickListener{

    private EditText et_id, et_name, et_age, et_address;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        setActionBar(toolbar);

        //Reference all the edit texts here
        et_id=(EditText)findViewById(R.id.et_id);
        et_name=(EditText)findViewById(R.id.et_name);
        et_age=(EditText)findViewById(R.id.et_age);
        et_address=(EditText)findViewById(R.id.et_address);
        //listen to the Add Employee button click
        findViewById(R.id.btn_add).setOnClickListener(this);
    }

    //This method is used to inflate the menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }
    //This method responds to the item clicks on the menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        /*
        since we have only one item in our menu we omit
        the step of verifying which item was clicked
        switch (item.getItemId()){
            case R.id.menu_show_detail:
                break;
                .
                .
        }*/
        //take user to the search activity
        startActivity(new Intent(getBaseContext(), SearchEmployee.class));

        return true;
    }

    @Override
    public void onClick(View view) {
        if (!assertIfEmpty(et_id, et_name, et_age, et_address)){
            MyDatabase database=new MyDatabase(getBaseContext());
            database.addEmployee(
                    et_id.getText().toString(),
                    et_name.getText().toString(),
                    et_age.getText().toString(),
                    et_address.getText().toString()
            );
            Toast.makeText(
                    getBaseContext(),
                    "Employee Added Successfully",
                    Toast.LENGTH_LONG
            ).show();
        }else
            Toast.makeText(
                getBaseContext(),
                "Kindly fill all details",
                Toast.LENGTH_LONG
            ).show();
    }

    /**
     * This function checks if any of the editTexts are empty
     * @param editTexts the editTexts
     * @return true if any one of them is empty
     */
    static boolean assertIfEmpty(EditText...editTexts){
        for (EditText editText : editTexts)
            if (editText.getText().toString().isEmpty())
                return true;
        return false;
    }
}
