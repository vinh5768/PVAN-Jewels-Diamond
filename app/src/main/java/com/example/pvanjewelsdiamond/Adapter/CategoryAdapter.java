package com.example.pvanjewelsdiamond.Adapter;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pvanjewelsdiamond.Activity.MainActivity;
import com.example.pvanjewelsdiamond.Api.ApiService;
import com.example.pvanjewelsdiamond.Model.AdapterModel.HomePageModel;
import com.example.pvanjewelsdiamond.Model.Category;
import com.example.pvanjewelsdiamond.Model.DBLocal.DataLocalManager;
import com.example.pvanjewelsdiamond.Model.Product;
import com.example.pvanjewelsdiamond.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {
    private List<HomePageModel> mListHomePage;
    private Context mContext;


    public CategoryAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setData(List<HomePageModel> list){
        this.mListHomePage = list;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category, parent, false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        HomePageModel homePageModel = mListHomePage.get(position);
        if (homePageModel == null)
            return;


        holder.txt_category.setText(homePageModel.getCategory().getCategory_Name());

        JewelAdapter jewelAdapter = new JewelAdapter(this.mContext);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.mContext, RecyclerView.HORIZONTAL, false);
        holder.rcv_product.setLayoutManager(linearLayoutManager);

        jewelAdapter.setData(homePageModel.getmListProduct());
        holder.rcv_product.setAdapter(jewelAdapter);

        holder.more_product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataLocalManager.putIDCategory(homePageModel.getCategory().getID_Category());

                MainActivity.castOnEventFindListener();
            }
        });

    }
    @Override
    public int getItemCount() {
        if (mListHomePage != null)
            return mListHomePage.size();
        return 0;
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder{
        private TextView txt_category;
        private RecyclerView rcv_product;
        private LinearLayout more_product;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_category = itemView.findViewById(R.id.txt_category);
            rcv_product = itemView.findViewById(R.id.rcv_product);
            more_product = itemView.findViewById(R.id.more_product);
        }
    }
}
