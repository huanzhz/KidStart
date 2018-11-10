package com.kidstart.kidstart;

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
        return null;
    }
}
