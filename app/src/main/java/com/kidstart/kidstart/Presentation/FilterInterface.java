package com.kidstart.kidstart.Presentation;

import com.kidstart.kidstart.BusinessLogic.DisplayResultController;

import java.util.HashMap;

public interface FilterInterface {
    void filter(DisplayResultController displayResultController, HashMap<String, String> filterList);
}
