package com.microlearning.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.sinavtime.microlearning.R;

public class GalleryFragment extends Fragment {

    FloatingActionButton fab, fab1, fab2, fab3, fab4;
    Animation fab_open, fab_close, rotate_clock, rotate_anticlock;
    boolean isOpen = false;
    boolean admin = true;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_gallery, container, false);
        setupFloatingButton(v);
        return v;
    }

    private void setupFloatingButton(View v) {
        if (admin) {
            fab = v.findViewById(R.id.fab);
            fab1 = v.findViewById(R.id.fab1);
            fab2 = v.findViewById(R.id.fab2);
            fab3 = v.findViewById(R.id.fab3);
            fab4 = v.findViewById(R.id.fab4);
            fab_open = AnimationUtils.loadAnimation(getContext(), R.anim.fab_open);
            fab_close = AnimationUtils.loadAnimation(getContext(), R.anim.fab_close);
            rotate_clock = AnimationUtils.loadAnimation(getContext(), R.anim.rotate_clock);
            rotate_anticlock = AnimationUtils.loadAnimation(getContext(), R.anim.rotate_anticlock);
            fab.startAnimation(fab_open);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (isOpen) {
                        fab.startAnimation(rotate_anticlock);
                        fab1.startAnimation(fab_close);
                        fab1.setClickable(false);
                        fab2.startAnimation(fab_close);
                        fab2.setClickable(false);
                        fab3.startAnimation(fab_close);
                        fab3.setClickable(false);
                        fab4.startAnimation(fab_close);
                        fab4.setClickable(false);
                        isOpen = false;
                    } else {
                        fab.startAnimation(rotate_clock);
                        fab1.startAnimation(fab_open);
                        fab1.setClickable(true);
                        fab2.startAnimation(fab_open);
                        fab2.setClickable(true);
                        fab3.startAnimation(fab_open);
                        fab3.setClickable(true);
                        fab4.startAnimation(fab_open);
                        fab4.setClickable(true);
                        isOpen = true;
                    }
                }
            });
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
