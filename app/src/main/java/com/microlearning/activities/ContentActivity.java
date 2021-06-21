package com.microlearning.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;

import com.microlearning.adapter.GalleryAdapter;
import com.microlearning.adapter.MiniGalleryAdapter;
import com.microlearning.interfaces.GalleryViewListener;
import com.microlearning.model.GalleryItem;
import com.sinavtime.microlearning.R;

import java.util.List;

public class ContentActivity extends AppCompatActivity implements GalleryViewListener {
    private static final String ARG_CURRENT_POSITION = "position";
    private static final int PICK_IMAGE_REQUEST = 1;

    public List<GalleryItem> galleryItems;

    //Ana galery
    ViewPager viewPager;
    GalleryAdapter galleryAdapter;

    /*Şerit galeri*/
    RecyclerView recyclerViewGallery;
    MiniGalleryAdapter miniGalleryAdapter;
    RecyclerView.LayoutManager mGalleryStripLayoutManger;

    private int mCurrentPosition = 0;
    boolean isBottomBarVisible = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        galleryItems = (List<GalleryItem>) getIntent().getSerializableExtra("galleryItems");
        galleryItems.add(new GalleryItem(null, null, true));
        viewPager = findViewById(R.id.viewPager);
        viewPager.addOnPageChangeListener(viewListener);

        /*on touch*/
        viewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    if (isBottomBarVisible) {
                        //bottom bar is visible make it invisible
                        FadeOutBottomBar();
                    } else {
                        //bottom bar is invisible make it visible
                        FadeInBottomBar();
                    }
                }
                return false;
            }

        });
        recyclerViewGallery = findViewById(R.id.recyclerViewGallery);
        mGalleryStripLayoutManger = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewGallery.setLayoutManager(mGalleryStripLayoutManger);
        loadGallery();
    }

    public void loadGallery(){
        galleryAdapter = new GalleryAdapter(galleryItems, this);
        viewPager.setAdapter(galleryAdapter);
        viewPager.setCurrentItem(mCurrentPosition);

        miniGalleryAdapter = new MiniGalleryAdapter(this, galleryItems, this, mCurrentPosition);
        recyclerViewGallery.setAdapter(miniGalleryAdapter);
    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int i, float v, int i1) {

        }

        @Override
        public void onPageSelected(int i) {
        }

        @Override
        public void onPageScrollStateChanged(int state) {
            if (state == ViewPager.SCROLL_STATE_IDLE) {
                int currentSelected = viewPager.getCurrentItem();
                mGalleryStripLayoutManger.smoothScrollToPosition(recyclerViewGallery, null, currentSelected);
                miniGalleryAdapter.setSelected(currentSelected);

            }
        }
    };

    //Method to fadeIn bottom bar which is image textview name
    public void FadeInBottomBar() {
        //define alpha animation
        AlphaAnimation fadeIn = new AlphaAnimation(0.0f, 1.0f);
        //set duration
        fadeIn.setDuration(800);
        //set animation listener
        fadeIn.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                //set textview visible on animation ends
                //textViewImageName.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        isBottomBarVisible = true;
    }

    public void FadeOutBottomBar() {
        //define alpha animation
        AlphaAnimation fadeOut = new AlphaAnimation(1.0f, 0.0f);
        //set duration
        fadeOut.setDuration(800);
        //set animation listener
        fadeOut.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        isBottomBarVisible = false;
    }

    @Override
    public void miniGalleryItemSelected(int position) {
        viewPager.setCurrentItem(position);
    }

    @Override
    public void miniGalleryImageUpload() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            galleryItems.add(new GalleryItem(data.getData().toString(), "test",false));
            loadGallery();
        }
    }


}
