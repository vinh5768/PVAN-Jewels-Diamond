package com.example.pvanjewelsdiamond.Api.ApiModel;

import com.example.pvanjewelsdiamond.Model.Product;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProductApi {
    private boolean Success;
    @SerializedName("Data")
    private List<Product> mListProduct;

    public ProductApi(boolean success, List<Product> mListProduct) {
        Success = success;
        this.mListProduct = mListProduct;
    }

    public ProductApi() {
    }

    public boolean isSuccess() {
        return Success;
    }

    public void setSuccess(boolean success) {
        Success = success;
    }

    public List<Product> getmListProduct() {
        return mListProduct;
    }

    public void setmListProduct(List<Product> mListProduct) {
        this.mListProduct = mListProduct;
    }
}
