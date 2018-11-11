package com.kidstart.kidstart;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

/*

 */

public class SingletonManager {
    private static final SingletonManager ourInstance = new SingletonManager();
    private static DisplayResultController displayResultController = null;

    public static SingletonManager getInstance() {
        return ourInstance;
    }

    private SingletonManager() {
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
