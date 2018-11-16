package com.kidstart.kidstart.BusinessLogic;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import com.kidstart.kidstart.Presentation.FilterInterface;
import com.kidstart.kidstart.Presentation.SortInterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;

/**
 * This is a controller class where use observe pattern to update its function.
 * @author HuanZhang
 */
public class DisplayResultController extends Observable {

    private Context context;
    private String titleString;
    private AppCompatActivity appActivity;

    private ArrayList<HashMap<String, String>> recordList;
    private ArrayList<HashMap<String, String>> tempRecordList;

    private FilterFactory filterFactory = new FilterFactory();
    private SortFactory sortFactory = new SortFactory();

    /**
     * Constructor
     * @param ctx   Context for passing into APIController
     * @param titleString   The message for search filter
     * @param activity  The activity
     */
    public DisplayResultController(Context ctx, String titleString, AppCompatActivity activity){
        context = ctx;
        this.titleString = titleString;
        appActivity = activity;

        if(recordList == null) {
            recordList = new ArrayList<>();
            tempRecordList = new ArrayList<>();
        }
    }

    /**
     * Make a new copy of the original array
     * @param arrayListInput    array that going to copy
     * @param arrayListOutput   array that going be overwriten
     */
    public void recordCopy(ArrayList arrayListInput, ArrayList arrayListOutput) {
        arrayListOutput.clear();
        for (int i = 0; i < arrayListInput.size(); i++) {
            arrayListOutput.add(i, arrayListInput.get(i));
        }
    }

    /**
     * Set the array size to zero
     */
    public void resetArray(){
        if(recordList != null){
            recordList.clear();
            tempRecordList.clear();
            //notify DisplayResultUI to updateListView
            setChanged();
            notifyObservers();
        }
    }

    /**
     * Call the filter factory class and notify for an update on the listview that display the record
     * @param filterList    The list of information to filter
     * @param filterTypeList    The list contain name for factory class to decide which to filter
     */
    public void filter(HashMap<String,String> filterList, ArrayList<String> filterTypeList){
        //run through each filterType in the filterTypeList, create appropriate filters and filter
        for(int i=0; i<filterTypeList.size(); i++){
            FilterInterface filterController = filterFactory.getFilter(filterTypeList.get(i));
            filterController.filter(this, filterList);
        }
        setChanged();
        notifyObservers();
    }

    /**
     * Call the sort factory for different kind of sort method
     * @param sortType  Which type of sort is calling
     * @param sortAscMap    Sorting in ascending order or descending order
     */
    public void sort(String sortType, HashMap<String, Boolean> sortAscMap) {
        SortInterface sortController = sortFactory.getSort(sortType);
        boolean sortSuccessful = sortController.sort(sortAscMap.get(sortType));
        if (sortSuccessful) {
            setChanged();
            notifyObservers();
        }
    }

    /**
     * Get the record from apicontroller class
     */
    public void collateResult(){
        new APIController(context, titleString, appActivity, recordList, tempRecordList).execute();
    };

    /**
     * Create a new observer
     */
    public void onPostExecuteAPI() {
        setChanged();
        notifyObservers("new");
    }

    /**
     * Create a new variable for displayresultcontroller class
     *  @param ctx   Context for passing into APIController
     *  @param titleString   The message for search filter
     *  @param activity  The activity
     */
    public void setNew(Context ctx, String titleString, AppCompatActivity activity) {
        context = ctx;
        this.titleString = titleString;
        appActivity = activity;
        setChanged();
        notifyObservers();
    }

    /**
     * Get the record list
     * @return list of record
     */
    public ArrayList<HashMap<String, String>> getRecordList() {
        return recordList;
    }

    /**
     * Get the temp record list which is the origin
     * Do not modify change to this when filter
     * @return list of origin record
     */
    public ArrayList<HashMap<String, String>> getTempRecordList() {
        return tempRecordList;
    }
}
