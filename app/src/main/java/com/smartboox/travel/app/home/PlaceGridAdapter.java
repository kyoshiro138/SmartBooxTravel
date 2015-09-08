package com.smartboox.travel.app.home;

import android.content.Context;
import android.view.View;

import com.smartboox.travel.R;
import com.smartboox.travel.core.adapter.grid.BaseGridAdapter;

import java.util.List;

public class PlaceGridAdapter extends BaseGridAdapter<String> {
    public PlaceGridAdapter(Context context, List<String> itemList) {
        super(context, itemList);
    }

    @Override
    protected int getItemLayoutResource() {
        return R.layout.grid_item_travel_place;
    }

    @Override
    protected Object bindViewHolder(View view) {
        return null;
    }

    @Override
    protected void loadData(Object viewHolder, String s) {

    }
}
