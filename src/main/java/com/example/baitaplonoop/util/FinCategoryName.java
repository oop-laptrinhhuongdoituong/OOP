package com.example.baitaplonoop.util;

public class FinCategoryName {
    //find Name of category from treeView
    public static String findCategoryName(String a){
        int n=a.length()-1;
        int i=n-1;
        if(a.charAt(n)!=')')return a;
        while(a.charAt(i)!='('&&i>=0){
            if(a.charAt(i)<'0'||a.charAt(i)>'9')return a;
            i--;
        }
        return a.substring(0,i);
    }
}
