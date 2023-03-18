package com.example.pvanjewelsdiamond.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.pvanjewelsdiamond.R;

public class ReportActivity extends AppCompatActivity {
    Button btn_send_report;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        mapping();
        castOnEvenClickButton();
    }

    private void castOnEvenClickButton() {
        btn_send_report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void mapping() {
        btn_send_report = findViewById(R.id.btn_send_report);
    }
}