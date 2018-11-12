package com.kidstart.kidstart.BusinessLogic.Filter;

import com.kidstart.kidstart.BusinessLogic.DisplayResultController;
import com.kidstart.kidstart.Presentation.FilterInterface;

import java.util.HashMap;

/**
 * This class implement the Filter by rating method
 * @author HuanZhang
 */
public class FilterByRating implements FilterInterface {

    public FilterByRating() {
    }

    public void filter(DisplayResultController displayResultController, HashMap<String, String> filterList) {

        // Loop through the array to see which is not suitable for the filter
        for (int i = displayResultController.getRecordList().size() - 1; i >= 0; i--) {

            if (filterList.get("rating").equals("NULL")) {
                continue;
            }
            // If the record is match do not remove it
            if (Integer.valueOf(displayResultController.getRecordList().get(i).get("rating")) >= Integer.valueOf((filterList.get("rating")))) {
                    continue;
            } else {
                displayResultController.getRecordList().remove(i);
            }
        }
    }

}