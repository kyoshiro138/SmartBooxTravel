package com.smartboox.travel.core.adapter.list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.smartboox.travel.core.adapter.OnItemButtonClickListener;

import java.util.List;

public abstract class BaseListAdapter<TListData> extends ArrayAdapter<TListData> implements View.OnClickListener {
    protected Context mContext;
    protected List<TListData> mItemList;
    protected OnItemButtonClickListener mButtonClickListener;

    public List<TListData> getItemList() {
        return mItemList;
    }

    public BaseListAdapter(Context context, List<TListData> itemList) {
        super(context, -1, itemList);
        mContext = context;
        mItemList = itemList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        Object viewHolder;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(getItemLayoutResource(), parent, false);

            viewHolder = bindViewHolder(view);

            view.setTag(viewHolder);
            if (view instanceof ViewGroup) {
                ((ViewGroup) view).setDescendantFocusability(ViewGroup.FOCUS_BLOCK_DESCENDANTS);
            }
        } else {
            view = convertView;

            viewHolder = view.getTag();
        }

        loadData(viewHolder, mItemList.get(position));

        return view;
    }

    @Override
    public void onClick(View view) {
        
    }

    public void setOnItemButtonClickListener(OnItemButtonClickListener listener) {
        mButtonClickListener = listener;
    }

    public void reloadItemList(List<TListData> itemList) {
        mItemList = itemList;
        notifyDataSetChanged();
    }

    protected abstract int getItemLayoutResource();

    protected abstract Object bindViewHolder(View view);

    protected abstract void loadData(Object viewHolder, TListData data);
}
