package com.example.pvanjewelsdiamond.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pvanjewelsdiamond.Activity.DetailActivity;
import com.example.pvanjewelsdiamond.Adapter.CartAdapter;
import com.example.pvanjewelsdiamond.Adapter.FindAdapter;
import com.example.pvanjewelsdiamond.Api.ApiModel.ProductApi;
import com.example.pvanjewelsdiamond.Api.ApiService;
import com.example.pvanjewelsdiamond.Model.DBLocal.DataLocalManager;
import com.example.pvanjewelsdiamond.Model.Product;
import com.example.pvanjewelsdiamond.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FindFragment extends Fragment {
    View mView;
    RecyclerView rcv_find;
    private int ID_Category;
    private List<Product> mListProduct;

    @Override
    public void onResume() {
        super.onResume();
        mapping();
        getProductData(new FindInterface() {
            @Override
            public void onResponse(List<Product> list) {
                mListProduct = list;
                searchByType(ID_Category);
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.find_fragment, container, false);
        return mView;
    }

    private void searchByType(int id_category) {
        List<Product> list = new ArrayList<>();
        if (ID_Category > 0){
            for (int i =0; i< mListProduct.size(); i++){
                if (mListProduct.get(i).getID_Category() == id_category){
                    list.add(mListProduct.get(i));
                }
            }
        }else list =mListProduct;

        FindAdapter adapter = new FindAdapter(getContext());
        adapter.setData(list);
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);

        rcv_find.setLayoutManager(layoutManager);
        rcv_find.setAdapter(adapter);




        DataLocalManager.putIDCategory(0);
    }

    public interface FindInterface{
        void onResponse(List<Product> list);
    }

    public void getProductData(FindInterface i){
        ApiService.apiService.getAllProductInData().enqueue(new Callback<ProductApi>() {
            @Override
            public void onResponse(Call<ProductApi> call, Response<ProductApi> response) {
                if (response != null && response.body().isSuccess()){
                    i.onResponse(response.body().getmListProduct());
                }
            }

            @Override
            public void onFailure(Call<ProductApi> call, Throwable t) {

            }
        });
    }

    private void mapping() {
        rcv_find = mView.findViewById(R.id.rcv_find);
        ID_Category = DataLocalManager.getIDCategory();
    }
}
