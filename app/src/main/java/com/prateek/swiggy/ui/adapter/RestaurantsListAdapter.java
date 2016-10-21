package com.prateek.swiggy.ui.adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.prateek.swiggy.R;
import com.prateek.swiggy.rest.Response.Restaurant;
import com.prateek.swiggy.rest.Response.RestaurantsList;
import com.prateek.swiggy.ui.activities.DetailsActivity;

import java.util.List;

/**
 * Created by prateek.kesarwani on 25/09/16.
 */

public class RestaurantsListAdapter extends RecyclerView.Adapter<RestaurantsListAdapter.MyViewHolder> {

    private RestaurantsList mListRequest;

    public RestaurantsListAdapter() {

    }

    public RestaurantsListAdapter(RestaurantsList listRequest) {
        this.mListRequest = listRequest;
    }

    public void refreshWithData(RestaurantsList eventsList) {
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
        final Restaurant restaurant = mListRequest.get(position);

        holder.txtRestaurantTitle.setText(restaurant.getName());

        List<String> cuisineList = restaurant.getCuisine();
        String cuisineText = "";

        if (cuisineList != null && cuisineList.size() > 0) {
            cuisineText = cuisineList.get(0);
        }

        for (int count = 1; count < cuisineList.size(); count++) {
            cuisineText = cuisineText + ", " + cuisineList.get(count);
        }

        holder.txtRestaurantCuisine.setText(cuisineText);

        holder.txtRestaurantRating.setText("" + restaurant.getAvgRating());

        holder.txtOpeningTime.setText(restaurant.getNextOpenMessage());

        holder.txtOutletCount.setText(String.format(holder.txtOutletCount.getResources().getString(R.string.outlets_around), "" + restaurant.getChain().size()));

        holder.txtRestaurantPrice.setText(holder.txtRestaurantPrice.getResources().getString(R.string.price) + " " +
                restaurant.getCostForTwoString() + restaurant.getCostForTwo());

        holder.layoutMainView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), DetailsActivity.class);
                intent.putExtra(DetailsActivity.INTENT_RESTAURANT_DETAIL, restaurant);
                v.getContext().startActivity(intent);
            }
        });

        /*
        Picasso.with(holder.mainImg.getContext())
                .load(event.getImagePath())
                .placeholder(R.drawable.img_title)
                .into(holder.mainImg);
        */

    }

    @Override
    public int getItemCount() {
        if (mListRequest != null) {
            return mListRequest.size();
        }
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        RelativeLayout layoutMainView;
        TextView txtRestaurantTitle;
        TextView txtRestaurantCuisine;
        TextView txtRestaurantPrice;
        TextView txtRestaurantRating;
        TextView txtOpeningTime;
        TextView txtOutletCount;

        public MyViewHolder(View view) {
            super(view);
            layoutMainView = (RelativeLayout) view.findViewById(R.id.item_layout_main_view);
            txtRestaurantTitle = (TextView) view.findViewById(R.id.item_txt_restaurant_title);
            txtRestaurantCuisine = (TextView) view.findViewById(R.id.item_txt_restaurant_cuisine);
            txtRestaurantPrice = (TextView) view.findViewById(R.id.item_text_price);
            txtRestaurantRating = (TextView) view.findViewById(R.id.item_text_rating);
            txtOpeningTime = (TextView) view.findViewById(R.id.item_txt_restaurant_opening_info);
            txtOutletCount = (TextView) view.findViewById(R.id.item_txt_restaurant_outlet_info);
        }
    }
}