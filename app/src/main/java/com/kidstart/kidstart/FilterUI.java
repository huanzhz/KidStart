package com.kidstart.kidstart;

import android.content.Intent;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import java.util.HashMap;

/**
 * This class implement the Filter method
 * @author HuanZhang
 */
public class FilterUI extends AppCompatActivity {

    public static final String FILTER_MESSAGE = "com.kidstart.kidstart.FILTERMESSAGE";

    CheckBox cbChinese, cbMalay, cbTamil;
    Button submitBtn;
    Boolean checkBoxTicked;

    private DisplayResultController displayResultController;

    HashMap<String,String> filterList = new HashMap<String, String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_interface);
        cbChinese = (CheckBox) findViewById(R.id.checkBoxChinese);
        cbMalay = (CheckBox) findViewById(R.id.checkBoxMalay);
        cbTamil = (CheckBox) findViewById(R.id.checkBoxTamil);

        submitBtn = (Button) findViewById(R.id.submitButton);
        checkBoxTicked = false;

        // System Back button enable
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //displayResultController = getIntent().getParcelableExtra("resultContr");
        displayResultController = getIntent().getExtras().getParcelable("resultContr");
    }

    /**
     * Compare the race to filter out the unwanted data.
     */
//    public void filterRace(){
//        // Loop through the array to see which is not suitable for the filter
//        for (int i = DisplayResultController.tempRecordList.size()-1; i >= 0; i--) {
//
//            // If the record is match do not remove it
//            // String[] checked=["1","1","0"];
//            if(DisplayResultController.tempRecordList.get(i).get("Chinese").equals(filterList.get("Chinese")) &&
//                    DisplayResultController.tempRecordList.get(i).get("Malay").equals(filterList.get("Malay")) &&
//                    DisplayResultController.tempRecordList.get(i).get("Tamil").equals(filterList.get("Tamil")) ){
//                continue;
//            } else {
//                DisplayResultController.recordList.remove(i);
//            }
//        }
//    }
    public void filterRace(){
        // Loop through the array to see which is not suitable for the filter
        for (int i = displayResultController.tempRecordList.size()-1; i >= 0; i--) {

            // If the record is match do not remove it
            // String[] checked=["1","1","0"];
            if(displayResultController.tempRecordList.get(i).get("Chinese").equals(filterList.get("Chinese")) &&
                    displayResultController.tempRecordList.get(i).get("Malay").equals(filterList.get("Malay")) &&
                    displayResultController.tempRecordList.get(i).get("Tamil").equals(filterList.get("Tamil")) ){
                continue;
            } else {
                displayResultController.recordList.remove(i);
            }
        }
    }

    /**
     * Go to Display Result UI.
     * @param view
     */
    public void goToListView(View view){
        // Create a copied of the original and remove them by filtering
//      DisplayResultController.recordCopy(DisplayResultController.tempRecordList, DisplayResultController.recordList);
        displayResultController.recordCopy(displayResultController.tempRecordList, displayResultController.recordList);

        if(cbChinese.isChecked()) {
            filterList.put("Chinese","1");
            checkBoxTicked = true;
        }else{
            filterList.put("Chinese","0");
        }
        if(cbMalay.isChecked()) {
            filterList.put("Malay","1");
            checkBoxTicked = true;
        }else{
            filterList.put("Malay","0");
        }
        if(cbTamil.isChecked()) {
            filterList.put("Tamil","1");
            checkBoxTicked = true;
        }else{
            filterList.put("Tamil","0");
        }

        // If the checkBox for races is ticked
        if(checkBoxTicked){
            filterRace();
        }

        Intent intent = new Intent(this, DisplayResultUI.class);
        if(checkBoxTicked) {
//            if(DisplayResultController.recordList.size() == 0){
//                // Create a copied of the original
//                DisplayResultController.recordCopy(DisplayResultController.tempRecordList, DisplayResultController.recordList);
//                intent.putExtra(FILTER_MESSAGE, true);
//            }
            if(displayResultController.recordList.size() == 0){
                // Create a copied of the original
                displayResultController.recordCopy(displayResultController.tempRecordList, displayResultController.recordList);
                intent.putExtra(FILTER_MESSAGE, true);
            }
        }else{
            intent.putExtra(FILTER_MESSAGE, false);
        }
        startActivity(intent);
    }
}
