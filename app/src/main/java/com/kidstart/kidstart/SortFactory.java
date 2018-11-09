package com.kidstart.kidstart;

public class SortFactory {
    public SortInterface getSort(String sortType) {
        if(sortType == "name") {
            return new SortByName();
        }
//        else if(sortType == "blablabla"){
//
//        }
        return null;
    }
}
