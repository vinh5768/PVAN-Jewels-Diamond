package com.example.pvanjewelsdiamond.Model.DBLocal;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.pvanjewelsdiamond.Model.Account;

public class PVANSharedPreferences {
    private final String KEY = "MY_DATA";
    private Context mContext;

    public PVANSharedPreferences(Context mContext) {
        this.mContext = mContext;
    }
    public void putStringValue(String key, String value){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(KEY, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public String getListCart(String key){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(KEY, Context.MODE_PRIVATE);

        return sharedPreferences.getString(key,"");
    }
    public void putIDCategory(String key, int value){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(KEY, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(key, value);
        editor.apply();
    }
    public int getIDCategory(String key){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(KEY, Context.MODE_PRIVATE);

        return sharedPreferences.getInt(key,0);
    }

    public void putAccount(String key, String value){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(KEY, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }
    public String getAccount(String key){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(KEY, Context.MODE_PRIVATE);

        return sharedPreferences.getString(key,"");
    }

    public void putStatus(String key, boolean value){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(KEY, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }
    public boolean getStatus(String key){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(KEY, Context.MODE_PRIVATE);

        return sharedPreferences.getBoolean(key,false);
    }
}
