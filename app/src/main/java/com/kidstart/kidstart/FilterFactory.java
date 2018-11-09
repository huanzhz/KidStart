package com.kidstart.kidstart;

public class FilterFactory {

    public FilterInterface getFilter(String filterType){
        if(filterType == "language"){
                return new FilterByLanguage();
        }
//        else if(filterType == ""){
//            return new FilterByBlablaba
//        }
        return null;
    }
}
