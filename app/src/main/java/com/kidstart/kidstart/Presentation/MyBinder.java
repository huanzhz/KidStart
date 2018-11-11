package com.kidstart.kidstart.Presentation;

import android.view.View;
import android.widget.RatingBar;
import android.widget.SimpleAdapter;

import com.kidstart.kidstart.R;

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