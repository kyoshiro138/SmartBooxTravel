package com.smartboox.travel.app.menu;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.smartboox.travel.R;
import com.smartboox.travel.app.login.LoginFragment;
import com.smartboox.travel.appimplementation.fragment.AppFragment;
import com.smartboox.travel.appimplementation.manager.UserManager;

import java.util.ArrayList;
import java.util.List;

public class MenuFragment extends AppFragment implements AdapterView.OnItemClickListener {
    private ListView mMenuList;

    private UserManager mManager;

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
        menuList.add("Sign out");

        MenuListAdapter menuListAdapter = new MenuListAdapter(mActivity, menuList);
        mMenuList.setAdapter(menuListAdapter);

        mManager = new UserManager(getActivity());
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
                mManager.signOut();
                getNavigator().navigateToFirstLevelFragment(new LoginFragment(), null);
                break;
            default:
                break;
        }
    }
}
