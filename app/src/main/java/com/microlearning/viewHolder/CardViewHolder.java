package com.microlearning.viewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.microlearning.fragments.CardsFragment;
import com.microlearning.model.CardDTO;
import com.sinavtime.microlearning.R;

/**
 * Created by erhan.yener on 24.10.2018.
 */

public class CardViewHolder extends RecyclerView.ViewHolder {

    // CardView icersindeki objeler
    public TextView card_title;
    public TextView card_status;
    public ImageView card_mini_photo;
    public ProgressBar progressBar;

    public CardViewHolder(final View itemView) {
        super(itemView);
        this.card_mini_photo = (ImageView) itemView.findViewById(R.id.card_mini_photo);
        this.card_title = (TextView) itemView.findViewById(R.id.card_title);
        this.card_status = (TextView) itemView.findViewById(R.id.card_status);
        this.progressBar = itemView.findViewById(R.id.progressBar);
    }

    public void setData(final CardDTO cardDTO, final CardsFragment.CardViewListener listener) {
        this.card_mini_photo.setImageResource(cardDTO.getImageId());
        this.card_title.setText(cardDTO.getTitle());
        this.card_status.setText(cardDTO.getStatus());
        this.progressBar.setProgress(cardDTO.getScore());


        this.card_mini_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.showCardDetails(cardDTO.getCardId());
            }
        });
    }

}
