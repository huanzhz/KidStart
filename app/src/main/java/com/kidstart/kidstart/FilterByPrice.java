package com.kidstart.kidstart;

import java.util.HashMap;

/**
 * This class implement the Filter by price method
 * @author HuanZhang
 */
public class FilterByPrice implements FilterInterface {

    public FilterByPrice() {
    }

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
