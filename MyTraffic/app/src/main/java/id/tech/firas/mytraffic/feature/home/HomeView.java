package id.tech.firas.mytraffic.feature.home;

import android.content.Intent;

import id.tech.firas.mytraffic.models.DataItem;
import id.tech.firas.mytraffic.models.ResponseLogin;

/**
 * Created by Firas Luthfi on 2/17/2018.
 */

interface HomeView {
//
    void showLoading();

    void hideLoading();

    void getDataSuccess(ResponseLogin model);

    void getDataFail(String message);

    void getResponseSuccess(String message);

    void getResponFail(ResponseLogin model);

    void moveToDetail(Intent intent);

}
