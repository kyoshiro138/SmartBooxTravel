package com.smartboox.travel.core.navigator;

import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

/**
 * Interface for fragment navigation implementation
 */
public interface Navigable {
    void navigateTo(Fragment fragment, @Nullable Parcelable param);

    void navigateBackToFirstLevelFragment();

    void navigateToFirstLevelFragment(Fragment fragment, @Nullable Parcelable param);

    void navigateBack();

    void navigateBack(int count);
}
