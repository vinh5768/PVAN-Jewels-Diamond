package com.example.pvanjewelsdiamond.Model;

import java.io.Serializable;

public class Product implements Serializable {
    private int ID_Product;
    private String Product_Name;
    private int ID_Category;
    private int ID_Material;
    private int Quantity;
    private int Price;
    private String Description;


    // Getter Methods

    public int getID_Product() {
        return ID_Product;
    }

    public String getProduct_Name() {
        return Product_Name;
    }

    public int getID_Category() {
        return ID_Category;
    }

    public int getID_Material() {
        return ID_Material;
    }

    public int getQuantity() {
        return Quantity;
    }

    public int getPrice() {
        return Price;
    }

    public String getDescription() {
        return Description;
    }

    // Setter Methods

    public void setID_Product(int ID_Product) {
        this.ID_Product = ID_Product;
    }

    public void setProduct_Name(String Product_Name) {
        this.Product_Name = Product_Name;
    }

    public void setID_Category(int ID_Category) {
        this.ID_Category = ID_Category;
    }

    public void setID_Material(int ID_Material) {
        this.ID_Material = ID_Material;
    }

    public void setQuantity(int Quantity) {
        this.Quantity = Quantity;
    }

    public void setPrice(int Price) {
        this.Price = Price;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

}
