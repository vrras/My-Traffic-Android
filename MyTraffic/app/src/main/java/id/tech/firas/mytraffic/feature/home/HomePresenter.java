package id.tech.firas.mytraffic.feature.home;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import java.text.DateFormat;
import java.util.Date;

import id.tech.firas.mytraffic.base.ui.BasePresenter;
import id.tech.firas.mytraffic.feature.login.LoginActivity;
import id.tech.firas.mytraffic.feature.login.LoginView;
import id.tech.firas.mytraffic.models.DataItem;
import id.tech.firas.mytraffic.models.ResponseLogin;
import id.tech.firas.mytraffic.network.NetworkCallback;
import id.tech.firas.mytraffic.utils.MyConstant;
import id.tech.firas.mytraffic.utils.Pref;

/**
 * Created by Firas Luthfi on 2/17/2018.
 */

public class HomePresenter extends BasePresenter<HomeView> {

    public HomePresenter(HomeView view) {
        super.attachView(view);
    }

    public void getLatLng(String lat, String lng, Pref pref) {
        view.showLoading();

        String id_user = pref.userdata(MyConstant.KEY_SESSION_USERID);
        addSubscribe(apiStores.getLastLocation(lat, lng, Integer.parseInt(id_user)), new NetworkCallback<ResponseLogin>() {
            @Override
            public void onSuccess(ResponseLogin model) {
                if (model.isStatus()) {
                    view.getDataSuccess(model);
                } else {
                    view.getResponFail(model);
                }
            }

            @Override
            public void onFailure(String message) {
                view.getDataFail(message);
            }

            @Override
            public void onFinish() {
                view.hideLoading();
            }
        });
    }

    public void doLogout(final Pref pref, final Activity activity) {
        String id_user = pref.userdata(MyConstant.KEY_SESSION_USERID);

        view.showLoading();
        addSubscribe(apiStores.getLogout(Integer.parseInt(id_user)), new NetworkCallback<ResponseLogin>() {
            @Override
            public void onSuccess(ResponseLogin model) {
                if (model.isStatus()) {
                    pref.setLogin(false);
                    pref.set_userdata(MyConstant.KEY_SESSION_USERID, "");

                    getItem(activity);
                    view.getDataSuccess(model);
                } else {
                    view.getResponFail(model);
                }
            }

            @Override
            public void onFailure(String message) {
                view.getDataFail(message);
            }

            @Override
            public void onFinish() {
                view.hideLoading();
            }
        });
    }

//    public void onStartGoogleAPI(GoogleApiClient mGoogleApiClient, Activity activity) {
//        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION);
//
//        if (permission == PackageManager.PERMISSION_GRANTED) {
//            // We don't have permission so prompt the user
//            if (mGoogleApiClient != null) {
//                mGoogleApiClient.connect();
//            }
//        } else {
//
//        }
//    }
//
//    public void onStopGoogleAPI(GoogleApiClient mGoogleApiClient) {
//        if (mGoogleApiClient.isConnected()) {
//            mGoogleApiClient.disconnect();
//        }
//    }
//
//    public void setupGoogleAPI(GoogleApiClient mGoogleApiClient,
//                               GoogleApiClient.ConnectionCallbacks connectionCallbacks,
//                               GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener,
//                               Context context) {
//
//        mGoogleApiClient = new GoogleApiClient.Builder(context)
//                .addConnectionCallbacks(connectionCallbacks)
//                .addOnConnectionFailedListener(onConnectionFailedListener)
//                .addApi(LocationServices.API)
//                .build();
//    }
//
//    public void onConnectedGoogleAPI(LocationRequest mLocationRequest, GoogleApiClient mGoogleApiClient, LocationListener locationListener) {
//        mLocationRequest = LocationRequest.create();
//        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
//        mLocationRequest.setInterval(5000 * 2); // set interval insert lokasi
//        mLocationRequest.setFastestInterval(3000);
//        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, locationListener);
//    }
//
//    public void onSuspendedGoogleAPI(GoogleApiClient mGoogleApiClient){
//        mGoogleApiClient.connect();
//    }
//
//    public void onLocationChangedGoogleAPI(String mLastUpdateTime, TextView tvLat,
//                                           TextView tvLng, Location location, Context context){
//        mLastUpdateTime = DateFormat.getTimeInstance().format(new Date());
//        tvLat.setText(String.valueOf(location.getLatitude()));
//        tvLng.setText(String.valueOf(location.getLongitude()));
//        Toast.makeText(context, "Updated: " + mLastUpdateTime, Toast.LENGTH_SHORT).show();
//    }
//
//    public void verifyPermission(Activity activity, String[] PERMISSIONS_LOCATION, int REQUEST_ACCESS_FINE_LOCATION){
//        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION);
//
//        if (permission != PackageManager.PERMISSION_GRANTED) {
//            // We don't have permission so prompt the user
//            ActivityCompat.requestPermissions(
//                    activity,
//                    PERMISSIONS_LOCATION,
//                    REQUEST_ACCESS_FINE_LOCATION
//            );
//        }
//    }

    private void getItem(Activity activity) {
        Intent intent = new Intent(activity, LoginActivity.class);
        view.moveToDetail(intent);
    }

}
