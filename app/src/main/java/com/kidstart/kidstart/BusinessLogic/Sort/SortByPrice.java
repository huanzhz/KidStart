package com.kidstart.kidstart.BusinessLogic.Sort;

import com.kidstart.kidstart.BusinessLogic.DisplayResultController;
import com.kidstart.kidstart.BusinessLogic.SingletonManager;
import com.kidstart.kidstart.Presentation.SortInterface;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

/**
 * This class implement the Sort by price
 * @author HuanZhang
 */
public class SortByPrice implements SortInterface {

    /**
     * Sort by price
     * @param asc   sorting in ascending or descending order
     * @return  true if sort successful
     */
    public boolean sort(Boolean asc) {
        DisplayResultController displayerResultController = SingletonManager.getDisplayResultControllerInstance();

        if (asc == false) {
            if (displayerResultController.getRecordList().size() != 0) {
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
        } else {
            if (displayerResultController.getRecordList().size() != 0) {
                //ArrayList< HashMap< String,String >> arrayList= recordList;
                Collections.sort(displayerResultController.getRecordList(), new Comparator<HashMap<String, String>>() {

                    @Override
                    public int compare(HashMap<String, String> lhs,
                                       HashMap<String, String> rhs) {
                        // Do your comparison logic here and return accordingly.
                        return (rhs.get("price")).compareTo(lhs.get("price"));
                    }
                });
                return true;
            }
            return false;
        }
    }
}
