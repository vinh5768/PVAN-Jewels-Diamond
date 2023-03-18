package com.example.pvanjewelsdiamond.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pvanjewelsdiamond.Api.ApiService;
import com.example.pvanjewelsdiamond.Model.Account;
import com.example.pvanjewelsdiamond.Model.DBLocal.DataLocalManager;
import com.example.pvanjewelsdiamond.R;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    Button btn_Login;
    EditText et_UserName, et_Password;
    TextView txt_forgotPassword, txt_gotoRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mapping();
        setEditText();
        catchOnEvent();
    }

    private void setEditText() {
        Account account = DataLocalManager.getAccount();
        if (account != null){
            et_UserName.setText(account.getUser_Name());
            et_Password.setText(account.getPassword());
        }
    }

    private void catchOnEvent() {
        btn_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAccount(new CallBackAccount() {
                    @Override
                    public void onResponse(Account account) {
                        Account tmp = account;
                        DataLocalManager.putAccount(tmp);
                        DataLocalManager.putStatus(true);
                        finish();
                    }
                });
            }
        });
    }

    public interface CallBackAccount{
        void onResponse(Account account);
    }

    private void checkAccount(CallBackAccount callBackAccount) {
    String userName = et_UserName.getText().toString();
    String password = et_Password.getText().toString();

    ApiService.apiService.getAccount(userName, password).enqueue(new Callback<Account>() {
        @Override
        public void onResponse(Call<Account> call, Response<Account> response) {

            if (response.body() != null){
                callBackAccount.onResponse(response.body());
            }
        }

        @Override
        public void onFailure(Call<Account> call, Throwable t) {
            Log.i("ERROR", t.toString());
        }
    });



    }

    private void mapping() {

        btn_Login = findViewById(R.id.btn_Login);
        et_UserName = findViewById(R.id.et_UserName);
        et_Password = findViewById(R.id.et_Password);
        txt_forgotPassword = findViewById(R.id.txt_forgotPassword);
        txt_gotoRegister = findViewById(R.id.txt_gotoRegister);

    }
}