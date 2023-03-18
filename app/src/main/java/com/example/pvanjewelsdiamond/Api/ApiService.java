package com.example.pvanjewelsdiamond.Api;

import android.widget.Gallery;

import com.example.pvanjewelsdiamond.Api.ApiModel.CategoryApi;
import com.example.pvanjewelsdiamond.Api.ApiModel.Message;
import com.example.pvanjewelsdiamond.Api.ApiModel.PhotoApi;
import com.example.pvanjewelsdiamond.Api.ApiModel.ProductApi;
import com.example.pvanjewelsdiamond.Model.Account;
import com.example.pvanjewelsdiamond.Model.Cart;
import com.example.pvanjewelsdiamond.Model.Category;
import com.example.pvanjewelsdiamond.Model.Order;
import com.example.pvanjewelsdiamond.Model.OrderDetail;
import com.example.pvanjewelsdiamond.Model.Photo;
import com.example.pvanjewelsdiamond.Model.Product;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Currency;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface ApiService {
//    Gson gson = new GsonBuilder().setDateFormat("dd/MM/yyyy HH:mm").create();

    Gson gson = new GsonBuilder()
            .setLenient()
            .create();

    ApiService apiService = new Retrofit.Builder()
            .baseUrl("http://192.168.1.10:3000/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiService.class);

    // Lấy đữ liệu tất cả các product
    @GET("/api/products")
    Call<ProductApi> getAllProductInData();

    // Lấy hình ảnh theo Id product
    @GET("/api/photo/search")
    Call<PhotoApi> getPhotoByID(@Query("id_product") int id_product);

    // Lấy các loại category
    @GET("/api/category")
    Call<CategoryApi> getCategoryInData();

    // Đăng nhập vào ứng dụng
    @POST("/api/login")
    Call<Account> getAccount(@Query("User_Name") String userName, @Query("Password") String password);

    @POST("api/order")
    Call<Message> postOrder(@Body Order order);

    @POST("api/order/detail")
    Call<Message> postOrderDetail(@Body List<OrderDetail> mListDetail);

}
