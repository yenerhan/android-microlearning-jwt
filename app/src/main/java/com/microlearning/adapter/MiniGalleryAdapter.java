package com.microlearning.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.microlearning.activities.SquareLayout;
import com.microlearning.interfaces.GalleryViewListener;
import com.microlearning.model.GalleryItem;
import com.microlearning.viewHolder.MiniGalleryView2Holder;
import com.microlearning.viewHolder.MiniGalleryViewHolder;
import com.sinavtime.microlearning.R;

import java.util.List;

public class MiniGalleryAdapter extends RecyclerView.Adapter {

    Context context;
    LayoutInflater inflater;
    GalleryViewListener galleryViewListener;

    List<GalleryItem> galleryItems;
    GalleryItem mCurrentSelected;

    public MiniGalleryAdapter(Context context, List<GalleryItem> galleryItems, GalleryViewListener galleryViewListener, int currentPosition) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.galleryViewListener = galleryViewListener;
        if (galleryItems != null) {
            this.galleryItems = galleryItems;
        }

        mCurrentSelected = galleryItems.get(currentPosition);
        mCurrentSelected.isSelected = true;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view;
        SquareLayout squareLayout;
        switch (viewType) {
            case 2:
                view = inflater.inflate(R.layout.custom_row_gallery_strip_item, viewGroup, false);
                squareLayout = view.findViewById(R.id.squareLayout);
                return new MiniGalleryViewHolder(squareLayout);
            case 1:
                view = inflater.inflate(R.layout.custom_row_gallery_strip_button, viewGroup, false);
                squareLayout = view.findViewById(R.id.squareLayout);
                return new MiniGalleryView2Holder(squareLayout);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        GalleryItem galleryItem = galleryItems.get(position);
        if(galleryItem.isLastItem){
            ((MiniGalleryView2Holder) holder).setData(galleryViewListener, position, context);
        }else {
            ((MiniGalleryViewHolder) holder).setData(galleryItem, galleryViewListener, position, context);
        }

    }

    @Override
    public int getItemCount() {
        return galleryItems.size();
    }

    @Override
    public int getItemViewType(int position) {
        if(galleryItems.size()==0 || galleryItems.get(position).isLastItem){
            return 1;
        }
        return 2;
    }

    public void setSelected(int position) {
        mCurrentSelected.isSelected = false;
        notifyItemChanged(galleryItems.indexOf(mCurrentSelected));
        galleryItems.get(position).isSelected = true;
        notifyItemChanged(position);
        mCurrentSelected = galleryItems.get(position);

    }

    public void removeSelection() {
        mCurrentSelected.isSelected = false;
    }
}


