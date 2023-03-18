package com.example.pvanjewelsdiamond.Model.DBLocal;

import android.app.Application;

public class DBApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        DataLocalManager.init(getApplicationContext());
    }
}
