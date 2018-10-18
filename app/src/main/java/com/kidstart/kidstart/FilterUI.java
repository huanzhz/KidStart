package com.kidstart.kidstart;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import java.util.HashMap;

public class FilterUI extends AppCompatActivity {

    CheckBox cbChinese, cbMalay, cbTamil;
    Button submitBtn;

    HashMap<String,String> filterList = new HashMap<String, String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_interface);
        cbChinese = (CheckBox) findViewById(R.id.checkBoxChinese);
        cbMalay = (CheckBox) findViewById(R.id.checkBoxMalay);
        cbTamil = (CheckBox) findViewById(R.id.checkBoxTamil);

        submitBtn = (Button) findViewById(R.id.submitButton);

        // System Back button enable
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }

    public void goToListView(View view){
        if(cbChinese.isChecked()) {
            filterList.put("Chinese","1");
        }else{
            filterList.put("Chinese","0");
        }
        if(cbMalay.isChecked()) {
            filterList.put("Malay","1");
        }else{
            filterList.put("Malay","0");
        }
        if(cbTamil.isChecked()) {
            filterList.put("Tamil","1");
        }else{
            filterList.put("Tamil","0");
        }

        Intent intent = new Intent(this, DisplayResultUI.class);
        Bundle extras = new Bundle();
        extras.putSerializable("filterHashMapMessage", filterList);
        extras.putString("filter","FilterPage");
        intent.putExtras(extras);
        startActivity(intent);
    }
}
