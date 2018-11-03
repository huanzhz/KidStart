package com.kidstart.kidstart;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

/**
 * This is the main class
 * @author HuanZhang
 */
public class MainActivity extends AppCompatActivity {

    public static final String MAIN_MESSAGE = "com.kidstart.kidstart.MAINMESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create a space/address for the record
        if(DisplayResultController.recordList == null){
            DisplayResultController.recordList = new ArrayList<>();
            DisplayResultController.tempRecordList = new ArrayList<>();
        }else{
            DisplayResultController.resetArray();
        }
    }

    public void displayResultView(View view){

        Intent intent = new Intent(this, DisplayResultUI.class);
        EditText editText = (EditText) findViewById(R.id.editText);
        String message = editText.getText().toString();
        intent.putExtra(MAIN_MESSAGE, message);
        startActivity(intent);
    }
}
