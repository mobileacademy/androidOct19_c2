package com.e.navdrawerapp;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.e.navdrawerapp.network.Beer;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class BeersAdapter extends BaseAdapter {

    private List<Beer> beers = new ArrayList<>();
    private static final String TAG = "BeersAdapter";

    public BeersAdapter(List<Beer> dataSource) {
        beers.addAll(dataSource);
    }

    public void updateList(List<Beer> newList) {
        beers.clear();
        beers.addAll(newList);
        notifyDataSetChanged(); // trigger UI update
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.d(TAG, "getView for position=" + position);

        // get current beer
        Beer currentBeer = beers.get(position);
        ViewHolder viewHolder;
        if (convertView == null) {
            Log.d(TAG, "view is null at position=" + position + " inflate layout for row");
            // inflate each list row layout
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.beer_list_item_layout, parent, false);
            viewHolder = new ViewHolder();

            viewHolder.beerImageView = convertView.findViewById(R.id.beerPictureImageView);
            viewHolder.beerNameTextView = convertView.findViewById(R.id.nameTextView);
            viewHolder.beerDescTextView = convertView.findViewById(R.id.descTextView);

            convertView.setTag(viewHolder);
        } else {
            Log.d(TAG, "view is NOT null at position=" + position + " re-use views");
            // re - use the viewHolder
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.beerNameTextView.setText(currentBeer.getName());
        viewHolder.beerDescTextView.setText(currentBeer.getDescription());

        if (currentBeer.getImageUrl() != null) {
            Picasso.get().load(currentBeer.getImageUrl()).into(viewHolder.beerImageView);
        }
        return convertView;
    }

    @Override
    public int getCount() {
        return beers.size();
    }

    @Override
    public Beer getItem(int position) {
        return beers.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    class ViewHolder {
        ImageView beerImageView;
        TextView beerNameTextView;
        TextView beerDescTextView;
    }
}
