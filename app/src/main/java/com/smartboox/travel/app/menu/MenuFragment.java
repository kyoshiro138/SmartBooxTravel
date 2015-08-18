package com.smartboox.travel.app.menu;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.smartboox.travel.R;
import com.smartboox.travel.appimplementation.fragment.AppFragment;

import java.util.ArrayList;
import java.util.List;

public class MenuFragment extends AppFragment implements AdapterView.OnItemClickListener {
    private ListView mMenuList;

    @Override
    protected int getFragmentLayoutResource() {
        return R.layout.fragment_menu;
    }

    @Override
    protected void bindView(View rootView) {
        mMenuList = (ListView) mRootView.findViewById(R.id.list_menu);
        mMenuList.setOnItemClickListener(this);
    }

    @Override
    protected void loadData() {
        List<String> menuList = new ArrayList<>();
        menuList.add("");

        MenuListAdapter menuListAdapter = new MenuListAdapter(mActivity, menuList);
        mMenuList.setAdapter(menuListAdapter);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
                break;
            default:
                break;
        }
    }
}
