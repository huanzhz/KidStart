package com.kidstart.kidstart;

import android.content.Intent;
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
//    private FilterController filterController = new FilterController();

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

        displayResultController = SingletonManager.getDisplayResultControllerInstance();
    }

    /**
     * Go to Display Result UI.
     * @param view
     */
    public void goToListView(View view){
        // Create a copied of the original and remove them by filtering
        displayResultController.recordCopy(displayResultController.getTempRecordList(), displayResultController.getRecordList());

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
            //filter will be managed by displayResultController
            displayResultController.filter(filterList);
        }

//        Intent intent = new Intent(this, DisplayResultUI.class);
        Intent intent = new Intent();
        if(checkBoxTicked) {
            if(displayResultController.getRecordList().size() == 0){
                // Create a copied of the original
                displayResultController.recordCopy(displayResultController.getTempRecordList(), displayResultController.getRecordList());
            }
        }
        setResult(RESULT_OK, intent);
        finish();
    }
}
