package com.kidstart.kidstart.BusinessLogic;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * This class implement the getAPI method
 * @author HuanZhang
 */
public class APIController extends AsyncTask<Void, Void, Void> {
//    private static String TAG = DisplayResultUI.class.getSimpleName();
    private static ProgressDialog pDialog;
    //URL of the JSON
    private static String url = "https://data.gov.sg/api/action/datastore_search?resource_id=4fc3fd79-64f2-4027-8d5b-ce0d7c279646&limit=2000";
    private Context mContext;
    private String titleString;
    private AppCompatActivity mainActivity;
    private ArrayList<HashMap<String, String>> recordList;
    private ArrayList<HashMap<String, String>> tmpRecordList;
    private boolean searchfilter = false;

    public APIController(Context ctx, String titleString, AppCompatActivity activity, ArrayList<HashMap<String, String>> recordList, ArrayList<HashMap<String, String>> tmpRecordList){
        this.mContext = ctx;
        this.titleString = titleString;
        mainActivity = activity;
        this.recordList = recordList;
        this.tmpRecordList = tmpRecordList;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        // Show loading dialog
        pDialog = new ProgressDialog(mContext);
        pDialog.setMessage("loading...");
        pDialog.setCancelable(false);
        pDialog.show();
    }

    @Override
    protected Void doInBackground(Void... voids) {
        HttpHandler sh = new HttpHandler();

        String jsonStr = sh.makeServiceCall(url);

        Log.e("JSON", "Response from url: " + jsonStr);

        if(jsonStr != null){
            try{
                JSONObject jsonObject = new JSONObject(jsonStr);

                // Getting json array node
                JSONArray records = jsonObject.getJSONObject("result").getJSONArray("records");

                // Loopong through all records
                for(int i = 0; i < records.length(); i ++){
                    searchfilter = false;
                    JSONObject c = records.getJSONObject(i);

                    String centre_name = c.getString("centre_name");
                    String centre_address = c.getString("centre_address");
                    // Check the string within the Name
                    if(centre_name.contains(titleString)){
                        searchfilter = true;
                    }
                    // Check the string within the Address or postal code
                    if(centre_address.contains(titleString) && !searchfilter){
                        searchfilter = true;
                    }
                    // If the search not found then go to next iteration of the for loop
                    if(!searchfilter){
                        continue;
                    }
                    String centre_website = c.getString("centre_website");
                    String second_languages_offered = c.getString("second_languages_offered");
                    String weekday_full_day = c.getString("weekday_full_day");
                    String food_offered = c.getString("food_offered");
                    String _id = c.getString("_id"); // maybe need REMOVE REMOVE REMOVE


                    // [0, 100] + 300 => [300, 400]
                    String _price = String.valueOf(new Random().nextInt(900) + 100);
                    _price = "$"+_price+"   ";

                    // [0, 5] => [0, 5]
                    String _rating = String.valueOf(new Random().nextInt(6));

                    // [0, 30] => [0, 30]
                    String _review = String.valueOf(new Random().nextInt(31));
                    _review = ""+_review+" Reviews";

                    String _distance = String.valueOf(new Random().nextInt(101));

                    HashMap<String, String> childCareRecord = new HashMap<>();

                    // Adding each child node to hashmap
                    childCareRecord.put("centreName", centre_name);
                    childCareRecord.put("centreAddress", centre_address);
                    childCareRecord.put("centreWebsite", centre_website);
                    childCareRecord.put("secondLanguagesOffered", second_languages_offered);
                    childCareRecord.put("weekdayFullDay", weekday_full_day);
                    childCareRecord.put("foodOffered", food_offered);
                    childCareRecord.put("testID", _id);
                    childCareRecord.put("price", _price);
                    childCareRecord.put("rating", _rating);
                    childCareRecord.put("review", _review);
                    childCareRecord.put("distance", _distance);

                    // Adding record to record list
                    recordList.add(childCareRecord);
                    // Add another record for filtering
                    tmpRecordList.add(childCareRecord);
                }
            }catch (final JSONException e){
                Log.e("JSON Error", "JSON parsing error: " + e.getMessage());
            }
        } else {
            Log.e("JSON ERROR", "Couldn't get json from server.");
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

        SingletonManager.getDisplayResultControllerInstance().onPostExecuteAPI();
    }
}
