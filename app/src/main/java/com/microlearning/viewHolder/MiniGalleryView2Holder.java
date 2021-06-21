package com.microlearning.viewHolder;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.microlearning.activities.ScreenUtils;
import com.microlearning.interfaces.GalleryViewListener;
import com.microlearning.model.GalleryItem;
import com.sinavtime.microlearning.R;

/**
 * Created by erhan.yener on 24.10.2018.
 */

public class MiniGalleryView2Holder extends RecyclerView.ViewHolder {

    // Mini Gallery icerisindeki objeler
    public Button image_upload_button;


    public MiniGalleryView2Holder(final View itemView) {
        super(itemView);
        this.image_upload_button = (Button) itemView.findViewById(R.id.upload_button);
    }


    public void setData(final GalleryViewListener galleryViewListener, final int position, Context context) {
        image_upload_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                galleryViewListener.miniGalleryImageUpload();
            }
        });
    }

}
