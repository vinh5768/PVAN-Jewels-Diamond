package com.example.pvanjewelsdiamond.Model;

public class Account {
    private Integer ID_Account;
    private String User_Name;
    private String Password;
    private String Customer_Name;
    private String Address;
    private String Phone_Number;

    public Account(Integer ID_Account, String user_Name, String password, String customer_Name, String address, String phone_Number) {
        this.ID_Account = ID_Account;
        User_Name = user_Name;
        Password = password;
        Customer_Name = customer_Name;
        Address = address;
        Phone_Number = phone_Number;
    }

    public Account() {
    }

    public Integer getID_Account() {
        return ID_Account;
    }

    public void setID_Account(Integer ID_Account) {
        this.ID_Account = ID_Account;
    }

    public String getUser_Name() {
        return User_Name;
    }

    public void setUser_Name(String user_Name) {
        User_Name = user_Name;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getCustomer_Name() {
        return Customer_Name;
    }

    public void setCustomer_Name(String customer_Name) {
        Customer_Name = customer_Name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getPhone_Number() {
        return Phone_Number;
    }

    public void setPhone_Number(String phone_Number) {
        Phone_Number = phone_Number;
    }
}
