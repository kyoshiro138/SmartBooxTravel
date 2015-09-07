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

    private int mMenuImageId;

    public int getMenuImageId() {
        return mMenuImageId;
    }

    public MenuItem(int id, String title) {
        this(id, title, 0);
    }

    public MenuItem(int id, String title, int imageId) {
        mMenuId = id;
        mMenuTitle = title;
        mMenuImageId = imageId;
    }
}
