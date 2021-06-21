package com.microlearning.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.microlearning.adapter.ContentAdapter;
import com.microlearning.model.ContentDTO;
import com.sinavtime.microlearning.R;

import java.util.ArrayList;
import java.util.List;

public class CardDetailsFragment extends Fragment {

    ContentAdapter contentAdapter;
    List<ContentDTO> contentDTOList;
    Long selectedCardId;

    private LinearLayout dotsLayout;
    private TextView[] mDots;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_card_details, container, false);
        //selectedCardId = getIntent().getExtras().getLong("selectedCardId");
        ViewPager viewPager = v.findViewById(R.id.viewPager);
        contentDTOList = new ArrayList<>();
        loadCardDTO();
        contentAdapter = new ContentAdapter(contentDTOList, getActivity());
        viewPager.setAdapter(contentAdapter);
        viewPager.addOnPageChangeListener(viewListener);

        dotsLayout = v.findViewById(R.id.dotsLayout);
        addDotsIndicator(0);
        return v;
    }

    private void loadCardDTO() {
        if (contentDTOList != null) {
            ContentDTO content1 = new ContentDTO();
            content1.setText("Test 1");
            content1.setContentImage(R.drawable.image1);
            contentDTOList.add(content1);

            ContentDTO content2 = new ContentDTO();
            content2.setText("Test 2");
            content2.setContentImage(R.drawable.image2);
            contentDTOList.add(content2);

            ContentDTO content3 = new ContentDTO();
            content3.setText("Test 3");
            content3.setContentImage(R.drawable.image3);
            contentDTOList.add(content3);

            ContentDTO content4 = new ContentDTO();
            content4.setText("Test 4");
            content4.setContentImage(R.drawable.image4);
            contentDTOList.add(content4);
        }
    }
    private void addDotsIndicator(int position) {
        mDots = new TextView[contentDTOList.size()];
        dotsLayout.removeAllViews();
        for (int i = 0; i < mDots.length; i++) {
            mDots[i] = new TextView(getContext());
            mDots[i].setText(Html.fromHtml("&#8226"));
            mDots[i].setTextSize(33);
            mDots[i].setTextColor(getResources().getColor(R.color.dotDefault));
            dotsLayout.addView(mDots[i]);
        }

        if (mDots.length > 0) {
            mDots[position].setTextColor(getResources().getColor(R.color.dotSelected));
        }
    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int i, float v, int i1) {

        }

        @Override
        public void onPageSelected(int i) {
            addDotsIndicator(i);
        }

        @Override
        public void onPageScrollStateChanged(int i) {

        }
    };
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
