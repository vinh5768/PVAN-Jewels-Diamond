package com.example.pvanjewelsdiamond.Model;

public class Cart {
    private Product Product;
    private int Quantity;

    public Cart(Product product, int quantity) {
        Product = product;
        Quantity = quantity;
    }

    public Cart() {
    }

    public Product getProduct() {
        return Product;
    }

    public void setProduct(Product product) {
        Product = product;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }
}
