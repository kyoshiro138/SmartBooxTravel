package com.smartboox.travel.app.locationlist;

import android.content.Context;
import android.view.View;

import com.smartboox.travel.appimplementation.domain.model.TravelLocation;
import com.smartboox.travel.core.adapter.list.BaseListAdapter;

import java.util.List;

public class LocationListAdapter extends BaseListAdapter<TravelLocation> {
    public LocationListAdapter(Context context, List<TravelLocation> itemList) {
        super(context, itemList);
    }

    @Override
    protected int getItemLayoutResource() {
        return 0;
    }

    @Override
    protected Object bindViewHolder(View view) {
        return null;
    }

    @Override
    protected void loadData(Object viewHolder, TravelLocation travelLocation) {

    }
}
