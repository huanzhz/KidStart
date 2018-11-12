package com.kidstart.kidstart.BusinessLogic;

import com.kidstart.kidstart.BusinessLogic.Filter.FilterByFood;
import com.kidstart.kidstart.BusinessLogic.Filter.FilterByHour;
import com.kidstart.kidstart.BusinessLogic.Filter.FilterByLanguage;
import com.kidstart.kidstart.BusinessLogic.Filter.FilterByLevel;
import com.kidstart.kidstart.BusinessLogic.Filter.FilterByPrice;
import com.kidstart.kidstart.BusinessLogic.Filter.FilterByRating;
import com.kidstart.kidstart.Presentation.FilterInterface;

public class FilterFactory {

    public FilterInterface getFilter(String filterType){
        if(filterType == "language"){
            return new FilterByLanguage();
        }
        else if(filterType == "rating"){
            return new FilterByRating();
        }
        else if(filterType == "food"){
            return new FilterByFood();
        }
        else if(filterType == "level"){
            return new FilterByLevel();
        }
        else if(filterType == "hour"){
            return new FilterByHour();
        }
        else if(filterType == "price"){
            return new FilterByPrice();
        }
        return null;
    }
}
