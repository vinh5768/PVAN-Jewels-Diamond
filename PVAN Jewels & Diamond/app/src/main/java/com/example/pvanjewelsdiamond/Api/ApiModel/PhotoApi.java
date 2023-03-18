package com.example.pvanjewelsdiamond.Api.ApiModel;

import com.example.pvanjewelsdiamond.Model.Photo;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PhotoApi {
    private boolean Success;
    @SerializedName("Data")
    private List<Photo> mListPhoto;

    public PhotoApi(boolean success, List<Photo> mListPhoto) {
        Success = success;
        this.mListPhoto = mListPhoto;
    }

    public PhotoApi() {
    }


    public boolean isSuccess() {
        return Success;
    }

    public void setSuccess(boolean success) {
        Success = success;
    }

    public List<Photo> getmListPhoto() {
        return mListPhoto;
    }
    public void setmListPhoto(List<Photo> mListPhoto) {
        this.mListPhoto = mListPhoto;
    }
}
