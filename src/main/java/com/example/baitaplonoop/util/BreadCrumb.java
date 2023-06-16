package com.example.baitaplonoop.util;

import javafx.collections.ObservableList;
import javafx.scene.control.Hyperlink;

import java.util.Objects;

public class BreadCrumb {
    public static void addBreadCrumb(ObservableList<Hyperlink> breadcrumbList, ObservableList<Integer> level,int a,Hyperlink hyperlink){
         for(int i=1;i<breadcrumbList.size();i++){
           if(level.get(i)>=a){
               breadcrumbList.remove(i);
               level.remove(i);
           }
         }
         breadcrumbList.add(hyperlink);
         level.add(a);
    }
    public static void changeBreadCrumb(ObservableList<Hyperlink> breadcrumbList,ObservableList<Integer> level,Hyperlink hyperlink){
        for(int i=0;i<breadcrumbList.size();i++){
            if(Objects.equals(breadcrumbList.get(i).getText(), hyperlink.getText())){
                breadcrumbList.remove(i+1,breadcrumbList.size());
                level.remove(i+1,breadcrumbList.size());
                break;
            }
        }
    }
}
