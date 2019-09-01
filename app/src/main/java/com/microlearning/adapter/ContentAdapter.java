package com.microlearning.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.microlearning.model.CardDTO;
import com.microlearning.model.ContentDTO;
import com.sinavtime.microlearning.R;

import java.util.List;

public class ContentAdapter extends PagerAdapter {

    private List<ContentDTO> contentDTOList;
    private Context context;
    private LayoutInflater inflater;

    public ContentAdapter(List<ContentDTO> contentDTOList, Context context) {
        this.contentDTOList = contentDTOList;
        this.context = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return contentDTOList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view==o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = inflater.inflate(R.layout.content, container, false);
        ImageView imageView = view.findViewById(R.id.image);
        ContentDTO contentDTO = contentDTOList.get(position);
        imageView.setImageResource(contentDTO.getContentImage());
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((FrameLayout)object);
    }
}
