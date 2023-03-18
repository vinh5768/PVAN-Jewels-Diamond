package com.example.pvanjewelsdiamond.Model.AdapterModel;

import com.example.pvanjewelsdiamond.Model.Category;
import com.example.pvanjewelsdiamond.Model.Product;

import java.util.List;

public class HomePageModel {
    private Category category;
    private List<Product> mListProduct;

    public HomePageModel(Category category, List<Product> mListProduct) {
        this.category = category;
        this.mListProduct = mListProduct;
    }

    public HomePageModel() {
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Product> getmListProduct() {
        return mListProduct;
    }

    public void setmListProduct(List<Product> mListProduct) {
        this.mListProduct = mListProduct;
    }
}
