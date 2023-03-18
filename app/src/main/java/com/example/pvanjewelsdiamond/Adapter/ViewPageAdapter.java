package com.example.pvanjewelsdiamond.Adapter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.pvanjewelsdiamond.Fragment.CartFragment;
import com.example.pvanjewelsdiamond.Fragment.FindFragment;
import com.example.pvanjewelsdiamond.Fragment.HomeFragment;
import com.example.pvanjewelsdiamond.Fragment.ProfileFragment;

public class ViewPageAdapter extends FragmentPagerAdapter {


    public ViewPageAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new HomeFragment();
            case 1:
                return new FindFragment();
            case 2:
                return new CartFragment();
            case 3:
                return new ProfileFragment();

        }
        return new HomeFragment();
    }
    @Override
    public int getCount() {
        return 4;
    }


}
