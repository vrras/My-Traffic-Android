package id.tech.firas.mytraffic.feature.login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.tech.firas.mytraffic.R;
import id.tech.firas.mytraffic.base.mvp.MvpActivity;
import id.tech.firas.mytraffic.models.ResponseLogin;
import id.tech.firas.mytraffic.utils.Pref;

public class LoginActivity extends MvpActivity<LoginPresenter>
        implements LoginView{

    @BindView(R.id.etUsername)
    EditText etUsername;
    @BindView(R.id.etPassword)
    EditText etPassword;
    @BindView(R.id.tvPass)
    TextView tvPass;
    @BindView(R.id.btnLogin)
    Button btnLogin;
    @BindView(R.id.btnRegister)
    Button btnRegister;
    @BindView(R.id.tvVersion)
    TextView tvVersion;
    @BindView(R.id.rlLayout)
    RelativeLayout rlLayout;
    View parentView;

    ProgressDialog loading;
    Pref pref;

    @Override
    protected LoginPresenter createPresenter() {
        return new LoginPresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        parentView = rlLayout;
        pref = new Pref(getApplicationContext());

        loading = new ProgressDialog(this);
        loading.setMessage("Loading...");
        loading.setIndeterminate(true);
        loading.setCancelable(false);

        presenter.checkPref(pref, etUsername, this);

    }

    @OnClick({R.id.btnLogin, R.id.btnRegister})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnLogin:
                String user = etUsername.getText().toString();
                String pass = etPassword.getText().toString();

                presenter.doLogin(pref, user, pass, this);
                break;
            case R.id.btnRegister:
                break;
        }
    }

    @Override
    public void showLoading() {
        loading.show();
    }

    @Override
    public void hideLoading() {
        loading.dismiss();
    }

    @Override
    public void getPrefExpired(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void getDataSuccess(ResponseLogin model) {
        Toast.makeText(LoginActivity.this, model.getMsg().toString(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void getDataFail(String message) {
        Snackbar.make(parentView, message, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    @Override
    public void getResponFail(ResponseLogin model) {
        Snackbar.make(parentView, model.getMsg().toString(), Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    @Override
    public void moveToDetail(Intent intent) {
        startActivity(intent);
        finish();
    }
}
