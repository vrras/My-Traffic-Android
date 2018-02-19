package id.tech.firas.mytraffic.base.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;

import id.tech.firas.mytraffic.base.ui.BaseActivity;
import id.tech.firas.mytraffic.base.ui.BasePresenter;

/**
 * Created by Firas Luthfi on 2/17/2018.
 */

public abstract class MvpActivity<P extends BasePresenter> extends BaseActivity {
    protected P presenter;

    protected abstract P createPresenter();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        presenter = createPresenter();
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.dettachView();
        }
    }
}
