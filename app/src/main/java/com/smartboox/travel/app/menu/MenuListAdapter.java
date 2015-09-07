package com.smartboox.travel.app.menu;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.smartboox.travel.R;
import com.smartboox.travel.core.adapter.list.BaseListAdapter;

import java.util.List;

public class MenuListAdapter extends BaseListAdapter<MenuItem> {
    public MenuListAdapter(Context context, List<MenuItem> itemList) {
        super(context, itemList);
    }

    @Override
    protected int getItemLayoutResource() {
        return R.layout.list_single_line;
    }

    @Override
    protected Object bindViewHolder(View view) {
        MenuItemViewHolder viewHolder = new MenuItemViewHolder();

        viewHolder.mText = (TextView) view.findViewById(R.id.tv_menu_item_title);
        viewHolder.mImage = (ImageView) view.findViewById(R.id.iv_menu_item_image);

        return viewHolder;
    }

    @Override
    protected void loadData(Object viewHolder, MenuItem menuItem) {
        MenuItemViewHolder menuViewHolder = (MenuItemViewHolder) viewHolder;

        menuViewHolder.mText.setText(menuItem.getMenuTitle());
        if (menuItem.getMenuImageId() > 0) {
            menuViewHolder.mImage.setImageResource(menuItem.getMenuImageId());
        } else {
            menuViewHolder.mImage.setVisibility(View.GONE);
        }
    }

    private static class MenuItemViewHolder {
        public TextView mText;
        public ImageView mImage;
    }
}
