package com.example.pvanjewelsdiamond.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.pvanjewelsdiamond.Model.Photo;
import com.example.pvanjewelsdiamond.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PhotoAdapter extends PagerAdapter {
    private Context mContext;
    private List<Photo> mListPhoto;

    public PhotoAdapter(Context mContext, List<Photo> mListPhoto) {
        this.mContext = mContext;
        this.mListPhoto = mListPhoto;
    }

    @Override
    public int getCount() {
        if (mListPhoto != null)
            return mListPhoto.size();
        return 0;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.item_photo, container, false);
        ImageView mImageView = view.findViewById(R.id.img_product_detail);
        mImageView.setAdjustViewBounds(true);

        mImageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        Photo photo = mListPhoto.get(position);

        if (photo != null){
            Picasso.with(mContext).load(photo.getURL()).into(mImageView);
        }
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
