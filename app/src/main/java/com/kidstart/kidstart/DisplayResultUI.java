package com.kidstart.kidstart;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

public class DisplayResultUI extends AppCompatActivity {

    private String TAG = DisplayResultUI.class.getSimpleName();

    private ProgressDialog pDialog;
    private ListView lv;
    private Button mySortButton;
    private boolean filterBool;

    //URL of the JSON
    private static String url = "https://data.gov.sg/api/action/datastore_search?resource_id=4fc3fd79-64f2-4027-8d5b-ce0d7c279646&limit=50";

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

        lv = (ListView) findViewById(R.id.listView);
        filterBool = false;
        // Create a space/address for the record
        if(DisplayResultController.recordList == null){
            DisplayResultController.recordList = new ArrayList<>();
            DisplayResultController.tempRecordList = new ArrayList<>();
        }
        Intent intent = getIntent();
        // If access from the filter activity
        if(intent.getExtras() != null) {
            filterBool = true;
            Bundle extras = intent.getExtras();
            if (extras.getString("filter").contentEquals("FilterPage")) {
                filterhashMap = (HashMap<String, String>) extras.getSerializable("filterHashMapMessage");

                // Create a copied of the original and remove them by filtering
                DisplayResultController.recordCopy(DisplayResultController.tempRecordList, DisplayResultController.recordList);

                // Loop through the array to see which is not suitable for the filter
                for (int i = DisplayResultController.tempRecordList.size()-1; i >= 0; i--) {

                    // If the record is match do not remove it
                    // String[] checked=["1","1","0"];
                    if(DisplayResultController.tempRecordList.get(i).get("Chinese").equals(filterhashMap.get("Chinese")) &&
                            DisplayResultController.tempRecordList.get(i).get("Malay").equals(filterhashMap.get("Malay")) &&
                            DisplayResultController.tempRecordList.get(i).get("Tamil").equals(filterhashMap.get("Tamil")) ){
                        continue;
                    } else {
                        DisplayResultController.recordList.remove(i);
                    }
                }
            }
        }

        // Add in record if it is empty else update the view
        if(DisplayResultController.recordList.size() == 0 && DisplayResultController.tempRecordList.size() == 0){
            //DisplayResultController.createRecord();
            new GetRecords().execute();
        }else if(DisplayResultController.recordList.size() == 0 && !filterBool) {
            // Create a copied of the original and remove them by filtering
            DisplayResultController.recordCopy(DisplayResultController.tempRecordList, DisplayResultController.recordList);
            updateListView(DisplayResultController.recordList);
        }else if(DisplayResultController.recordList.size() == 0 && filterBool) {
            // Popup show no result found
            FailureDialog exampleDialog = new FailureDialog();
            exampleDialog.show(getSupportFragmentManager(), "example dialog");
        }else {
            updateListView(DisplayResultController.recordList);
        }

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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
                sortData();
            }
        });
    }

    private class GetRecords extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Show loading dialog
            pDialog = new ProgressDialog(DisplayResultUI.this);
            pDialog.setMessage("loading...");
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            HttpHandler sh = new HttpHandler();

            String jsonStr = sh.makeServiceCall(url);

            Log.e(TAG, "Response from url: " + jsonStr);

            if(jsonStr != null){
                try{
                    JSONObject jsonObject = new JSONObject(jsonStr);

                    // Getting json array node
                    JSONArray records = jsonObject.getJSONObject("result").getJSONArray("records");

                    // Loopong through all records
                    for(int i = 0; i < records.length(); i ++){
                        JSONObject c = records.getJSONObject(i);

                        String centre_name = c.getString("centre_name");
                        String centre_address = c.getString("centre_address");
                        String centre_website = c.getString("centre_website");
                        String second_languages_offered = c.getString("second_languages_offered");
                        String weekday_full_day = c.getString("weekday_full_day");
                        String _id = c.getString("_id");

                        HashMap<String, String> childCareRecord = new HashMap<>();

                        // Adding each child node to hashmap
                        childCareRecord.put("centreName", centre_name);
                        childCareRecord.put("centreAddress", centre_address);
                        childCareRecord.put("centreWebsite", centre_website);

                        if(second_languages_offered.contains("Chinese")){
                            childCareRecord.put("Chinese", "1");
                        } else {
                            childCareRecord.put("Chinese", "0");
                        }

                        if(second_languages_offered.contains("Malay")){
                            childCareRecord.put("Malay", "1");
                        } else {
                            childCareRecord.put("Malay", "0");
                        }

                        if(second_languages_offered.contains("Tamil")){
                            childCareRecord.put("Tamil", "1");
                        } else {
                            childCareRecord.put("Tamil", "0");
                        }

                        childCareRecord.put("secondLanguagesOffered", second_languages_offered);
                        childCareRecord.put("weekdayFullDay", weekday_full_day);
                        childCareRecord.put("testID", _id);

                        // Adding record to record list
                        DisplayResultController.recordList.add(childCareRecord);
                        // Add another record for filtering
                        DisplayResultController.tempRecordList.add(childCareRecord);
                    }
                }catch (final JSONException e){
                    Log.e(TAG, "JSON parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(DisplayResultUI.this,
                                    "JSON parsing error: " + e.getMessage(),
                                    Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            } else {
                Log.e(TAG, "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(DisplayResultUI.this,
                                "Couldn't get json from server.",
                                Toast.LENGTH_SHORT).show();
                    }
                });
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            // Dismiss the dialog
            if(pDialog.isShowing()){
                pDialog.dismiss();
            }

            updateListView(DisplayResultController.recordList);
        }
    }

    public void sortData(){
        //ArrayList< HashMap< String,String >> arrayList= recordList;
        Collections.sort(DisplayResultController.recordList, new Comparator<HashMap< String,String >>() {

            @Override
            public int compare(HashMap<String, String> lhs,
                               HashMap<String, String> rhs) {
                // Do your comparison logic here and return accordingly.
                return (rhs.get("testID")).compareTo(lhs.get("testID"));
            }
        });

        updateListView(DisplayResultController.recordList);
    }

    public void updateListView(ArrayList arrayList){
        // Update jason data to listview
        // SimpleAdapter(Context context, List<? extends Map<String, ?>> data, int resource, String[] from, int[] to)
        ListAdapter adapter = new SimpleAdapter(
                DisplayResultUI.this, arrayList,
                R.layout.school_listing, new String[]{"centreName", "centreAddress", "testID", "secondLanguagesOffered"},
                new int[]{R.id.name, R.id.location, R.id.operationhour, R.id.test1});

        lv.setAdapter(adapter);
    }

    public void goToFilterView(View view){
        Intent intent = new Intent(this, FilterUI.class);
        startActivity(intent);
    }
}
