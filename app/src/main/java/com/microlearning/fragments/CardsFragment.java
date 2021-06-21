package com.microlearning.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.microlearning.adapter.CardAdapter;
import com.microlearning.model.CardDTO;
import com.sinavtime.microlearning.R;

import java.util.ArrayList;
import java.util.List;

public class CardsFragment extends Fragment {

    private RecyclerView recyclerView;
    private CardAdapter adapter;
    private List<CardDTO> cardDTOList;
    private CardViewListener listener;

    FloatingActionButton fab, fab1;
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
        View v = inflater.inflate(R.layout.fragment_cards, container, false);

        recyclerView = (RecyclerView) v.findViewById(R.id.recycler_view);
        cardDTOList = new ArrayList<>();
        loadCardDTO();
        adapter = new CardAdapter(getActivity(), cardDTOList, listener);
        setupFloatingButton(v);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adapter);
        return v;
    }



    private void loadCardDTO() {
        if (cardDTOList != null) {
            CardDTO card1 = new CardDTO();
            card1.setCardId(1L);
            card1.setTitle("Test 1");
            card1.setStatus("%75");
            card1.setImageId(R.drawable.image1);
            card1.setScore(80);
            cardDTOList.add(card1);

            CardDTO card2 = new CardDTO();
            card2.setCardId(2L);
            card2.setTitle("Test 2");
            card2.setStatus("%75");
            card2.setImageId(R.drawable.image2);
            card2.setScore(75);
            cardDTOList.add(card2);

            CardDTO card3 = new CardDTO();
            card3.setCardId(3L);
            card3.setTitle("Test 3");
            card3.setStatus("%100");
            card3.setImageId(R.drawable.image3);
            card3.setScore(100);
            cardDTOList.add(card3);

            CardDTO card4 = new CardDTO();
            card4.setCardId(4L);
            card4.setTitle("Test 4");
            card4.setStatus("%100");
            card4.setImageId(R.drawable.image4);
            card4.setScore(85);
            cardDTOList.add(card4);

            CardDTO card5 = new CardDTO();
            card5.setCardId(5L);
            card5.setTitle("Test 5");
            card5.setStatus("%100");
            card5.setImageId(R.drawable.image4);
            card5.setScore(85);
            cardDTOList.add(card5);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof CardViewListener) {
            listener = (CardViewListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement CardViewListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener=null;
    }

    public interface CardViewListener {
        void showCardDetails(Long cardId);
        void createNewGallery();
    }

    private void setupFloatingButton(View v) {
        if (admin) {
            fab = v.findViewById(R.id.fab);
            fab1 = v.findViewById(R.id.fab1);
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
                        isOpen = false;
                    } else {
                        fab.startAnimation(rotate_clock);
                        fab1.startAnimation(fab_open);
                        fab1.setClickable(true);
                        isOpen = true;
                    }
                }
            });
            fab1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.createNewGallery();
                }
            });
        }
    }
}
