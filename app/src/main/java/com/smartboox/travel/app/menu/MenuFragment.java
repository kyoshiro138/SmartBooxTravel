package com.smartboox.travel.app.menu;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.smartboox.travel.R;
import com.smartboox.travel.app.login.LoginFragment;
import com.smartboox.travel.appimplementation.domain.model.User;
import com.smartboox.travel.appimplementation.fragment.AppFragment;
import com.smartboox.travel.appimplementation.manager.UserManager;
import com.smartboox.travel.core.view.label.MaterialLabel;

import java.util.ArrayList;
import java.util.List;

public class MenuFragment extends AppFragment implements AdapterView.OnItemClickListener {
    private static final int MENU_SIGN_OUT = 0;
    private static final int MENU_BECOME_MEMBER = 1;

    private ListView mMenuList;
    private MenuListAdapter mMenuListAdapter;
    private UserManager mManager;
    private MaterialLabel mTvUsername;

    @Override
    protected int getFragmentLayoutResource() {
        return R.layout.fragment_menu;
    }

    @Override
    protected void bindView(View rootView) {
        mTvUsername = (MaterialLabel) rootView.findViewById(R.id.tv_menu_username);

        mMenuList = (ListView) mRootView.findViewById(R.id.list_menu);
        mMenuList.setOnItemClickListener(this);
    }

    @Override
    protected void loadData() {
        mManager = new UserManager(getActivity());
        loadMenu(mManager.getLocalUser());
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        int menuId = mMenuListAdapter.getItem(position).getMenuId();
        switch (menuId) {
            case MENU_SIGN_OUT:
                mManager.signOut();
                getNavigator().navigateToFirstLevelFragment(new LoginFragment(), null);
                break;
            default:
                break;
        }
    }

    public void loadMenu(User user) {
        if (user != null) {
            mTvUsername.setText(user.getUsername());

            List<MenuItem> menuList = new ArrayList<>();
            if (user.getUserId() > 0) {
                menuList.add(new MenuItem(MENU_SIGN_OUT, "Sign out"));
            } else {
                menuList.add(new MenuItem(MENU_BECOME_MEMBER, "Become member"));
                menuList.add(new MenuItem(MENU_SIGN_OUT, "Sign out"));
            }

            mMenuListAdapter = new MenuListAdapter(mActivity, menuList);
            mMenuList.setAdapter(mMenuListAdapter);
        }
    }
}
