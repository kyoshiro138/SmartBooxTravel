package com.smartboox.travel.core.navigator;

/**
 * NavigationFragment is implemented in Fragment for navigation
 * @param <TActivity>
 *     Activity or FragmentActivity which can navigable
 */
public  interface NavigationFragment<TActivity extends NavigationActivity> {
    TActivity getNavigationActivity();
}
