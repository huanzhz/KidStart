package com.kidstart.kidstart;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;

public class DisplayResultController extends Observable {

    private Context context;
    private String titleString;
    private AppCompatActivity appActivity;

    private ArrayList<HashMap<String, String>> recordList;
    private ArrayList<HashMap<String, String>> tempRecordList;

    private FilterController filterController = new FilterController();

    public DisplayResultController(Context ctx, String titleString, AppCompatActivity activity){
        context = ctx;
        this.titleString = titleString;
        appActivity = activity;

        if(recordList == null) {
            recordList = new ArrayList<>();
            tempRecordList = new ArrayList<>();
        }
    }

    public void recordCopy(ArrayList arrayListInput, ArrayList arrayListOutput) {
        arrayListOutput.clear();
        for (int i = 0; i < arrayListInput.size(); i++) {
            arrayListOutput.add(i, arrayListInput.get(i));
        }
    }

    public void resetArray(){
        if(recordList != null){
            recordList.clear();
            tempRecordList.clear();
            //notify DisplayResultUI to updateListView
            notifyObservers();
        }
    }

    //Message passed from FilterUI if checkBoxTicked
    public void filter(HashMap<String,String> filterList){
        filterController.filterRace(this, filterList);
        notifyObservers();
    }

    public void collateResult(){
        new APIController(context, titleString, appActivity, recordList, tempRecordList).execute();
    };

    public void setNew(Context ctx, String titleString, AppCompatActivity activity) {
        context = ctx;
        this.titleString = titleString;
        appActivity = activity;
        resetArray();
    }

    public ArrayList<HashMap<String, String>> getRecordList() {
        return recordList;
    }

    public ArrayList<HashMap<String, String>> getTempRecordList() {
        return tempRecordList;
    }
}
