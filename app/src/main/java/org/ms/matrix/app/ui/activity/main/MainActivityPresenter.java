package org.ms.matrix.app.ui.activity.main;

import org.ms.module.base.presenter.BasePresenter;

public class MainActivityPresenter extends BasePresenter<MainActivityModel,MainActivity> {
    public MainActivityPresenter(MainActivity view) {
        super(view);
    }

    @Override
    protected MainActivityModel initModel() {
        return new MainActivityModel(this);
    }

    @Override
    public void onDestory() {

    }
}
