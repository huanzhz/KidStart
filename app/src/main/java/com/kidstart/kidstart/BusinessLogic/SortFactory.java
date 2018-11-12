package com.kidstart.kidstart.BusinessLogic;

import com.kidstart.kidstart.BusinessLogic.Sort.*;
import com.kidstart.kidstart.Presentation.SortInterface;

public class SortFactory {
    public SortInterface getSort(String sortType) {
        if(sortType.equals("name")) {
            return new SortByName();
        }
        else if(sortType.equals("price")){
            return new SortByPrice();
        }
        else if(sortType.equals("rating")){
            return new SortByRating();
        }
        else if(sortType.equals("distance")){
            return new SortByDistance();
        }
        return null;
    }
}
