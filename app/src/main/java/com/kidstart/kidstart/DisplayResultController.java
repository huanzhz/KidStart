package com.kidstart.kidstart;

import java.util.ArrayList;
import java.util.HashMap;

public class DisplayResultController {

    public static ArrayList<HashMap<String, String>> recordList;
    public static ArrayList<HashMap<String, String>> tempRecordList;

    public static void recordCopy(ArrayList arrayListInput, ArrayList arrayListOutput) {

        arrayListOutput.clear();
        for (int i = 0; i < arrayListInput.size(); i++) {
            arrayListOutput.add(i, arrayListInput.get(i));
        }
    }

    public static void createRecord(){
        //new APIController.GetRecords().execute();
    }

}
