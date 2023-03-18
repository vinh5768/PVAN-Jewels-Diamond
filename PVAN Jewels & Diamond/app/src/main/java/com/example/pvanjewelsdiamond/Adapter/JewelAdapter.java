package com.example.pvanjewelsdiamond.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pvanjewelsdiamond.Activity.DetailActivity;
import com.example.pvanjewelsdiamond.Api.ApiModel.PhotoApi;
import com.example.pvanjewelsdiamond.Api.ApiService;
import com.example.pvanjewelsdiamond.Model.Photo;
import com.example.pvanjewelsdiamond.Model.Product;
import com.example.pvanjewelsdiamond.R;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Query;

public class JewelAdapter extends RecyclerView.Adapter<JewelAdapter.JewelViewHolder>{

    private List<Product> mProduct;
    private Context mContext;
    private List<Photo> mListPhoto;

    public JewelAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setData(List<Product> list){
        this.mProduct = list;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public JewelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_jewels,parent,false);
        return new JewelViewHolder(view);
    }
    
    

    @Override
    public void onBindViewHolder(@NonNull JewelViewHolder holder, int position) {
        Product product = mProduct.get(position);
        if (product == null)
            return;

        getPhoto(product.getID_Product(), new PhotoInterface() {
            @Override
            public void onResponse(List<Photo> list) {
                mListPhoto = list;
                Picasso.with(mContext).load(mListPhoto.get(1).getURL()).into(holder.img_product);
            }
        });

//        Picasso.with(mContext).load(getPhoto()).into(holder.img_product);
        holder.txt_product_name.setText(product.getProduct_Name());
        holder.txt_price.setText(product.getPrice()+"");

        holder.cv_item_product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, DetailActivity.class);
                intent.putExtra("list_photo", (Serializable) mListPhoto);
                intent.putExtra("list_product", product);
                mContext.startActivity(intent);
            }
        });



    }
    public interface PhotoInterface{
        void onResponse(List<Photo> list);
    }

    private void getPhoto(int Id, PhotoInterface photoInterface) {
        ApiService.apiService.getPhotoByID(Id).enqueue(new Callback<PhotoApi>() {
            @Override
            public void onResponse(Call<PhotoApi> call, Response<PhotoApi> response) {
                if (response.body() != null && response.body().isSuccess()){
                    photoInterface.onResponse(response.body().getmListPhoto());
                }
            }

            @Override
            public void onFailure(Call<PhotoApi> call, Throwable t) {
                Log.i("Error", t.toString());
            }
        });
    }

    @Override
    public int getItemCount() {
        if (mProduct != null)
            return mProduct.size();
        return 0;
    }

    public class JewelViewHolder extends RecyclerView.ViewHolder{
        CardView cv_item_product;
        ImageView img_product;
        TextView txt_product_name, txt_price;

        public JewelViewHolder(@NonNull View itemView) {
            super(itemView);

            img_product = itemView.findViewById(R.id.img_product);
            txt_price = itemView.findViewById(R.id.txt_price);
            txt_product_name = itemView.findViewById(R.id.txt_product_name);
            cv_item_product = itemView.findViewById(R.id.cv_item_product);

        }
    }
}
