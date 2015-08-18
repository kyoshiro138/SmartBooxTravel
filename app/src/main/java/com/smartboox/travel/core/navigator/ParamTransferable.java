package com.smartboox.travel.core.navigator;

import android.os.Parcelable;
import android.support.v4.app.Fragment;

/**
 * ParamTransferable is implemented in {@link Navigable} for param transfer
 */
public interface ParamTransferable {
    void transferParam(Fragment fragment, Parcelable param);
}
