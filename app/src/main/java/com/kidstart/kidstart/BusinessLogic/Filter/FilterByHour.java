package com.kidstart.kidstart.BusinessLogic.Filter;

import com.kidstart.kidstart.BusinessLogic.DisplayResultController;
import com.kidstart.kidstart.Presentation.FilterInterface;

import java.util.HashMap;

/**
 * This class implement the Filter by operating hour method
 * @author HuanZhang
 */
public class FilterByHour implements FilterInterface {

    /**
     * Constructor
     */
    public FilterByHour() {
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
//            if(displayResultController.getRecordList().get(i).get("foodOffered").equals(filterList.get("food"))){
//                continue;
//            } else {
//                displayResultController.getRecordList().remove(i);
//            }
//        }
    }
}
