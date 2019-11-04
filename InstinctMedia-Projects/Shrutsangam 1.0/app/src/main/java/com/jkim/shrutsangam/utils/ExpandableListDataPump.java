package com.jkim.shrutsangam.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExpandableListDataPump {
    public static HashMap<String, List<String>> getData() {
        HashMap<String, List<String>> expandableListDetail = new HashMap<String, List<String>>();

        List<String> dashboard = new ArrayList<String>();

        List<String> usageReport = new ArrayList<String>();


        List<String> book = new ArrayList<String>();
        book.add("Book Issues");
        book.add("Book Receive");
//        book.add("Book Pending");
        book.add("Book List");
//        book.add("Book Enquiry Form");

        List<String> gallery = new ArrayList<String>();
        gallery.add("Image List");
        gallery.add("Image Upload");


        expandableListDetail.put("Dashboard", dashboard);
        expandableListDetail.put("Usage Report", usageReport);
        expandableListDetail.put("Book", book);
        expandableListDetail.put("Gallery", gallery);
        return expandableListDetail;
    }
}
