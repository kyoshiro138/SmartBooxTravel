package com.smartboox.travel.app.home;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.smartboox.travel.R;
import com.smartboox.travel.appimplementation.domain.model.TravelPlace;
import com.smartboox.travel.core.adapter.grid.BaseGridAdapter;

import java.util.List;

public class PlaceGridAdapter extends BaseGridAdapter<TravelPlace> {
    public PlaceGridAdapter(Context context, List<TravelPlace> itemList) {
        super(context, itemList);
    }

    @Override
    protected int getItemLayoutResource() {
        return R.layout.grid_item_travel_place;
    }

    @Override
    protected Object bindViewHolder(View view) {
        PlaceItemViewHolder viewHolder = new PlaceItemViewHolder();

        viewHolder.mText = (TextView) view.findViewById(R.id.tv_place_name);
        viewHolder.mImage = (ImageView) view.findViewById(R.id.iv_place_image);

        return viewHolder;
    }

    @Override
    protected void loadData(Object viewHolder, TravelPlace travelPlace) {
        PlaceItemViewHolder placeViewHolder = (PlaceItemViewHolder) viewHolder;

        placeViewHolder.mText.setText(travelPlace.getName());
    }

    private static class PlaceItemViewHolder {
        public TextView mText;
        public ImageView mImage;
    }
}
