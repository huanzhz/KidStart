package com.kidstart.kidstart.BusinessLogic.Filter;

import com.kidstart.kidstart.BusinessLogic.DisplayResultController;
import com.kidstart.kidstart.Presentation.FilterInterface;

import java.util.HashMap;

/**
 * This class implement the Filter by different child course method
 * @author HuanZhang
 */
public class FilterByLevel implements FilterInterface {

    /**
     * Constructor
     */
    public FilterByLevel() {
    }

    /**
     * Filter function call by filterFactory class
     * @param displayResultController   Class that contain all the information of record
     * @param filterList    The list of information to filter
     */
    public void filter(DisplayResultController displayResultController, HashMap<String,String> filterList){

        // Loop through the array to see which is not suitable for the filter
//        for (int i = displayResultController.getRecordList().size()-1; i >= 0; i--) {
//
//            // If the record is match do not remove it
//            if(displayResultController.getRecordList().get(i).get("rating").equals(filterList.get("rating"))){
//                continue;
//            } else {
//                displayResultController.getRecordList().remove(i);
//            }
//        }
    }
}
