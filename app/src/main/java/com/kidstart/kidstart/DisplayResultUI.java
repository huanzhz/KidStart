package com.kidstart.kidstart;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observer;
import java.util.Observable;

/**
 * This is the result display class.
 * @author HuanZhang
 */
<<<<<<< HEAD
public class DisplayResultUI extends AppCompatActivity implements Parcelable, Observer{

=======
public class DisplayResultUI extends AppCompatActivity{
>>>>>>> ebe0c1cb54d6266635877809a916d7eaed770001

    public static ListView displayResultListView;
    private Button mySortButton;
    private boolean filterBool;
    private String titleString;
    private DisplayResultController displayResultController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_result_ui);

        // System naming
        //getSupportActionBar().setTitle("Hello World!");
        // System Back button enable
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Initialise the button and variables
        displayResultListView = (ListView) findViewById(R.id.listView);
        filterBool = false;

        //Check for incoming activity
//        Intent intent = getIntent();
//        if(intent.getExtras() != null) {
//            if(intent.getExtras().containsKey(FilterUI.FILTER_MESSAGE)){
//                filterBool = intent.getExtras().getBoolean(FilterUI.FILTER_MESSAGE);
//            }
//            if(intent.getExtras().containsKey(MainActivity.MAIN_MESSAGE)){
//                titleString = intent.getExtras().getString(MainActivity.MAIN_MESSAGE);
//            }
//        }
        Intent intent = getIntent();
        //replace !=null to onActivityResult()
        if(intent.getExtras() != null) {
            if(intent.getExtras().containsKey(MainActivity.MAIN_MESSAGE)){
                titleString = intent.getExtras().getString(MainActivity.MAIN_MESSAGE);
            }
        }

        displayResultController = singletonManager.getDisplayResultControllerInstance(DisplayResultUI.this, titleString, DisplayResultUI.this);

        if(displayResultController.getRecordList().size() == 0 && displayResultController.getTempRecordList().size() == 0) {
            // Create a new object to fetch the data
            displayResultController.collateResult();
        }else {
            updateListView(displayResultController.getRecordList());
            // If there is no record show a pop up
            if(filterBool) {
                // Popup show no result found
                FailureDialog exampleDialog = new FailureDialog();
                exampleDialog.show(getSupportFragmentManager(), "example dialog");
            }
        }

//        // Add in record if it is empty else update the view
//        if(DisplayResultController.recordList.size() == 0 && DisplayResultController.tempRecordList.size() == 0){
//            // Create a new object to fetch the data
//            DisplayResultController.collateResult(DisplayResultUI.this, titleString, DisplayResultUI.this);
//            // Similar to this code - "
//            //      APIController process = new APIController(DisplayResultUI.this);
//            //      process.execute();
//            // "
//        }else {
//            updateListView(DisplayResultController.recordList);
//            // If there is no record show a pop up
//            if(filterBool) {
//                // Popup show no result found
//                FailureDialog exampleDialog = new FailureDialog();
//                exampleDialog.show(getSupportFragmentManager(), "example dialog");
//            }
//        }

        // Button
        // Display more infomation about the centre
        displayResultListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                HashMap<String, String> selectedRecord = displayResultController.getRecordList().get(position);
                //HashMap<String, String> selectedRecord = DisplayResultController.recordList.get(position);

                Intent intent = new Intent(DisplayResultUI.this, DetailedInformationUI.class);

                intent.putExtra("hashMapMessage", selectedRecord);
                startActivity(intent);
                 }
            }
        );

        mySortButton = findViewById(R.id.sortButton);
        mySortButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(SortByName.sortData()) {
                    updateListView(displayResultController.getRecordList());
                    //updateListView(DisplayResultController.recordList);
                }
            }
        });
    }

    /*
    get FILTER_MESSAGE from FilterUI
     */
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if(resultCode == RESULT_OK) {
                filterBool = data.getBooleanExtra(FilterUI.FILTER_MESSAGE, false);
            }
        }
    }

    // Click back button
    public boolean onOptionsItemSelected(MenuItem item){
        displayResultController.resetArray();
        Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivityForResult(myIntent, 0);
        return true;
    }

    //Override method in ListResultObserver, and do something if Subject notifies
    @Override
    public void update(Observable observable, Object o){
        //TODO
        if (observable instanceof DisplayResultController) {
            updateListView();
        }
    }

    public void updateListView(){
        ListAdapter adapter = new SimpleAdapter(
                DisplayResultUI.this, displayResultController.getRecordList(),
                R.layout.school_listing, new String[]{"centreName", "centreAddress", "testID", "secondLanguagesOffered"},
                new int[]{R.id.name, R.id.location, R.id.operationhour, R.id.test1});

        displayResultListView.setAdapter(adapter);
    }

    public void updateListView(ArrayList arrayList){
        // Update jason data to listview
        // SimpleAdapter(Context context, List<? extends Map<String, ?>> data, int resource, String[] from, int[] to)
        ListAdapter adapter = new SimpleAdapter(
                DisplayResultUI.this, arrayList,
                R.layout.school_listing, new String[]{"centreName", "centreAddress", "testID", "secondLanguagesOffered"},
                new int[]{R.id.name, R.id.location, R.id.operationhour, R.id.test1});

        displayResultListView.setAdapter(adapter);
    }

    public void goToFilterView(View view){
        if(displayResultController.getRecordList().size() != 0) {
            Intent intent = new Intent(this, FilterUI.class);
            //startActivity(intent);
            startActivityForResult(intent, 1);
        }

//        if(DisplayResultController.recordList.size() != 0) {
//            Intent intent = new Intent(this, FilterUI.class);
//            startActivity(intent);
//        }
    }

}
