package com.example.pvanjewelsdiamond.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pvanjewelsdiamond.Activity.LoginActivity;
import com.example.pvanjewelsdiamond.Activity.MainActivity;
import com.example.pvanjewelsdiamond.Activity.ThankActivity;
import com.example.pvanjewelsdiamond.Adapter.CartAdapter;
import com.example.pvanjewelsdiamond.Api.ApiModel.Message;
import com.example.pvanjewelsdiamond.Api.ApiService;
import com.example.pvanjewelsdiamond.Model.Cart;
import com.example.pvanjewelsdiamond.Model.DBLocal.DataLocalManager;
import com.example.pvanjewelsdiamond.Model.Order;
import com.example.pvanjewelsdiamond.Model.OrderDetail;
import com.example.pvanjewelsdiamond.Model.Product;
import com.example.pvanjewelsdiamond.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartFragment extends Fragment {
    private View view;
    private Button btn_purchaseCart;
    private static EditText et_total_cart, et_description_cart;
    private  RecyclerView rcv_cart;
    private  List<Cart> mListCart;

    @Override
    public void onResume() {
        super.onResume();
        dataHandingOnRecyclerView();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.cart_fragment, container, false);
        mapping();
        catchOnEventClickButton();
        return view;
    }

    public interface IPostOrder{
        void onResponse(int ID_Order);
    }

    private void catchOnEventClickButton() {

        getOderInformation();

        btn_purchaseCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (DataLocalManager.getStatus()){
                    if (DataLocalManager.getListCart().size() > 0){
                        postOrderToSever(new IPostOrder() {
                            @Override
                            public void onResponse(int ID_Order) {
                                postOrderDetailToServer(ID_Order);
                                Intent intent = new Intent(getContext(), ThankActivity.class);
                                startActivity(intent);
                            }
                        });
                    }else {
                        MainActivity.castOnEventHomeListener();
                    }
                }else {
                    Intent intent = new Intent(getActivity(),LoginActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    private void postOrderDetailToServer(int ID_Order) {
        List<Cart> mListCart = DataLocalManager.getListCart();
        List<OrderDetail> mListOrder = new ArrayList<>();
        for (int i = 0; i < mListCart.size(); i++){
            Product product = mListCart.get(i).getProduct();
            OrderDetail tmp = new OrderDetail(null,product.getID_Product(), mListCart.get(i).getQuantity(), ID_Order);
            mListOrder.add(tmp);
        }
        ApiService.apiService.postOrderDetail(mListOrder).enqueue(new Callback<Message>() {
            @Override
            public void onResponse(Call<Message> call, Response<Message> response) {
                if (response.body()!= null && response.body().isStatus()){
                    DataLocalManager.putListCart(new ArrayList<>());
                    Toast.makeText(getContext(),response.body().getMessage(), Toast.LENGTH_SHORT);
                }

            }

            @Override
            public void onFailure(Call<Message> call, Throwable t) {

            }
        });
    }

    private void postOrderToSever(IPostOrder i) {
        Order order = getOderInformation();
        ApiService.apiService.postOrder(order).enqueue(new Callback<Message>() {
            @Override
            public void onResponse(Call<Message> call, Response<Message> response) {
                Toast.makeText(getContext(), response.toString(), Toast.LENGTH_SHORT);
                i.onResponse(Integer.parseInt(response.body().getMessage()));
            }

            @Override
            public void onFailure(Call<Message> call, Throwable t) {
                Toast.makeText(getContext(), t.toString(), Toast.LENGTH_SHORT);
            }
        });
    }

    private Order getOderInformation() {
        Order order = new Order();
        order.setID_Order(null);
        order.setID_Account(DataLocalManager.getAccount().getID_Account());
        order.setStatus(true);
        order.setDescription(et_description_cart.getText().toString());
        order.setOrder_Date(null);
        
        return order;
    }

    public void dataHandingOnRecyclerView() {
        mListCart = DataLocalManager.getListCart();
        if (mListCart.size() > 0){
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
            rcv_cart.setLayoutManager(linearLayoutManager);

            CartAdapter adapter = new CartAdapter(getContext());
            adapter.setData(mListCart);
            rcv_cart.setAdapter(adapter);
        }
        setTotalPrice(mListCart);
    }

    public static void setTotalPrice(List<Cart> list){
        int price = 0;

        for (int i =0; i< list.size(); i++){
            price += list.get(i).getQuantity() * list.get(i).getProduct().getPrice();
        }
        et_total_cart.setText(String.valueOf(price));
    }

    private void mapping() {
        btn_purchaseCart = view.findViewById(R.id.btn_purchaseCart);
        rcv_cart = view.findViewById(R.id.rcv_cart);
        et_total_cart = view.findViewById(R.id.et_total_cart);
        et_description_cart = view.findViewById(R.id.et_description_cart);
        mListCart = new ArrayList<>();
    }
}
