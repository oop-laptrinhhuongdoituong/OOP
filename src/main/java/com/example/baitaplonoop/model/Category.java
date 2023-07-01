package com.example.baitaplonoop.model;

public class Category {
    private String parentID;
    private String categoryID;

    public Category() {
    }

    public Category(String parentID, String categoryID) {
        this.parentID = parentID;
        this.categoryID = categoryID;
    }

    public String getParentID() {
        return parentID;
    }

    public void setParentID(String parentID) {
        this.parentID = parentID;
    }
    public String getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
    }
}
