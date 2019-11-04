package com.jkim.shrutsangam.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExpandableAdminListDataPump {
    public static HashMap<String, List<String>> getData() {
        HashMap<String, List<String>> expandableListDetail = new HashMap<String, List<String>>();

        List<String> dashboard = new ArrayList<String>();


        List<String> book = new ArrayList<String>();
        book.add("Book Issues Form");
        book.add("Book Receive Form");

        List<String> member = new ArrayList<String>();
        member.add("Member List");
        member.add("Add Member");


        expandableListDetail.put("Dashboard", dashboard);
//        expandableListDetail.put("Member", member);
        expandableListDetail.put("Book", book);
        expandableListDetail.put("Member", member);
        return expandableListDetail;
    }
}
