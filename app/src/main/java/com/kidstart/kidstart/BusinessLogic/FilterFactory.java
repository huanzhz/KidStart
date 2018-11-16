package com.kidstart.kidstart.BusinessLogic;

import com.kidstart.kidstart.BusinessLogic.Filter.*;
import com.kidstart.kidstart.Presentation.FilterInterface;

/**
 * This class implement the factory pattern
 * @author HuanZhang
 */
public class FilterFactory {

    /**
     * Choose which filter to use
     * @param filterType    The list contain name for factory class to decide which to filter
     * @return      The function for filtering
     */
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
