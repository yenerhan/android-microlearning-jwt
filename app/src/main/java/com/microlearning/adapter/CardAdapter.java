package com.microlearning.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.microlearning.interfaces.CardViewListener;
import com.microlearning.model.CardDTO;
import com.microlearning.viewHolder.CardViewHolder;
import com.sinavtime.microlearning.R;

import java.util.ArrayList;
import java.util.List;

public class CardAdapter extends RecyclerView.Adapter {

    LayoutInflater inflater;
    private List<CardDTO> cardDTOList = new ArrayList<>();
    CardViewListener cardViewListener;

    public CardAdapter(Context context, List<CardDTO> cardDTOList,CardViewListener cardViewListener) {
        inflater = LayoutInflater.from(context);
        this.cardViewListener=cardViewListener;
        if (cardDTOList != null) {
            this.cardDTOList = cardDTOList;
        }
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.story_card, viewGroup, false);
        return new CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        CardDTO cardDTO = cardDTOList.get(position);
        ((CardViewHolder) holder).setData(cardDTO, cardViewListener);
    }

    @Override
    public int getItemCount() {
        return cardDTOList.size();
    }
}
