package com.kidstart.kidstart.Presentation;

import android.view.View;
import android.widget.RatingBar;
import android.widget.SimpleAdapter;

import com.kidstart.kidstart.R;

/**
 * This class is to bind the rating bar to the list nodes
 * @author HuanZhang
 */
class MyBinder implements SimpleAdapter.ViewBinder {
    /**
     * Set the value of the list nodes
     * @param view
     * @param data  The display data in each node
     * @param textRepresentation
     * @return
     */
    @Override
    public boolean setViewValue(View view, Object data, String textRepresentation) {
        if(view.getId() == R.id.ratingBar){
            String stringval = (String) data;
            float ratingValue = Float.parseFloat(stringval);
            RatingBar ratingBar = (RatingBar) view;
            ratingBar.setRating(ratingValue);
            ratingBar.setEnabled(false);
            return true;
        }
        return false;
    }
}