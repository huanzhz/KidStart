package com.kidstart.kidstart.BusinessLogic;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import com.kidstart.kidstart.Presentation.FilterInterface;
import com.kidstart.kidstart.Presentation.SortInterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;

public class DisplayResultController extends Observable {

    private Context context;
    private String titleString;
    private AppCompatActivity appActivity;

    private ArrayList<HashMap<String, String>> recordList;
    private ArrayList<HashMap<String, String>> tempRecordList;

    private FilterFactory filterFactory = new FilterFactory();
    private SortFactory sortFactory = new SortFactory();

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
            setChanged();
            notifyObservers();
        }
    }

    //Message passed from FilterUI if checkBoxTicked
    public void filter(HashMap<String,String> filterList, ArrayList<String> filterTypeList){
        //run through each filterType in the filterTypeList, create appropriate filters and filter
        for(int i=0; i<filterTypeList.size(); i++){
            FilterInterface filterController = filterFactory.getFilter(filterTypeList.get(i));
            filterController.filter(this, filterList);
        }
        setChanged();
        notifyObservers();
    }

    public void sort(String sortType, HashMap<String, Boolean> sortAscMap) {
        SortInterface sortController = sortFactory.getSort(sortType);
        boolean sortSuccessful = sortController.sort(sortAscMap.get(sortType));
        if (sortSuccessful) {
            setChanged();
            notifyObservers();
        }
    }

    public void collateResult(){
        new APIController(context, titleString, appActivity, recordList, tempRecordList).execute();
    };

    public void onPostExecuteAPI() {
        setChanged();
        notifyObservers("new");
    }

    public void setNew(Context ctx, String titleString, AppCompatActivity activity) {
        context = ctx;
        this.titleString = titleString;
        appActivity = activity;
        setChanged();
        notifyObservers();
    }

    public ArrayList<HashMap<String, String>> getRecordList() {
        return recordList;
    }

    public ArrayList<HashMap<String, String>> getTempRecordList() {
        return tempRecordList;
    }
}
