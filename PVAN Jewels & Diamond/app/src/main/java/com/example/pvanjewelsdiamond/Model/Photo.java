package com.example.pvanjewelsdiamond.Model;

import java.io.Serializable;

public class Photo implements Serializable {
    private int ID_Photo;

    private int ID_Product;

    private String URL;

    public Photo(int ID_Photo, int ID_Product, String URL) {
        this.ID_Photo = ID_Photo;
        this.ID_Product = ID_Product;
        this.URL = URL;
    }

    public Photo() {
    }

    public int getID_Photo() {
        return ID_Photo;
    }

    public void setID_Photo(int ID_Photo) {
        this.ID_Photo = ID_Photo;
    }

    public int getID_Product() {
        return ID_Product;
    }

    public void setID_Product(int ID_Product) {
        this.ID_Product = ID_Product;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }
}
