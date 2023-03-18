package com.example.pvanjewelsdiamond.Model;

import java.io.Serializable;
import java.util.List;
public class Category implements Serializable {
    private int ID_Category;
    private String Category_Name;

    public Category(int ID_Category, String category_Name) {
        this.ID_Category = ID_Category;
        Category_Name = category_Name;
    }

    public Category() {
    }

    public int getID_Category() {
        return ID_Category;
    }

    public void setID_Category(int ID_Category) {
        this.ID_Category = ID_Category;
    }

    public String getCategory_Name() {
        return Category_Name;
    }

    public void setCategory_Name(String category_Name) {
        Category_Name = category_Name;
    }
}
