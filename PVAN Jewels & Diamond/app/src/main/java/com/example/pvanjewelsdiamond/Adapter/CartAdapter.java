package com.example.pvanjewelsdiamond.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pvanjewelsdiamond.Api.ApiModel.PhotoApi;
import com.example.pvanjewelsdiamond.Api.ApiService;
import com.example.pvanjewelsdiamond.Fragment.CartFragment;
import com.example.pvanjewelsdiamond.Model.Cart;
import com.example.pvanjewelsdiamond.Model.Category;
import com.example.pvanjewelsdiamond.Model.DBLocal.DataLocalManager;
import com.example.pvanjewelsdiamond.Model.Photo;
import com.example.pvanjewelsdiamond.Model.Product;
import com.example.pvanjewelsdiamond.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder>{

    private List<Cart> mListCart;
    private Context mContext;

    public CartAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setData(List<Cart> list){
        this.mListCart = list;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        Cart cart = mListCart.get(position);
        if (cart == null)
            return;
        getPhoto(cart.getProduct().getID_Product(), new JewelAdapter.PhotoInterface() {
            @Override
            public void onResponse(List<Photo> list) {
                Picasso.with(mContext).load(list.get(0).getURL()).into(holder.img_cart);
            }
        });
        holder.txt_price_cart.setText(String.valueOf(cart.getQuantity()*cart.getProduct().getPrice()));
        holder.txt_productName_cart.setText(cart.getProduct().getProduct_Name());
        holder.btn_amount_cart.setText(String.valueOf(cart.getQuantity()));
        catchOnEventListener(holder, position);

    }

    private void catchOnEventListener(CartViewHolder holder, int position) {
        Cart cart = mListCart.get(position);
        holder.btn_minusProduct_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                catchOnEventListener(holder, position);
                if (cart.getQuantity()>1){
                    int amount = cart.getQuantity()-1;
                    mListCart.get(position).setQuantity(amount);

                    holder.btn_amount_cart.setText(String.valueOf(amount));
                    DataLocalManager.putListCart(mListCart);
                    CartFragment.setTotalPrice(mListCart);
                }

            }
        });
        holder.btn_addProduct_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int amount = cart.getQuantity()+1;
                mListCart.get(position).setQuantity(amount);

                holder.btn_amount_cart.setText(String.valueOf(amount));
                DataLocalManager.putListCart(mListCart);
                CartFragment.setTotalPrice(mListCart);
            }
        });

        holder.layout_delete.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onClick(View v) {
                mListCart.remove(cart);
                DataLocalManager.putListCart(mListCart);
                notifyDataSetChanged();
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
        if (mListCart != null)
            return mListCart.size();
        return 0;
    }

    public class CartViewHolder extends RecyclerView.ViewHolder{
        ImageView img_cart;
        Button btn_addProduct_cart,btn_minusProduct_cart, btn_amount_cart;
        TextView txt_price_cart, txt_productName_cart;
        LinearLayout layout_delete;
        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            img_cart = itemView.findViewById(R.id.img_cart);
            btn_addProduct_cart = itemView.findViewById(R.id.btn_addProduct_cart);
            btn_minusProduct_cart = itemView.findViewById(R.id.btn_minusProduct_cart);
            btn_amount_cart = itemView.findViewById(R.id.btn_amount_cart);
            txt_price_cart = itemView.findViewById(R.id.txt_price_cart);
            txt_productName_cart = itemView.findViewById(R.id.txt_productName_cart);
            layout_delete = itemView.findViewById(R.id.layout_delete);
        }
    }
}
