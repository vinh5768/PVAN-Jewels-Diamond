package com.example.pvanjewelsdiamond.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.pvanjewelsdiamond.Adapter.PhotoAdapter;
import com.example.pvanjewelsdiamond.Api.ApiService;
import com.example.pvanjewelsdiamond.Helper.MySQLCartHelper;
import com.example.pvanjewelsdiamond.Model.Cart;
import com.example.pvanjewelsdiamond.Model.DBLocal.DataLocalManager;
import com.example.pvanjewelsdiamond.Model.Photo;
import com.example.pvanjewelsdiamond.Model.Product;
import com.example.pvanjewelsdiamond.R;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;

public class DetailActivity extends AppCompatActivity {
    private ViewPager vp_img_product_detail;
    private CircleIndicator circle_indicator_detail;
    private PhotoAdapter adapter;
    private EditText edt_total_detail;
    private TextView txt_description_detail;
    private Button btn_add_detail;
    private List<Photo> mListPhoto;
    private Product item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mapping();
    }

    private void mapping() {
        vp_img_product_detail = findViewById(R.id.vp_img_product_detail);
        circle_indicator_detail = findViewById(R.id.circle_indicator_detail);
        txt_description_detail = findViewById(R.id.txt_description_detail);
        edt_total_detail = findViewById(R.id.edt_total_detail);
        btn_add_detail = findViewById(R.id.btn_add_detail);
        //Lấy dữ liệu
        getListPhoto();

        //Set dữ liệu lên adapter hình ảnh
        adapter = new PhotoAdapter(getApplicationContext(),mListPhoto);
        vp_img_product_detail.setAdapter(adapter);

        circle_indicator_detail.setViewPager(vp_img_product_detail);
        adapter.registerDataSetObserver(circle_indicator_detail.getDataSetObserver());

        txt_description_detail.setText(item.getDescription());
        edt_total_detail.setText(item.getPrice()+ "");

        btn_add_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Cart> list =  DataLocalManager.getListCart();
                boolean exit = true;
                for (int i = 0 ; i < list.size(); i++){
                    if (item.getID_Product() == list.get(i).getProduct().getID_Product()){
                        list.get(i).setQuantity(list.get(i).getQuantity() + 1);
                        exit = false;
                    }
                }
                if (exit){
                    list.add(new Cart(item, 1 ));
                }

                DataLocalManager.putListCart(list);
                finish();
            }
        });


    }



    private void getListPhoto() {
        Intent intent = getIntent();
        mListPhoto = (List<Photo>) intent.getSerializableExtra("list_photo");
        item = (Product) intent.getSerializableExtra("list_product");
    }
}