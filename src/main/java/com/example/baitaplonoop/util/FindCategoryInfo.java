package com.example.baitaplonoop.util;

import javafx.scene.control.TreeItem;

public class FindCategoryInfo {
    //find Name of category from treeView
    public static String findCategoryName(String a) {
        int n = a.length() - 1;
        int i = n - 1;
        if (a.charAt(n) != ')') return a;
        while (a.charAt(i) != '(' && i >= 0) {
            if (a.charAt(i) < '0' || a.charAt(i) > '9') return a;
            i--;
        }
        return a.substring(0, i);
    }
    //find number of question of category
    public static String findNumberQuestionsOfACategory(TreeItem<String> item) {
        String a = item.getValue();
        int n = a.length() - 1;
        int i = n - 1;
        if (a.charAt(n) != ')') return "0";
        while (a.charAt(i) != '(' && i >= 0) {
            if (a.charAt(i) < '0' || a.charAt(i) > '9') return "0";
            i--;
        }
        return a.substring(i + 1, n);
    }

    public static String findNumberQuestionOfCategoryAndSubCategories(TreeItem<String> item) {
        String b = findNumberQuestionsOfACategory(item);
        if (item.isLeaf()) {
            return b;
        } else {
            for (TreeItem<String> item1 : item.getChildren()) {
                b = Integer.toString(Integer.parseInt(b) + Integer.parseInt(FindCategoryInfo.findNumberQuestionOfCategoryAndSubCategories(item1)));
            }
            return b;
        }
    }

}
