package com.kidstart.kidstart.BusinessLogic;

import com.kidstart.kidstart.BusinessLogic.Filter.*;
import com.kidstart.kidstart.Presentation.FilterInterface;

public class FilterFactory {
    public FilterInterface getFilter(String filterType){
        if(filterType.equals("language")){
            return new FilterByLanguage();
        }
        else if(filterType.equals("rating")){
            return new FilterByRating();
        }
        else if(filterType.equals("food")){
            return new FilterByFood();
        }
        else if(filterType.equals("level")){
            return new FilterByLevel();
        }
        else if(filterType.equals("hour")){
            return new FilterByHour();
        }
        else if(filterType.equals("price")){
            return new FilterByPrice();
        }
        return null;
    }
}
