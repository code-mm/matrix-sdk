package org.ms.matrix.app.ui.fragment.message;

import org.ms.module.base.presenter.BasePresenter;

public class MessageFragmentPresenter extends BasePresenter<MessageFragmentModel,MessageFragment>
{


    public MessageFragmentPresenter(MessageFragment view) {
        super(view);
    }

    @Override
    protected MessageFragmentModel initModel() {
        return null;
    }

    @Override
    public void onDestory() {

    }
}
