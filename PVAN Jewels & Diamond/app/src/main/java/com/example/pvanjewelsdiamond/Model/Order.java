package com.example.pvanjewelsdiamond.Model;

import java.util.Date;

public class Order {
    private Integer ID_Order;
    private int ID_Account;
    private Date Order_Date;
    private boolean Status;
    private String Description;

    public Order(int ID_Order, int ID_Account, Date order_Date, boolean status, String description) {
        this.ID_Order = ID_Order;
        this.ID_Account = ID_Account;
        Order_Date = order_Date;
        Status = status;
        Description = description;
    }

    public Order() {
    }

    public int getID_Order() {
        return ID_Order;
    }

    public void setID_Order(Integer ID_Order) {
        this.ID_Order = ID_Order;
    }

    public int getID_Account() {
        return ID_Account;
    }

    public void setID_Account(int ID_Account) {
        this.ID_Account = ID_Account;
    }

    public Date getOrder_Date() {
        return Order_Date;
    }

    public void setOrder_Date(Date order_Date) {
        Order_Date = order_Date;
    }

    public boolean getStatus() {
        return Status;
    }

    public void setStatus(boolean status) {
        Status = status;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }
}
