package com.smartboox.travel.core.adapter.grid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.smartboox.travel.core.adapter.OnItemButtonClickListener;

import java.util.List;

public abstract class BaseGridAdapter<TGridData> extends BaseAdapter implements View.OnClickListener {
    protected Context mContext;
    protected List<TGridData> mItemList;
    protected OnItemButtonClickListener mButtonClickListener;

    public BaseGridAdapter(Context context, List<TGridData> itemList) {
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
    public int getCount() {
        return mItemList.size();
    }

    @Override
    public Object getItem(int position) {
        return mItemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public void onClick(View view) {
        if(mButtonClickListener!=null) {
            mButtonClickListener.onButtonClick(view);
        }
    }

    public void setOnItemButtonClickListener(OnItemButtonClickListener listener) {
        mButtonClickListener = listener;
    }

    public void reloadItemList(List<TGridData> itemList) {
        mItemList = itemList;
        notifyDataSetChanged();
    }

    protected abstract int getItemLayoutResource();

    protected abstract Object bindViewHolder(View view);

    protected abstract void loadData(Object viewHolder, TGridData data);
}
