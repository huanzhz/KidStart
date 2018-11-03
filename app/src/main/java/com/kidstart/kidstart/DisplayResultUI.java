package com.kidstart.kidstart;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

/**
 * This is the result display class.
 * @author HuanZhang
 */
public class DisplayResultUI extends AppCompatActivity {

    public static ListView displayResultListView;
    private Button mySortButton;
    private boolean filterBool;
    private String titleString;

    //ArrayList<HashMap<Stsring, String>> recordList;
    HashMap<String,String> filterhashMap = new HashMap<String, String>();

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

        // Check for incoming activity
        Intent intent = getIntent();
        if(intent.getExtras() != null) {
            if(intent.getExtras().containsKey(FilterUI.FILTER_MESSAGE)){
                filterBool = intent.getExtras().getBoolean(FilterUI.FILTER_MESSAGE);
            }
            if(intent.getExtras().containsKey(MainActivity.MAIN_MESSAGE)){
                titleString = intent.getExtras().getString(MainActivity.MAIN_MESSAGE);
            }
        }

        // Add in record if it is empty else update the view
        if(DisplayResultController.recordList.size() == 0 && DisplayResultController.tempRecordList.size() == 0){
            // Create a new object to fetch the data
            new APIController(DisplayResultUI.this, titleString, DisplayResultUI.this).execute();
            // Similar to this code - "
            //      APIController process = new APIController(DisplayResultUI.this);
            //      process.execute();
            // "
        }else {
            updateListView(DisplayResultController.recordList);
            // If there is no record show a pop up
            if(filterBool) {
                // Popup show no result found
                FailureDialog exampleDialog = new FailureDialog();
                exampleDialog.show(getSupportFragmentManager(), "example dialog");
            }
        }

        // Button
        // Display more infomation about the centre
        displayResultListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            HashMap<String, String> selectedRecord = DisplayResultController.recordList.get(position);

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
                    updateListView(DisplayResultController.recordList);
                }
            }
        });
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
        if(DisplayResultController.recordList.size() != 0) {
            Intent intent = new Intent(this, FilterUI.class);
            startActivity(intent);
        }
    }
}
