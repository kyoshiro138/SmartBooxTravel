package com.smartboox.travel.core.navigator;

/**
 * NavigationActivity is implemented in Activity or FragmentActivity for navigation
 * @param <TNavigator>
 *     Navigable interface
 */
public interface NavigationActivity<TNavigator extends Navigable> {
    TNavigator getNavigator();
}
