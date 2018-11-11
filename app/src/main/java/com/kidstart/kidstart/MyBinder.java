package com.kidstart.kidstart;

import android.view.View;
import android.widget.RatingBar;
import android.widget.SimpleAdapter;

class MyBinder implements SimpleAdapter.ViewBinder {
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