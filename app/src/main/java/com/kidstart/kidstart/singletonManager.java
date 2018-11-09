package com.kidstart.kidstart;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

/*

 */

public class singletonManager {
    private static final singletonManager ourInstance = new singletonManager();
    private static DisplayResultController displayResultController = null;

    public static singletonManager getInstance() {
        return ourInstance;
    }

    private singletonManager() {
    }

    public static DisplayResultController getDisplayResultControllerInstance(Context ctx, String titleString, AppCompatActivity activity){
        if(displayResultController == null)
            displayResultController = new DisplayResultController(ctx, titleString, activity);
        else{
            displayResultController.setNew(ctx, titleString, activity);
        }
        return displayResultController;
    }

    public static DisplayResultController getDisplayResultControllerInstance(){
        return displayResultController;
    }

}
