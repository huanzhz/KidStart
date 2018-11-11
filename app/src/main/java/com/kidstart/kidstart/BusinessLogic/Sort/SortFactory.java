package com.kidstart.kidstart.BusinessLogic.Sort;

import com.kidstart.kidstart.Presentation.SortInterface;

public class SortFactory {
    public SortInterface getSort(String sortType) {
        if(sortType == "name") {
            return new SortByName();
        }
        else if(sortType == "price"){
            return new SortByPrice();
        }
        else if(sortType == "rating"){
            return new SortByRating();
        }
        else if(sortType == "distance"){
            return new SortByDistance();
        }
        return null;
    }
}