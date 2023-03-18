package com.example.pvanjewelsdiamond.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pvanjewelsdiamond.Activity.MainActivity;
import com.example.pvanjewelsdiamond.Adapter.CategoryAdapter;
import com.example.pvanjewelsdiamond.Api.ApiModel.CategoryApi;
import com.example.pvanjewelsdiamond.Api.ApiModel.ProductApi;
import com.example.pvanjewelsdiamond.Api.ApiService;
import com.example.pvanjewelsdiamond.Model.AdapterModel.HomePageModel;
import com.example.pvanjewelsdiamond.Model.Category;
import com.example.pvanjewelsdiamond.Model.Product;
import com.example.pvanjewelsdiamond.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {
    View mView;
    List<Category> mListCategory;
    List<Product> mListProduct;
    List<HomePageModel> mListHomePage;
    ImageView banner1;
    RecyclerView rcv_category_home;
    Context mContext;
    EditText search_edit_home;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.home_fragment, container, false);

        mContext = getContext();
        GetData(new WaitApiCallBack() {
            @Override
            public void onResponse() {
                getDataForCategoryAdapter();

                mapping();

                catchOnEventListener();
            }


        });

        return mView;
    }
    private void catchOnEventListener() {
        search_edit_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.castOnEventFindListener();
            }
        });
    }

    private void getDataForCategoryAdapter() {
        mListHomePage = new ArrayList<>();
        for (int i = 0; i < mListCategory.size(); i++){
            List<Product> list = new ArrayList<>();
            int count =0;
            for ( int j = 0; j < mListProduct.size(); j++){
                if (mListProduct.get(j).getID_Category() == mListCategory.get(i).getID_Category() && count < 5){
                    list.add(mListProduct.get(j));
                    count++;
                }
            }

            mListHomePage.add(new HomePageModel(mListCategory.get(i),list));
        }
    }

    private void mapping() {
        banner1 = mView.findViewById(R.id.baner1);
        setDataByBanner();
        rcv_category_home = mView.findViewById(R.id.rcv_category_home);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        rcv_category_home.setLayoutManager(linearLayoutManager);
        rcv_category_home.setNestedScrollingEnabled(false);

        CategoryAdapter adapter = new CategoryAdapter(getContext());

        adapter.setData(mListHomePage);

        rcv_category_home.setAdapter(adapter);

        search_edit_home = mView.findViewById(R.id.search_edit_home);

    }

    private void setDataByBanner() {
        Picasso.with(getContext()).load("https://www.pnj.com.vn/blog/wp-content/uploads/2021/06/Tuoi-At-Suu-1985-Hop-Mau-Gi-2021-1024x768.jpg").into(banner1);
    }

    public interface WaitApiCallBack {
        void onResponse();
    }

    private void GetData(WaitApiCallBack callBack) {
        mListCategory = new ArrayList<>();

        mListProduct = new ArrayList<>();

        ApiService.apiService.getCategoryInData().enqueue(new Callback<CategoryApi>() {
            @Override
            public void onResponse(Call<CategoryApi> call, Response<CategoryApi> response) {
//                Log.v("Error", response.body().getListCategory().toString());
                CategoryApi categoryApi = response.body();
                if (categoryApi != null && categoryApi.isStatus()){
                    mListCategory = categoryApi.getListCategory();

                }
            }

            @Override
            public void onFailure(Call<CategoryApi> call, Throwable t) {
                Log.v("Error", t.toString());
            }
        });

        //Gọi api lấy dữ liệu product từ sever
        ApiService.apiService.getAllProductInData().enqueue(new Callback<ProductApi>() {
            @Override
            public void onResponse(Call<ProductApi> call, Response<ProductApi> response) {

                if (response.body() != null && response.body().isSuccess()){
                    mListProduct = response.body().getmListProduct();
                    callBack.onResponse();
                }
            }

            @Override
            public void onFailure(Call<ProductApi> call, Throwable t) {
                Log.v("Error", t.toString());
            }
        });


    }
}
