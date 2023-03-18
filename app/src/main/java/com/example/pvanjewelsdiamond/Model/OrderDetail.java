package com.example.pvanjewelsdiamond.Model;

public class OrderDetail {
    private Integer ID_Detail;
    private int ID_Product;
    private int Quantity;
    private int ID_Order;

    public OrderDetail(Integer ID_Detail, int ID_Product, int quantity, int ID_Order) {
        this.ID_Detail = ID_Detail;
        this.ID_Product = ID_Product;
        Quantity = quantity;
        this.ID_Order = ID_Order;
    }

    public OrderDetail() {
    }

    public Integer getID_Detail() {
        return ID_Detail;
    }

    public void setID_Detail(Integer ID_Detail) {
        this.ID_Detail = ID_Detail;
    }

    public int getID_Product() {
        return ID_Product;
    }

    public void setID_Product(int ID_Product) {
        this.ID_Product = ID_Product;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    public int getID_Order() {
        return ID_Order;
    }

    public void setID_Order(int ID_Order) {
        this.ID_Order = ID_Order;
    }
}
