package com.example.baitaplonoop.util;

import javafx.collections.ObservableList;
import javafx.scene.control.Hyperlink;

public class BreadCrumb {
    public static void addBreadCrumb(ObservableList<Hyperlink> breadcrumbList, ObservableList<Integer> level, int a, Hyperlink hyperlink){
       while(level.get(level.size()-1)>=a){
           breadcrumbList.remove(level.size()-1);
           level.remove(level.size()-1);
       }
         breadcrumbList.add(hyperlink);
         level.add(a);
    }
    public static void changeBreadCrumb(ObservableList<Hyperlink> breadcrumbList,ObservableList<Integer> level,Hyperlink hyperlink){
       int i=0;
        for(;i<breadcrumbList.size();i++)
            if (breadcrumbList.get(i).getText().equals(hyperlink.getText())) {
                break;
            }
        breadcrumbList.remove(i+1,breadcrumbList.size());
        level.remove(i+1,level.size());
    }
}
