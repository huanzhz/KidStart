package com.kidstart.kidstart;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class APIController {

    private static String TAG = DisplayResultUI.class.getSimpleName();
    private static ProgressDialog pDialog;
    //URL of the JSON
    private static String url = "https://data.gov.sg/api/action/datastore_search?resource_id=4fc3fd79-64f2-4027-8d5b-ce0d7c279646&limit=50";

    public static class GetRecords extends AsyncTask<Void, Void, Void> {

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
                }
            } else {
                Log.e(TAG, "Couldn't get json from server.");
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            // Dismiss the dialog
//            if(pDialog.isShowing()){
//                pDialog.dismiss();
//            }

            //updateListView(DisplayResultController.recordList);
        }
    }
}
