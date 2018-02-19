package id.tech.firas.mytraffic.feature.login;

import android.content.Intent;

import id.tech.firas.mytraffic.models.DataLogin;
import id.tech.firas.mytraffic.models.ResponseLogin;

/**
 * Created by Firas Luthfi on 2/18/2018.
 */

public interface LoginView {

    void showLoading();

    void hideLoading();

    void getPrefExpired(String message);

    void getDataSuccess(ResponseLogin model);

    void getDataFail(String message);

    void getResponFail(ResponseLogin model);

    void moveToDetail(Intent intent);

}
