package com.kidstart.kidstart.Presentation;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.SimpleAdapter;

import com.kidstart.kidstart.BusinessLogic.DisplayResultController;
import com.kidstart.kidstart.R;
import com.kidstart.kidstart.BusinessLogic.SingletonManager;

import java.util.HashMap;
import java.util.Observer;
import java.util.Observable;

/**
 * This is the result display class.
 * @author HuanZhang
 */
public class DisplayResultUI extends AppCompatActivity implements Observer {

    private ListView displayResultListView;
    private Button nameSortButton, priceSortButton, distanceSortButton, ratingSortButton;
    private RatingBar _ratingBar;
    private String titleString;
    private DisplayResultController displayResultController;
    private ListAdapter adapter;
    private HashMap<String, Boolean> sortAscMap = new HashMap<>();
    private String sortType = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_result_ui);

        //set all sorting to false
        sortAscMap.put("name", false);
        sortAscMap.put("price", false);
        sortAscMap.put("distance", false);
        sortAscMap.put("rating", false);

        // System naming
        //getSupportActionBar().setTitle("Hello World!");
        // System Back button enable
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Initialise the button and variables
        displayResultListView = (ListView) findViewById(R.id.listView);

        titleString = "";
        // Check for incoming activity
        Intent intent = getIntent();
        //replace !=null to onActivityResult()
        if(intent.getExtras() != null) {
            if(intent.getExtras().containsKey(HomePageUI.MAIN_MESSAGE)){
                titleString = intent.getExtras().getString(HomePageUI.MAIN_MESSAGE);
            }
        }

        displayResultController = SingletonManager.getDisplayResultControllerInstance(DisplayResultUI.this, titleString, DisplayResultUI.this);
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
                intent.putExtra("Death",titleString);
                startActivity(intent);
                 }
            }
        );

        // Sort buttons initialise
        nameSortButton = findViewById(R.id.sortButton);
        priceSortButton = findViewById(R.id.priceButton);
        distanceSortButton = findViewById(R.id.distanceButton);
        ratingSortButton = findViewById(R.id.ratingButton);

        nameSortButton.setBackgroundColor(Color.GRAY);
        priceSortButton.setBackgroundColor(Color.GRAY);
        distanceSortButton.setBackgroundColor(Color.GRAY);
        ratingSortButton.setBackgroundColor(Color.GRAY);

        nameSortButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sortType = "name";
                sortListView();
                //change boolean to opposite
                sortAscMap.put(sortType, !sortAscMap.get(sortType));
                nameSortButton.setBackgroundColor(Color.DKGRAY);
                priceSortButton.setBackgroundColor(Color.GRAY);
                distanceSortButton.setBackgroundColor(Color.GRAY);
                ratingSortButton.setBackgroundColor(Color.GRAY);
            }
        });
        priceSortButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sortType = "price";
                sortListView();
                //change boolean to opposite
                sortAscMap.put(sortType, !sortAscMap.get(sortType));
                nameSortButton.setBackgroundColor(Color.GRAY);
                priceSortButton.setBackgroundColor(Color.DKGRAY);
                distanceSortButton.setBackgroundColor(Color.GRAY);
                ratingSortButton.setBackgroundColor(Color.GRAY);
            }
        });
        distanceSortButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sortType = "distance";
                sortListView();
                //change boolean to opposite
                sortAscMap.put(sortType, !sortAscMap.get(sortType));
                nameSortButton.setBackgroundColor(Color.GRAY);
                priceSortButton.setBackgroundColor(Color.GRAY);
                distanceSortButton.setBackgroundColor(Color.DKGRAY);
                ratingSortButton.setBackgroundColor(Color.GRAY);
            }
        });
        ratingSortButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sortType = "rating";
                sortListView();
                //change boolean to opposite
                sortAscMap.put(sortType, !sortAscMap.get(sortType));
                nameSortButton.setBackgroundColor(Color.GRAY);
                priceSortButton.setBackgroundColor(Color.GRAY);
                distanceSortButton.setBackgroundColor(Color.GRAY);
                ratingSortButton.setBackgroundColor(Color.DKGRAY);
            }
        });
    }

    // Click back button
    public boolean onOptionsItemSelected(MenuItem item){
        displayResultController.resetArray();
        Intent myIntent = new Intent(this, HomePageUI.class);
        displayResultController.resetArray();
        startActivity(myIntent);
        return true;
    }

    //Override method in ListResultObserver, and do something if Subject notifies
    @Override
    public void update(Observable observable, Object arg){
        //TODO
        if (observable instanceof DisplayResultController) {
            if (arg == "new") {
                newListView();
            } else {
                updateListView();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 0) {
            displayResultController.collateResult();
        }
    }

    public void sortListView() {
        //onClick for which sort button
        displayResultController.sort(sortType, sortAscMap);
    }

    public void updateListView(){
        ((BaseAdapter)adapter).notifyDataSetChanged();
    }

    public void newListView(){
        adapter = new SimpleAdapter(
                DisplayResultUI.this, displayResultController.getRecordList(),
                R.layout.school_listing, new String[]{"centreName", "rating", "price", "review"},
                new int[]{R.id.nameTextView, R.id.ratingBar, R.id.priceTextView, R.id.reviewTextView});
        ((SimpleAdapter) adapter).setViewBinder(new MyBinder());
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
