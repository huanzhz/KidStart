package com.kidstart.kidstart.Presentation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.RatingBar;
import android.widget.TextView;

import com.kidstart.kidstart.R;

import java.util.HashMap;

/**
 * This class display fully detail information of a single record
 * @author HuanZhang
 */
public class DetailedInformationUI extends AppCompatActivity {

    private String titleString;
    private TextView infoNameTexView, infoAddressTextView, infoPriceTextView, infoLevelOfferTextView,
                        infoLanguageTextView, infoFoodTextView, infoHourTextView, infoReviewTextView;
    private RatingBar infoRatingBar;

    /**
     * Similar to constructor
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_information_ui);

        // System Back button enable
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        HashMap<String, String> hashMap = (HashMap<String, String>) intent.getSerializableExtra("hashMapMessage");

        infoNameTexView = findViewById(R.id.infoName);
        infoNameTexView.setText(hashMap.get("centreName"));
        infoRatingBar = findViewById(R.id.infoRatingBar);
        infoRatingBar.setRating(Float.parseFloat(hashMap.get("rating")));
        infoAddressTextView = findViewById(R.id.infoAddress);
        infoAddressTextView.setText(hashMap.get("centreAddress"));
        infoPriceTextView = findViewById(R.id.infoPrice);
        infoPriceTextView.setText("School Fees: "+hashMap.get("price"));
        infoLevelOfferTextView = findViewById(R.id.infoLevelOffer);
        infoLevelOfferTextView.setText("Levels offered: PG,N1,N2,K1,K2");
        infoLanguageTextView = findViewById(R.id.infoLanguage);
        infoLanguageTextView.setText("Second Languages: "+hashMap.get("secondLanguagesOffered"));
        infoFoodTextView = findViewById(R.id.infoFood);
        infoFoodTextView.setText("Food Catered: "+hashMap.get("foodOffered"));
        infoHourTextView = findViewById(R.id.infoHour);
        infoHourTextView.setText("Hour of care: "+hashMap.get("weekday_full_day"));
        infoReviewTextView = findViewById(R.id.infoReview);
        infoReviewTextView.setText(hashMap.get("review"));
        infoRatingBar.setEnabled(false);

        titleString = intent.getExtras().getString("Death");
    }

    /**
     * Trigger when back button is pressed
     * @param item
     * @return
     */
    public boolean onOptionsItemSelected(MenuItem item){
        Intent intent = new Intent();
        setResult(RESULT_OK, intent);
        finish();
        return true;
    }
}
