package com.kidstart.kidstart;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;

/**
 * This class implement the filter by language method
 * @author HuanZhang
 */
public class FilterByLanguage implements FilterInterface {

    public FilterByLanguage() {
    }

    public void filter(DisplayResultController displayResultController, HashMap<String,String> filterList){

        // Loop through the array to see which is not suitable for the filter
        for (int i = displayResultController.getRecordList().size()-1; i >= 0; i--) {

            // If the record is match do not remove it
            // String[] checked=["1","1","0"];
            if(displayResultController.getRecordList().get(i).get("secondLanguagesOffered").equals(filterList.get("language"))){
                continue;
            } else {
                displayResultController.getRecordList().remove(i);
            }
        }
    }
}
