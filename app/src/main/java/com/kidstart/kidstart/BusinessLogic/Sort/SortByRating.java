package com.kidstart.kidstart.BusinessLogic.Sort;

import com.kidstart.kidstart.BusinessLogic.DisplayResultController;
import com.kidstart.kidstart.BusinessLogic.SingletonManager;
import com.kidstart.kidstart.Presentation.SortInterface;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

public class SortByRating implements SortInterface {

    public boolean sort(){
        DisplayResultController displayerResultController = SingletonManager.getDisplayResultControllerInstance();
        if(displayerResultController.getRecordList().size() != 0) {
            //ArrayList< HashMap< String,String >> arrayList= recordList;
            Collections.sort(displayerResultController.getRecordList(), new Comparator<HashMap<String, String>>() {

                @Override
                public int compare(HashMap<String, String> lhs,
                                   HashMap<String, String> rhs) {
                    // Do your comparison logic here and return accordingly.
                    return (rhs.get("rating")).compareTo(lhs.get("rating"));
                }
            });
            return true;
        }
        return false;
    }
}
