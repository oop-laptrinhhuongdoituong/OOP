package com.example.baitaplonoop.Model;

public class Category {
    private String parentID;
    private String categoryName;
    private String categoryID;
    private String categoryInfo;

    public Category() {
    }

    public Category(String parentID, String categoryName, String categoryID, String categoryInfo) {
        this.parentID = parentID;
        this.categoryName = categoryName;
        this.categoryID = categoryID;
        this.categoryInfo = categoryInfo;
    }

    public String getParentID() {
        return parentID;
    }

    public void setParentID(String parentID) {
        this.parentID = parentID;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
    }

    public String getCategoryInfo() {
        return categoryInfo;
    }

    public void setCategoryInfo(String categoryInfo) {
        this.categoryInfo = categoryInfo;
    }
}