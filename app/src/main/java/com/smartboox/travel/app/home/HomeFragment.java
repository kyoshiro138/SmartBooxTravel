package com.smartboox.travel.app.home;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.AdapterView;

import com.android.volley.VolleyError;
import com.smartboox.travel.R;
import com.smartboox.travel.app.locationlist.LocationListFragment;
import com.smartboox.travel.app.placemap.PlaceMapFragment;
import com.smartboox.travel.appimplementation.dialog.AppDialogBuilder;
import com.smartboox.travel.appimplementation.domain.model.TravelPlace;
import com.smartboox.travel.appimplementation.fragment.AppFragment;
import com.smartboox.travel.appimplementation.manager.TravelManager;
import com.smartboox.travel.appimplementation.manager.UserManager;
import com.smartboox.travel.appimplementation.service.AppResponseObject;
import com.smartboox.travel.appimplementation.service.response.GetTravelDataResponseObject;
import com.smartboox.travel.core.adapter.OnItemButtonClickListener;
import com.smartboox.travel.core.database.ActiveAndroidDatabaseHelper;
import com.smartboox.travel.core.dialog.Dialog;
import com.smartboox.travel.core.dialog.OnDialogButtonClickListener;
import com.smartboox.travel.core.service.client.OnServiceResponseListener;
import com.smartboox.travel.core.view.gridview.AnimationGridView;
import com.smartboox.travel.core.view.gridview.OnGridHiddenListener;
import com.smartboox.travel.utils.DummyCreator;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends AppFragment implements AdapterView.OnItemClickListener, OnItemButtonClickListener, OnServiceResponseListener<AppResponseObject>,OnDialogButtonClickListener {

    private AnimationGridView mPlaceGrid;
    private PlaceGridAdapter mPlaceGridAdapter;

    private UserManager mUserManager;
    private TravelManager mTravelManager;
    private AppDialogBuilder mDialogBuilder;

    @Override
    protected int getFragmentLayoutResource() {
        return R.layout.fragment_home;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity.setDrawerEnabled(true);
        mActivity.getToolbar().setToolbarVisibility(View.VISIBLE);
    }

    @Override
    protected void bindView(View rootView) {
        mPlaceGrid = (AnimationGridView) rootView.findViewById(R.id.gv_home_place_grid);
        mPlaceGrid.setOnItemClickListener(this);
    }

    @Override
    protected void loadData() {
        mUserManager = new UserManager(getActivity());
        mTravelManager = new TravelManager(getActivity());
        mDialogBuilder = new AppDialogBuilder(getActivity());

        mActivity.reloadMenu(mUserManager.getLocalUser());
        mActivity.getToolbar().reloadToolbar("Where are you travelling?");

        mTravelManager.requestTravelData(0, this);

        mPlaceGridAdapter = new PlaceGridAdapter(getActivity(), new ArrayList<TravelPlace>());
        mPlaceGridAdapter.setOnItemButtonClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
        mPlaceGrid.hideGrid(R.anim.anim_scale_disappear, new OnGridHiddenListener() {
            @Override
            public void onHidden() {
                getNavigator().navigateTo(new LocationListFragment(), (Parcelable) mPlaceGrid.getItemAtPosition(position));
            }
        });
    }

    @Override
    public void onButtonClick(View view) {
        switch (view.getId()) {
            case R.id.btn_place_map:
                TravelPlace place = (TravelPlace) view.getTag();
                getNavigator().navigateTo(new PlaceMapFragment(), place);
                break;
            default:
                break;
        }
    }

    @Override
    public void onResponseSuccess(String tag, AppResponseObject response) {
        switch (tag) {
            case TravelManager.SERVICE_GET_TRAVEL_DATA:
                getTravelDataResponse((GetTravelDataResponseObject) response);
                break;
            default:
                break;
        }
    }

    @Override
    public void onResponseFailed(String tag, VolleyError error) {
        getLocalTravelData();
    }

    @Override
    public void onParseError(String tag, String response) {

    }

    private void getTravelDataResponse(GetTravelDataResponseObject response) {
        boolean isSuccess = response.getStatus();
        if(isSuccess) {
            mTravelManager.saveTravelPlaceList(response.getResponseData().getPlaceList());
            mPlaceGridAdapter.reloadItemList(response.getResponseData().getPlaceList());
            mPlaceGrid.showGrid(mPlaceGridAdapter, R.anim.anim_scale_appear);
        } else {
            getLocalTravelData();
        }
    }

    private void getLocalTravelData() {
        List<TravelPlace> localPlaceList = mTravelManager.getLocalTravelPlaceList();
        if(localPlaceList!=null && !localPlaceList.isEmpty()) {
            mPlaceGridAdapter.reloadItemList(localPlaceList);
            mPlaceGrid.showGrid(mPlaceGridAdapter, R.anim.anim_scale_appear);
        } else {
            Dialog dialog = mDialogBuilder.buildWarningDialog(AppDialogBuilder.DIALOG_NETWORK_RETRY, "Network error. Please try again.", this);
            dialog.show();
        }
    }

    @Override
    public void onClick(Dialog dialog, int which) {
        switch (dialog.getTag()) {
            case AppDialogBuilder.DIALOG_NETWORK_RETRY:
                if(which == DialogInterface.BUTTON_POSITIVE) {
                    mTravelManager.requestTravelData(0, this);
                }
                break;
            default:
                break;
        }
    }
}
