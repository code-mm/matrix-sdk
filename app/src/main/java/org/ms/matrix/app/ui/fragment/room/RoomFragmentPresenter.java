package org.ms.matrix.app.ui.fragment.room;

import org.ms.module.base.presenter.BasePresenter;

public class RoomFragmentPresenter extends BasePresenter<RoomFragmentModel,RoomFragment> {
    public RoomFragmentPresenter(RoomFragment view) {
        super(view);
    }

    @Override
    protected RoomFragmentModel initModel() {
        return null;
    }

    @Override
    public void onDestory() {

    }
}
