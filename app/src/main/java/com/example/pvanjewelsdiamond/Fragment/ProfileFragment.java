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
import androidx.fragment.app.FragmentManager;

import com.example.pvanjewelsdiamond.Activity.ContactActivity;
import com.example.pvanjewelsdiamond.Activity.DetailActivity;
import com.example.pvanjewelsdiamond.Activity.LoginActivity;
import com.example.pvanjewelsdiamond.Activity.ReportActivity;
import com.example.pvanjewelsdiamond.Activity.StoreInformationActivity;
import com.example.pvanjewelsdiamond.Model.Account;
import com.example.pvanjewelsdiamond.Model.DBLocal.DataLocalManager;
import com.example.pvanjewelsdiamond.R;

public class ProfileFragment extends Fragment {
    View mView;
    Button btn_login_profile, btn_notice_profile, btn_report_profile, btn_store_profile, btn_contact_profile, btn_logout_profiles;
    Account account;
    private static boolean STATUS = false;


    @Override
    public void onResume() {
        super.onResume();
        Account tmp = DataLocalManager.getAccount();
        if (tmp != null)
            account = tmp;

        setStatus();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.profile_fragment, container, false);
        mapping();
        CatchOnEventListener();
        return mView;
    }

    private void setStatus() {
        STATUS = DataLocalManager.getStatus();

        if (STATUS && account != null){
            btn_logout_profiles.setVisibility(View.VISIBLE);
            btn_login_profile.setText(account.getUser_Name());
        }else {
            btn_login_profile.setText("Đăng nhập| Đăng ký");
            btn_logout_profiles.setVisibility(View.GONE);
        }
    }

    private void CatchOnEventListener() {
        // bắt sự kiện đăng nhập
        if (!STATUS || account == null){
            btn_login_profile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                }
            });
        }

        // Bắt sự kiện nhận thông báo
        btn_notice_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        // Bắt sự kiện lấy địa chỉ cửa hàng
        btn_store_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), StoreInformationActivity.class);
                startActivity(intent);
            }
        });
        // Bắt sự kiện lấy thông tin cửa hàng
        btn_contact_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ContactActivity.class);
                startActivity(intent);
            }
        });
        // Bắt sự kiện report
        btn_report_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ReportActivity.class);
                startActivity(intent);
            }
        });
        btn_logout_profiles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataLocalManager.putStatus(false);
                setStatus();
            }
        });
    }

    private void mapping() {
        btn_login_profile = mView.findViewById(R.id.btn_login_profile);
        btn_notice_profile = mView.findViewById(R.id.btn_notice_profile);
        btn_report_profile = mView.findViewById(R.id.btn_report_profile);
        btn_store_profile = mView.findViewById(R.id.btn_store_profile);
        btn_contact_profile = mView.findViewById(R.id.btn_contact_profile);
        btn_logout_profiles = mView.findViewById(R.id.btn_logout_profiles);
    }
}
