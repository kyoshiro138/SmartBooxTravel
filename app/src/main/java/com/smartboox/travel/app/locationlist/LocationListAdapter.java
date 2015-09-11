package com.smartboox.travel.app.locationlist;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.smartboox.travel.R;
import com.smartboox.travel.appimplementation.domain.model.TravelLocation;
import com.smartboox.travel.core.adapter.list.BaseListAdapter;

import java.util.List;

public class LocationListAdapter extends BaseListAdapter<TravelLocation> {
    public LocationListAdapter(Context context, List<TravelLocation> itemList) {
        super(context, itemList);
    }

    @Override
    protected int getItemLayoutResource() {
        return R.layout.list_item_location;
    }

    @Override
    protected Object bindViewHolder(View view) {
        LocationItemViewHolder viewHolder = new LocationItemViewHolder();

        viewHolder.mText = (TextView) view.findViewById(R.id.tv_location_item_name);

        return viewHolder;
    }

    @Override
    protected void loadData(Object viewHolder, TravelLocation travelLocation) {
        LocationItemViewHolder locationViewHolder = (LocationItemViewHolder) viewHolder;

        locationViewHolder.mText.setText(travelLocation.getName());
    }

    private static class LocationItemViewHolder {
        public TextView mText;
    }
}
