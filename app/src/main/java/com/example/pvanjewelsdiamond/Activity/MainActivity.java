package com.example.pvanjewelsdiamond.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import com.example.pvanjewelsdiamond.Adapter.ViewPageAdapter;
import com.example.pvanjewelsdiamond.Model.Product;
import com.example.pvanjewelsdiamond.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity {
    private static BottomNavigationView navigationView;
    private static ViewPager vp_home;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mapping();
        castOnEventNavigation();
        setUpViewPage();

    }

    private void setUpViewPage() {
        ViewPageAdapter viewPageAdapter = new ViewPageAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        vp_home.setAdapter(viewPageAdapter);

    }

    private void castOnEventNavigation() {
        navigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId ()) {
                case R.id.navigation_shop:
                    vp_home.setCurrentItem(0);
                    break;
                case R.id.navigation_find:
                    vp_home.setCurrentItem(1);
                    break;
                case R.id.navigation_cart:
                    vp_home.setCurrentItem(2);
                    break;
                case R.id.navigation_profile:
                    vp_home.setCurrentItem(3);
                    break;
            }
            return true;
        });

        vp_home.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                onChanceNavigation(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
    private static void onChanceNavigation(int position) {
        switch (position){
            case 0:
                navigationView.getMenu().findItem(R.id.navigation_shop).setChecked(true);
                break;
            case 1:
                navigationView.getMenu().findItem(R.id.navigation_find).setChecked(true);
                break;
            case 2:
                navigationView.getMenu().findItem(R.id.navigation_cart).setChecked(true);
                break;
            case 3:
                navigationView.getMenu().findItem(R.id.navigation_profile).setChecked(true);
                break;
        }
    }


    public static void castOnEventFindListener(){
        vp_home.setCurrentItem(1);
        onChanceNavigation(1);
    }
    public static void castOnEventHomeListener(){
        vp_home.setCurrentItem(0);
        onChanceNavigation(0);
    }
    public static void setViewPage(int position){
        onChanceNavigation(position);
    }



    private void mapping() {
        navigationView = findViewById(R.id.navigation);
        vp_home = findViewById(R.id.vp_home);

    }

    public final void goToDetailsProduct(Product product){
        Intent intent = new Intent(MainActivity.this, DetailActivity.class);
        startActivity(intent);
    }
}