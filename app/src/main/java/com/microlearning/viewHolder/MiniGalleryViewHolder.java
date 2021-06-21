package com.microlearning.viewHolder;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.microlearning.activities.ScreenUtils;
import com.microlearning.interfaces.GalleryViewListener;
import com.microlearning.model.GalleryItem;
import com.sinavtime.microlearning.R;

/**
 * Created by erhan.yener on 24.10.2018.
 */

public class MiniGalleryViewHolder extends RecyclerView.ViewHolder {

    // Mini Gallery icerisindeki objeler
    public ImageView image_view_thumbnail;


    public MiniGalleryViewHolder(final View itemView) {
        super(itemView);
        this.image_view_thumbnail = (ImageView) itemView.findViewById(R.id.image_view_thumbnail);
    }


    public void setData(final GalleryItem miniGalleryItem, final GalleryViewListener galleryViewListener, final int position, Context context) {
        final int thumbSize = ScreenUtils.getScreenWidth(context) / 6;
        Bitmap ThumbImage = ThumbnailUtils.extractThumbnail(BitmapFactory.decodeFile(miniGalleryItem.getImageUri()), thumbSize, thumbSize);
        image_view_thumbnail.setImageBitmap(ThumbImage);
        if (miniGalleryItem.isSelected) {
            image_view_thumbnail.setBackgroundColor(ContextCompat.getColor(context, android.R.color.white));
        } else {
            image_view_thumbnail.setBackgroundColor(0);
        }

        image_view_thumbnail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                galleryViewListener.miniGalleryItemSelected(position);
            }
        });
    }

}
