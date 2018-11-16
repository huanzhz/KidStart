package com.kidstart.kidstart.BusinessLogic;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

/**
 * This is a singleton class
 * @author HuanZhang
 */
public class SingletonManager {
    private static final SingletonManager ourInstance = new SingletonManager();
    private static DisplayResultController displayResultController = null;

    /**
     * Get the singleton manager
     * @return singleton manager
     */
    public static SingletonManager getInstance() {
        return ourInstance;
    }

    /**
     * Constructor
     */
    private SingletonManager() {
    }

    /**
     * Create a new displayresultcontroller if is null
     * @param ctx   Context for passing into APIController
     * @param titleString   The message for search filter
     * @param activity  The activity
     * @return the controller instance
     */
    public static DisplayResultController getDisplayResultControllerInstance(Context ctx, String titleString, AppCompatActivity activity){
        if(displayResultController == null)
            displayResultController = new DisplayResultController(ctx, titleString, activity);
        else{
            displayResultController.setNew(ctx, titleString, activity);
        }
        return displayResultController;
    }

    /**
     * Get the displayresultcontroller
     * @return the displayresultcontroller
     */
    public static DisplayResultController getDisplayResultControllerInstance(){
        return displayResultController;
    }

}
