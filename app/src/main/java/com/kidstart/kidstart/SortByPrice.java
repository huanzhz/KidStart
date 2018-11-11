package com.kidstart.kidstart;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

public class SortByPrice implements SortInterface {

    public boolean sort(){
        DisplayResultController displayerResultController = SingletonManager.getDisplayResultControllerInstance();
        if(displayerResultController.getRecordList().size() != 0) {
            //ArrayList< HashMap< String,String >> arrayList= recordList;
            Collections.sort(displayerResultController.getRecordList(), new Comparator<HashMap<String, String>>() {

                @Override
                public int compare(HashMap<String, String> lhs,
                                   HashMap<String, String> rhs) {
                    // Do your comparison logic here and return accordingly.
                    return (lhs.get("price")).compareTo(rhs.get("price"));
                }
            });
            return true;
        }
        return false;
    }
}
