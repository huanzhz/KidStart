package com.kidstart.kidstart.BusinessLogic.Filter;

import com.kidstart.kidstart.BusinessLogic.DisplayResultController;
import com.kidstart.kidstart.Presentation.FilterInterface;

import java.util.HashMap;

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

            if(filterList.get("language").equals("NULL")){
                continue;
            }

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
