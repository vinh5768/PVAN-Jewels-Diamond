package com.example.pvanjewelsdiamond.Helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.ContentView;
import androidx.annotation.Nullable;

import com.example.pvanjewelsdiamond.Model.Product;

import java.util.Date;

public class MySQLCartHelper extends SQLiteOpenHelper {
    private Context mContext;
    private static final String DATABASE_NAME = "pvan.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "Cart";
    private static final String ID_PRODUCT = "ID_Product";
    private static final String  PRODUCT_NAME = "Product_Name";
    private static final String  ID_CATEGORY = "ID_Category";
    private static final String  ID_MATERIAL = "ID_Material";
    private static final String  QUANTITY = "Quantity";
    private static final String  PRICE = "Price";
    private static final String  DESCRIPTION = "Description";
    private static final String  COUNT = "Count";


    public MySQLCartHelper(@Nullable Context context) {
        super(context,DATABASE_NAME, null, DATABASE_VERSION);
        this.mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE "+ TABLE_NAME+ " (" + ID_PRODUCT + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                PRODUCT_NAME + " TEXT, " +
                ID_CATEGORY + " INTEGER, " +
                ID_MATERIAL + " INTEGER, " +
                QUANTITY + " INTEGER, " +
                PRICE + " INTEGER, " +
                DESCRIPTION + " TEXT, " +
                COUNT + " INTEGER);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(db);
    }

    public void addCart(Product item, int count){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(PRODUCT_NAME, item.getProduct_Name());
        cv.put(ID_CATEGORY, item.getID_Category());
        cv.put(ID_MATERIAL, item.getID_Material());
        cv.put(QUANTITY, item.getQuantity());
        cv.put(PRICE, item.getPrice());
        cv.put(DESCRIPTION, item.getDescription());
        cv.put(COUNT, count);


        long result = db.insert(TABLE_NAME,null, cv);
        if (result == -1 )
            Toast.makeText(mContext, "Thêm vào giỏ thất bại", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(mContext, "Thêm vào giỏ thành công", Toast.LENGTH_SHORT).show();
        db.close();
    }

}
