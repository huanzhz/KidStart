package com.kidstart.kidstart;

import java.util.ArrayList;
import java.util.HashMap;

public class FilterController {
    private DisplayResultController displayResultController = singletonManager.getDisplayResultControllerInstance();

    public FilterController(){

    }

    public void filterRace(HashMap<String,String> filterList){
        // Loop through the array to see which is not suitable for the filter
        for (int i = displayResultController.getTempRecordList().size()-1; i >= 0; i--) {

            // If the record is match do not remove it
            // String[] checked=["1","1","0"];
            if(displayResultController.getTempRecordList().get(i).get("Chinese").equals(filterList.get("Chinese")) &&
                    displayResultController.getTempRecordList().get(i).get("Malay").equals(filterList.get("Malay")) &&
                    displayResultController.getTempRecordList().get(i).get("Tamil").equals(filterList.get("Tamil")) ){
                continue;
            } else {
                displayResultController.getRecordList().remove(i);
            }
        }
    }
}
