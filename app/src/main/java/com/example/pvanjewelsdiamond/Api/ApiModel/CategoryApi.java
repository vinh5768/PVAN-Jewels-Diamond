package com.example.pvanjewelsdiamond.Api.ApiModel;

import com.example.pvanjewelsdiamond.Model.Category;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CategoryApi {
    private boolean Status;
    @SerializedName("Data")
    List<Category> listCategory;

    public CategoryApi(boolean status, List<Category> listCategory) {
        Status = status;
        this.listCategory = listCategory;
    }

    public CategoryApi() {
    }

    public boolean isStatus() {
        return Status;
    }

    public void setStatus(boolean status) {
        Status = status;
    }

    public List<Category> getListCategory() {
        return listCategory;
    }

    public void setListCategory(List<Category> listCategory) {
        this.listCategory = listCategory;
    }
}
