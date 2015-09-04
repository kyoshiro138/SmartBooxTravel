package com.smartboox.travel.app.menu;

public class MenuItem {
    private int mMenuId;

    public int getMenuId() {
        return mMenuId;
    }

    private String mMenuTitle;

    public String getMenuTitle() {
        return mMenuTitle;
    }

    public MenuItem(int id, String title) {
        mMenuId = id;
        mMenuTitle = title;
    }
}
