package com.example.pvanjewelsdiamond.Model.DBLocal;

import android.content.Context;

import com.example.pvanjewelsdiamond.Model.Account;
import com.example.pvanjewelsdiamond.Model.Cart;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DataLocalManager {
    private static String LIST_CART = "mListCart";
    private static String ID_CATEGORY = "id_category";
    private static String ACCOUNT = "account";
    private static String STATUS = "status";
    private static DataLocalManager instance;
    private PVANSharedPreferences preferences;

    public static void init (Context context){
        instance = new DataLocalManager();
        instance.preferences = new PVANSharedPreferences(context);
    }

    public static DataLocalManager getInstance(){
        if (instance == null){
            instance = new DataLocalManager();
        }
        return instance;
    }

    public static void putListCart(List<Cart> list){
        Gson gson = new Gson();
        JsonArray jsonArray = gson.toJsonTree(list).getAsJsonArray();
        DataLocalManager.getInstance().preferences.putStringValue(LIST_CART, jsonArray.toString());
    }
    public static List<Cart> getListCart(){
        String tmp = DataLocalManager.getInstance().preferences.getListCart(LIST_CART);
        List<Cart> list = new ArrayList<>();
        try{
            JSONArray jsonArray = new JSONArray(tmp);
            Gson gson = new Gson();
            for (int i =0; i< jsonArray.length(); i++){
                Cart cart = gson.fromJson(jsonArray.getJSONObject(i).toString(), Cart.class);
                list.add(cart);
            }
        }catch (JSONException e){
            e.printStackTrace();
        }
        return list;
    }
    public static void putIDCategory(int id){
        DataLocalManager.getInstance().preferences.putIDCategory(ID_CATEGORY,id);
    }
    public static int getIDCategory(){
        return DataLocalManager.getInstance().preferences.getIDCategory(ID_CATEGORY);
    }
    public static void putAccount(Account account){
        Gson gson = new Gson();
        JsonObject jsonObject = gson.toJsonTree(account).getAsJsonObject();
        DataLocalManager.getInstance().preferences.putAccount(ACCOUNT, jsonObject.toString());
    }
    public static Account getAccount(){
        String tmp = DataLocalManager.getInstance().preferences.getAccount(ACCOUNT);
        Account account = new Account();
        Gson gson = new Gson();
        account = gson.fromJson(tmp, Account.class);


        return account;
    }

    public static void putStatus(boolean status){
        DataLocalManager.getInstance().preferences.putStatus(STATUS, status);
    }

    public static boolean getStatus(){
        return DataLocalManager.getInstance().preferences.getStatus(STATUS);
    }

}
