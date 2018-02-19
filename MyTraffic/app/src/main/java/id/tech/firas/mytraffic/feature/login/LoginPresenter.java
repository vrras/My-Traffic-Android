package id.tech.firas.mytraffic.feature.login;

import android.app.Activity;
import android.content.Intent;
import android.widget.EditText;

import id.tech.firas.mytraffic.base.ui.BasePresenter;
import id.tech.firas.mytraffic.feature.home.HomeActivity;
import id.tech.firas.mytraffic.models.ResponseLogin;
import id.tech.firas.mytraffic.network.NetworkCallback;
import id.tech.firas.mytraffic.utils.MyConstant;
import id.tech.firas.mytraffic.utils.Pref;

/**
 * Created by Firas Luthfi on 2/18/2018.
 */

public class LoginPresenter extends BasePresenter<LoginView> {

    public LoginPresenter(LoginView view) {
        super.attachView(view);
    }

    public void checkPref(Pref pref, EditText etUser, Activity activity) {
        if (pref.isLoggedIn()) {
            getItem(activity);
        }else{
            if (pref.userdata(MyConstant.KEY_SESSION_USERNAME)!= null){
                etUser.setText(pref.userdata(MyConstant.KEY_SESSION_USERNAME));
                view.getPrefExpired("Session login has expired.");
            }else {
                etUser.setText("");
            }
        }
    }

    public void doLogin(final Pref pref, String username, String password, final Activity activity) {
        view.showLoading();
        addSubscribe(apiStores.getLogin(username, password), new NetworkCallback<ResponseLogin>() {
            @Override
            public void onSuccess(ResponseLogin model) {
               if(model.isStatus()){
                   pref.setLogin(true);
                   pref.set_userdata(MyConstant.KEY_SESSION_USERNAME, model.getData().get(0).getUsername().toString());
                   pref.set_userdata(MyConstant.KEY_SESSION_USERID, String.valueOf(model.getData().get(0).getIdUser()));
                   pref.set_userdata(MyConstant.KEY_SESSION_FULLNAME, model.getData().get(0).getFullName().toString());

                   view.getDataSuccess(model);
                   getItem(activity);
               }else{
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

    public void getItem(Activity activity) {
        Intent intent = new Intent(activity, HomeActivity.class);
        view.moveToDetail(intent);
    }

}
