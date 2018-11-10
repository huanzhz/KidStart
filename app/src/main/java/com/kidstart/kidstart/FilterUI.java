package com.kidstart.kidstart;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class implement the Filter method
 * @author HuanZhang
 */
public class FilterUI extends AppCompatActivity {

    private Spinner ratingSpinner, foodSpinner, languageSpinner, levelSpinner;
    private ArrayAdapter<String> ratingAdapter, foodAdapter, languageAdapter, levelAdapter;

    private DisplayResultController displayResultController;

    HashMap<String,String> filterList = new HashMap<String, String>();
    private ArrayList<String> filterTypeList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_interface);

        filterTypeList.clear();

        ratingSpinner   = (Spinner) findViewById(R.id.filterRatingSpinner);
        foodSpinner     = (Spinner) findViewById(R.id.filterFoodSpinner);
        languageSpinner = (Spinner) findViewById(R.id.filterLanguageSpinner);
        levelSpinner    = (Spinner) findViewById(R.id.filterLevelSpinner);

        ratingAdapter   = new ArrayAdapter<String>(FilterUI.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.ratings));
        ratingAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        foodAdapter     = new ArrayAdapter<String>(FilterUI.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.food));
        foodAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        languageAdapter = new ArrayAdapter<String>(FilterUI.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.language));
        languageAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        levelAdapter = new ArrayAdapter<String>(FilterUI.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.level));
        levelAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        ratingSpinner.setAdapter(ratingAdapter);
        foodSpinner.setAdapter(foodAdapter);
        languageSpinner.setAdapter(languageAdapter);
        levelSpinner.setAdapter(levelAdapter);

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

        // Get the result of spinner
        String ratingString = ratingSpinner.getSelectedItem().toString();
        filterList.put("rating",ratingString);
        String foodString = foodSpinner.getSelectedItem().toString();
        filterList.put("food",foodString);
        String languageString = languageSpinner.getSelectedItem().toString();
        filterList.put("language",languageString);
        String levelString = languageSpinner.getSelectedItem().toString();
        filterList.put("level",levelString);

        filterTypeList.add("language");
        filterTypeList.add("rating");
        filterTypeList.add("food");
        filterTypeList.add("level");
        displayResultController.filter(filterList, filterTypeList);

        Intent intent = new Intent();
        setResult(RESULT_OK, intent);
        finish();
    }
}
