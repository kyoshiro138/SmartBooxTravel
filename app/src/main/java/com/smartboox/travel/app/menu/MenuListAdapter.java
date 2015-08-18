package com.smartboox.travel.app.menu;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.smartboox.travel.R;
import com.smartboox.travel.core.adapter.list.BaseListAdapter;

import java.util.List;

public class MenuListAdapter extends BaseListAdapter<String> {
    public MenuListAdapter(Context context, List<String> itemList) {
        super(context, itemList);
    }

    @Override
    protected int getItemLayoutResource() {
        return R.layout.list_menu_item;
    }

    @Override
    protected Object bindViewHolder(View view) {
        MenuItemViewHolder viewHolder = new MenuItemViewHolder();

        viewHolder.mText = (TextView) view.findViewById(R.id.tv_menu_text);

        return viewHolder;
    }

    @Override
    protected void loadData(Object viewHolder, String menuItem) {
        MenuItemViewHolder menuViewHolder = (MenuItemViewHolder) viewHolder;

        menuViewHolder.mText.setText(menuItem);
//        menuViewHolder.mImage.setImageResource(android.R.drawable.ic_menu_add);
    }

    private static class MenuItemViewHolder {
        public TextView mText;
//        public ImageView mImage;
    }
}
