package com.kidstart.kidstart.Presentation;

import com.kidstart.kidstart.BusinessLogic.DisplayResultController;

import java.util.HashMap;

/**
 * This class display the filter interface
 * @author HuanZhang
 */
public interface FilterInterface {
    void filter(DisplayResultController displayResultController, HashMap<String, String> filterList);
}
