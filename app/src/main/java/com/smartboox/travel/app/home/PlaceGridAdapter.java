package com.smartboox.travel.app.home;

import android.content.Context;
import android.view.View;
import android.widget.ImageButton;
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

        viewHolder.mTextViewName = (TextView) view.findViewById(R.id.tv_place_name);
        viewHolder.mImageViewPicture = (ImageView) view.findViewById(R.id.iv_place_image);
        viewHolder.mButtonViewMap = (ImageButton) view.findViewById(R.id.btn_place_map);

        viewHolder.mButtonViewMap.setOnClickListener(this);

        return viewHolder;
    }

    @Override
    protected void loadData(Object viewHolder, TravelPlace travelPlace) {
        PlaceItemViewHolder placeViewHolder = (PlaceItemViewHolder) viewHolder;

        placeViewHolder.mTextViewName.setText(travelPlace.getName());
        placeViewHolder.mButtonViewMap.setTag(travelPlace);
    }

    private static class PlaceItemViewHolder {
        public TextView mTextViewName;
        public ImageView mImageViewPicture;
        public ImageButton mButtonViewMap;
    }
}
