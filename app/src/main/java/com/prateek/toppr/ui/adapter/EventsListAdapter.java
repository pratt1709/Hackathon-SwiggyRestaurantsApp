package com.prateek.toppr.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.prateek.toppr.R;
import com.prateek.toppr.rest.request.Event;
import com.prateek.toppr.rest.request.EventsListRequest;
import com.squareup.picasso.Picasso;

/**
 * Created by prateek.kesarwani on 25/09/16.
 */

public class EventsListAdapter extends RecyclerView.Adapter<EventsListAdapter.MyViewHolder> {

    private EventsListRequest mListRequest;

    public EventsListAdapter() {

    }

    public EventsListAdapter(EventsListRequest listRequest) {
        this.mListRequest = listRequest;
    }

    public void refreshWithData(EventsListRequest eventsListRequest) {
        this.mListRequest = eventsListRequest;
        this.notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Event event = mListRequest.get(position);
        holder.titleTxt.setText(event.getName());
        holder.categoryTxt.setText(event.getCategory());

        Picasso.with(holder.mainImg.getContext())
                .load(event.getImagePath())
                .placeholder(R.drawable.img_title)
                .into(holder.mainImg);
    }

    @Override
    public int getItemCount() {
        return mListRequest.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView categoryTxt, titleTxt;
        public ImageView mainImg;

        public MyViewHolder(View view) {
            super(view);
            categoryTxt = (TextView) view.findViewById(R.id.txt_list_item_category);
            titleTxt = (TextView) view.findViewById(R.id.txt_list_item_title);
            mainImg = (ImageView) view.findViewById(R.id.img_list_item_main);
        }
    }
}