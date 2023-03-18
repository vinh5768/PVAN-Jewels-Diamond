package com.example.pvanjewelsdiamond.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pvanjewelsdiamond.Activity.DetailActivity;
import com.example.pvanjewelsdiamond.Api.ApiModel.PhotoApi;
import com.example.pvanjewelsdiamond.Api.ApiService;
import com.example.pvanjewelsdiamond.Model.Cart;
import com.example.pvanjewelsdiamond.Model.Photo;
import com.example.pvanjewelsdiamond.Model.Product;
import com.example.pvanjewelsdiamond.R;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FindAdapter extends RecyclerView.Adapter<FindAdapter.FindViewHolder> {
    private Context mContext;
    private List<Product> mListProduct;
    private List<Photo> mListPhoto;

    public FindAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setData(List<Product> list){
        this.mListProduct = list;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public FindViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_find, parent, false);
        return new FindViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FindViewHolder holder, int position) {
        Product item = mListProduct.get(position);
        if (item == null)
            return;
        getPhoto(item.getID_Product(), new JewelAdapter.PhotoInterface() {
            @Override
            public void onResponse(List<Photo> list) {
                mListPhoto = list;
                Picasso.with(mContext).load(list.get(0).getURL()).into(holder.img_product_find);
            }
        });
        holder.txt_name_find.setText(item.getProduct_Name());
        holder.txt_price_find.setText(String.valueOf(item.getPrice()));

        holder.layout_item_find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, DetailActivity.class);
                intent.putExtra("list_photo", (Serializable) mListPhoto);
                intent.putExtra("list_product", item);
                mContext.startActivity(intent);

            }
        });


    }
    private void getPhoto(int Id, JewelAdapter.PhotoInterface photoInterface) {
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
        if (mListProduct != null)
            return mListProduct.size();
        return 0;
    }

    public class FindViewHolder extends RecyclerView.ViewHolder {
        ImageView img_product_find;
        TextView txt_price_find, txt_name_find;
        LinearLayout layout_item_find;
        public FindViewHolder(@NonNull View itemView) {
            super(itemView);
            img_product_find = itemView.findViewById(R.id.img_product_find);
            txt_price_find = itemView.findViewById(R.id.txt_price_find);
            txt_name_find = itemView.findViewById(R.id.txt_name_find);
            layout_item_find = itemView.findViewById(R.id.layout_item_find);
        }
    }

}
