package com.microlearning.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.microlearning.model.GalleryItem;
import com.sinavtime.microlearning.R;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.List;

public class GalleryAdapter extends PagerAdapter {

    private Context context;
    private LayoutInflater inflater;
    List<GalleryItem> galleryItems;

    public GalleryAdapter(List<GalleryItem> galleryItems, Context context) {
        this.galleryItems = galleryItems;
        this.context = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return galleryItems.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = inflater.inflate(R.layout.content, container, false);
        ImageView imageView = view.findViewById(R.id.image);
        Picasso.with(context).load(new File(galleryItems.get(position).imageUri)).fit().into(imageView);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((FrameLayout) object);
    }
}
