package com.kidstart.kidstart;

import android.view.Display;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

/**
 * This class implement the Sort method
 * @author HuanZhang
 */
public class SortByName {

    /**
     * Sort the name of the centre in ascending order
     * @return true if successfully sorted
     */
    public static boolean sortData(){
        DisplayResultController displayerResultController = singletonManager.getDisplayResultControllerInstance();

        if(displayerResultController.getRecordList().size() != 0) {
            //ArrayList< HashMap< String,String >> arrayList= recordList;
            Collections.sort(displayerResultController.getRecordList(), new Comparator<HashMap<String, String>>() {

                @Override
                public int compare(HashMap<String, String> lhs,
                                   HashMap<String, String> rhs) {
                    // Do your comparison logic here and return accordingly.
                    return (lhs.get("centreName")).compareTo(rhs.get("centreName"));
                }
            });
            return true;
        }
        return false;
    }
}