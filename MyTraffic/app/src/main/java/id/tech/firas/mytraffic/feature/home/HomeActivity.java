package id.tech.firas.mytraffic.feature.home;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import java.text.DateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.tech.firas.mytraffic.R;
import id.tech.firas.mytraffic.base.mvp.MvpActivity;
import id.tech.firas.mytraffic.models.ResponseLogin;
import id.tech.firas.mytraffic.utils.Pref;

public class HomeActivity extends MvpActivity<HomePresenter>
        implements HomeView, GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener, LocationListener {

    @BindView(R.id.tvLat)
    TextView tvLat;
    @BindView(R.id.tvLng)
    TextView tvLng;
    @BindView(R.id.activity_home)
    RelativeLayout activityHome;

    Pref pref;
    View parentView;
    ProgressDialog loading;


    public GoogleApiClient mGoogleApiClient;
    public LocationRequest mLocationRequest;
    public String mLastUpdateTime;

    private static final int REQUEST_ACCESS_FINE_LOCATION = 1;
    private static String[] PERMISSIONS_LOCATION = {
            Manifest.permission.ACCESS_FINE_LOCATION
    };

    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        verifyGPSPermissions(HomeActivity.this);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        pref = new Pref(getApplicationContext());
        parentView = activityHome;

        loading = new ProgressDialog(this);
        loading.setMessage("Loading...");
        loading.setIndeterminate(true);
        loading.setCancelable(false);

        setupGoogleAPI();
//        presenter.setupGoogleAPI(mGoogleApiClient, this, this, this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        // connect ke Google API Client ketika start
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION);

        if (permission == PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            if (mGoogleApiClient != null) {
                mGoogleApiClient.connect();
            }
        } else {

        }
//        presenter.onStartGoogleAPI(mGoogleApiClient, activity);

    }

    @Override
    protected void onStop() {
        super.onStop();
        // disconnect ke Google API Client ketika activity stopped
        if (mGoogleApiClient.isConnected()) {
            mGoogleApiClient.disconnect();
        }
//        presenter.onStopGoogleAPI(mGoogleApiClient);
    }

    private void setupGoogleAPI() {
        // initialize Google API Client
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        // get last location ketika berhasil connect
        mLocationRequest = LocationRequest.create();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setInterval(5000*2); // set interval insert lokasi
        mLocationRequest.setFastestInterval(3000);
        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
//        presenter.onConnectedGoogleAPI(mLocationRequest, mGoogleApiClient, this);
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.i("LOCATION", "Connection Suspended");
        mGoogleApiClient.connect();
//        presenter.onSuspendedGoogleAPI(mGoogleApiClient);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.i("LOCATION", "Connection failed. Error: " + connectionResult.getErrorCode());
    }

    @Override
    public void onLocationChanged(Location location) {
        mLastUpdateTime = DateFormat.getTimeInstance().format(new Date());
        tvLat.setText(String.valueOf(location.getLatitude()));
        tvLng.setText(String.valueOf(location.getLongitude()));
        Toast.makeText(this, "Updated: " + mLastUpdateTime, Toast.LENGTH_SHORT).show();
//        presenter.onLocationChangedGoogleAPI(mLastUpdateTime, tvLat, tvLng, location);
        presenter.getLatLng(String.valueOf(location.getLatitude()).toString(), String.valueOf(location.getLongitude()).toString(), pref);

    }

    public void verifyGPSPermissions(Activity activity) {
        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_LOCATION,
                    REQUEST_ACCESS_FINE_LOCATION
            );
        }
//        presenter.verifyPermission(activity, PERMISSIONS_LOCATION, REQUEST_ACCESS_FINE_LOCATION);
    }

    @Override
    public void showLoading() {
        loading.show();
    }

    @Override
    public void hideLoading() {
        loading.hide();
    }

    @Override
    public void getDataSuccess(ResponseLogin model) {
        Toast.makeText(HomeActivity.this, model.getMsg().toString(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void getResponFail(ResponseLogin model) {
        Toast.makeText(HomeActivity.this, model.getMsg().toString(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void getDataFail(String message) {
        Toast.makeText(HomeActivity.this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void getResponseSuccess(String message) {
        Snackbar.make(parentView, message, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    @Override
    public void moveToDetail(Intent intent) {
        startActivity(intent);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_logout) {
            presenter.doLogout(pref, this);
        }

        return super.onOptionsItemSelected(item);
    }
}
