package com.prateek.swiggy.ui.adapter;

import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.prateek.swiggy.R;
import com.prateek.swiggy.data.PreferenceManager;
import com.prateek.swiggy.rest.Event;
import com.prateek.swiggy.rest.Response.EventsList;
import com.prateek.swiggy.ui.activities.DetailsActivity;
import com.squareup.picasso.Picasso;

/**
 * Created by prateek.kesarwani on 25/09/16.
 */

public class EventsListAdapter extends RecyclerView.Adapter<EventsListAdapter.MyViewHolder> {

    private EventsList mListRequest;

    public EventsListAdapter() {

    }

    public EventsListAdapter(EventsList listRequest) {
        this.mListRequest = listRequest;
    }

    public void refreshWithData(EventsList eventsList) {
        this.mListRequest = eventsList;
        this.notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final Event event = mListRequest.get(position);
        holder.titleTxt.setText(event.getName());
        holder.categoryTxt.setText(event.getCategory());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.cardView.getContext(), DetailsActivity.class);
                intent.putExtra(DetailsActivity.INTENT_EVENT_DETAIL, event);
                holder.cardView.getContext().startActivity(intent);
            }
        });

        Picasso.with(holder.mainImg.getContext())
                .load(event.getImagePath())
                .placeholder(R.drawable.img_title)
                .into(holder.mainImg);

        if (PreferenceManager.isFavourite(event.getId())) {
            setFavouriteImage(holder.favouriteImg, true);
        }

        holder.favouriteImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (PreferenceManager.isFavourite(event.getId())) {
                    setFavouriteImage(holder.favouriteImg, false);
                    PreferenceManager.removeFavourites(event.getId());
                } else {
                    setFavouriteImage(holder.favouriteImg, true);
                    PreferenceManager.addFavourites(event.getId());
                }
            }
        });

    }

    private void setFavouriteImage(ImageView imgView, boolean isEnable) {
        if (isEnable) {
            imgView.setImageDrawable(imgView.getContext().getResources().getDrawable(R.drawable.ic_favourite_enable));
        } else {
            imgView.setImageDrawable(imgView.getContext().getResources().getDrawable(R.drawable.ic_favourite_disable));
        }

    }

    @Override
    public int getItemCount() {
        return mListRequest.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public CardView cardView;
        public TextView categoryTxt, titleTxt;
        public ImageView mainImg;
        public ImageView favouriteImg;

        public MyViewHolder(View view) {
            super(view);
            cardView = (CardView) view;
            categoryTxt = (TextView) view.findViewById(R.id.txt_list_item_category);
            titleTxt = (TextView) view.findViewById(R.id.txt_list_item_title);
            mainImg = (ImageView) view.findViewById(R.id.img_list_item_main);
            favouriteImg = (ImageView) view.findViewById(R.id.img_list_item_favourite);
        }
    }
}