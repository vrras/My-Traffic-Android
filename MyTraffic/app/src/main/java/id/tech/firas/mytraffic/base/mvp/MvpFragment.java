package id.tech.firas.mytraffic.base.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import id.tech.firas.mytraffic.base.ui.BaseFragment;
import id.tech.firas.mytraffic.base.ui.BasePresenter;

/**
 * Created by Firas Luthfi on 2/17/2018.
 */

public abstract class MvpFragment<P extends BasePresenter> extends BaseFragment {
    protected P presenter;

    protected abstract P createPresenter();

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        presenter = createPresenter();
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.dettachView();
        }
    }
}
