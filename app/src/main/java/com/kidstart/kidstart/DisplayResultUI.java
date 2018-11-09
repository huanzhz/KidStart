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
public class DisplayResultUI extends AppCompatActivity implements Observer{

    public static ListView displayResultListView;
    private Button mySortButton;
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

        // Check for incoming activity
        Intent intent = getIntent();
        //replace !=null to onActivityResult()
        if(intent.getExtras() != null) {
            if(intent.getExtras().containsKey(MainActivity.MAIN_MESSAGE)){
                titleString = intent.getExtras().getString(MainActivity.MAIN_MESSAGE);
            }
        }

        displayResultController = singletonManager.getDisplayResultControllerInstance(DisplayResultUI.this, titleString, DisplayResultUI.this);
        displayResultController.addObserver(this);

        if(displayResultController.getRecordList().size() == 0 && displayResultController.getTempRecordList().size() == 0) {
            // Create a new object to fetch the data
            displayResultController.collateResult();
        }

        // Button
        // Display more infomation about the centre
        displayResultListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                HashMap<String, String> selectedRecord = displayResultController.getRecordList().get(position);

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
                    updateListView();
                }
            }
        });
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

    public void goToFilterView(View view){
        if(displayResultController.getTempRecordList().size() != 0) {
            Intent intent = new Intent(this, FilterUI.class);
            //startActivity(intent);
            startActivityForResult(intent, 1);
        }
    }

}
