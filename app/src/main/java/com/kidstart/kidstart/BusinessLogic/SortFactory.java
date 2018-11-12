package com.kidstart.kidstart.BusinessLogic;

import com.kidstart.kidstart.BusinessLogic.Sort.SortByDistance;
import com.kidstart.kidstart.BusinessLogic.Sort.SortByName;
import com.kidstart.kidstart.BusinessLogic.Sort.SortByPrice;
import com.kidstart.kidstart.BusinessLogic.Sort.SortByRating;
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
