package com.bharathksunilk.textcreator;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * In this class we are trying to perform read and write operations on android
 */
public class MainActivity extends Activity implements View.OnClickListener{

    TextView tv_file;
    EditText et_content;
    private String filePath;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_file=(TextView)findViewById(R.id.tv_file);
        et_content=(EditText)findViewById(R.id.et_content);

        setViewClickListeners(R.id.btn_create, R.id.btn_open, R.id.btn_save);
    }

    /**
     * This function attaches a click listener to this activity
     * @param ids these are the item id's to which are registered to click events
     */
    private void setViewClickListeners(int...ids){
        for(int id : ids){
            findViewById(id).setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_create:
                createFileCreateDialogue();
                break;
            case R.id.btn_open:
                //createFileOpenDialogue();//todo: use this while using internal storage
                openFile();//use this while using external storage
                break;
            case R.id.btn_save:
                writeContentToFile(et_content.getText().toString());
                break;
        }

    }

    //todo remove this method if you are using internal storage
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK)
        {
            filePath=data.getData().getPath();
            readContentsFromFile();
            tv_file.setText(filePath);
        }
        else
        {
            Toast.makeText(this,"Wrong Choice of File", Toast.LENGTH_LONG).show();
        }
    }

    void createFile(String filename){
        //external storage to documents folder
        filePath= Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS).getPath()+"/";
        //filePath = getFilesDir().getPath()+"/";//todo: use this when using internal storage
        Log.i("BKS", filePath);
        File f=new File(filePath +=filename);
        try {
            if (f.createNewFile()){
                Toast.makeText(MainActivity.this,
                        "New File created in External storage",
                        Toast.LENGTH_SHORT).show();
                tv_file.setText(filePath);
            }else {
                Toast.makeText(MainActivity.this,
                        "The File Already Exists",
                        Toast.LENGTH_SHORT).show();
            }
        } catch (IOException e) {
            e.printStackTrace();
            Log.d("BKS", filePath);
            Toast.makeText(getBaseContext(), ""+e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * This function creates a dialog to enter the filename you want to create
     */
    void createFileCreateDialogue(){
        final View dialogView=getLayoutInflater().inflate(R.layout.dialog_filename, null);
        final EditText text=(EditText)dialogView.findViewById(R.id.et_filename);
        new AlertDialog.Builder(this).setView(dialogView)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        if (text.getText().toString().isEmpty())
                            Toast.makeText(MainActivity.this,
                                    "No Filename Entered",
                                    Toast.LENGTH_LONG).show();
                        else {
                            createFile(text.getText().toString());
                        }
                    }
                })
                .create()
                .show();
    }

    /**
	 * todo remove this method if you are using internal storage
     * This function creates an intent to connect to the device's file viewer to choose a file
     */
    void openFile(){
        Intent intent=new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("text/plain");
        startActivityForResult(intent, 0);
    }

    /**
     * This function opens the file from the internal storage
     * @param filename the name of the file to be opened
     */
    void openFile(String filename){
        filePath=getFilesDir().getPath()+"/"+filename;
        readContentsFromFile();
    }

    /**
     * This function creates a dialog to enter the name of the file the user is trying to open
     * This method is only required when you are working with internal storage
     * as the FileViewer doesn't have permission to view files in your apps internal storage directory
     */
    void createFileOpenDialogue(){
        final View dialogView=getLayoutInflater().inflate(R.layout.dialog_filename, null);
        final EditText text=(EditText)dialogView.findViewById(R.id.et_filename);
        new AlertDialog.Builder(this).setView(dialogView)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        if (text.getText().toString().isEmpty())
                            Toast.makeText(MainActivity.this,
                                    "No Filename Entered",
                                    Toast.LENGTH_LONG).show();
                        else {
                            openFile(text.getText().toString());
                        }
                    }
                })
                .create()
                .show();
    }

    /**
     * This function reads contents from the file whose path is in filePath and sets its contents in the editText
     */
    void readContentsFromFile(){
        try {
            FileInputStream fileIn=new FileInputStream(filePath);
            InputStreamReader inputReader= new InputStreamReader(fileIn);
            BufferedReader br=new BufferedReader(inputReader);
            String data=br.readLine();
            et_content.setText("");
            while(data!=null)
            {
                et_content.append(data+"\n");
                data=br.readLine();
            }
            br.close();
            fileIn.close();
            inputReader.close();
        } catch (Exception e) {
            Toast.makeText(getBaseContext(),e.getLocalizedMessage(),
                    Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * This function writes contents to the file
     * @param content the content in the EditText Field
     */
    void writeContentToFile(String content){
        try {
            FileOutputStream fileOut=new FileOutputStream(new File(filePath));
            fileOut.write(content.getBytes());
            fileOut.close();
            //display file saved message
            Toast.makeText(getBaseContext(), "File Saved successfully!",
                    Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(getBaseContext(), e.getLocalizedMessage(),
                    Toast.LENGTH_SHORT).show();
        }
    }

}
