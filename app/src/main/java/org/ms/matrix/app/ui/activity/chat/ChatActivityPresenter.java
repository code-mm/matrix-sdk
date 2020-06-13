package org.ms.matrix.app.ui.activity.chat;

import org.ms.module.base.presenter.BasePresenter;

public class ChatActivityPresenter  extends BasePresenter<ChatActivityModel, ChatActivity> {

    public ChatActivityPresenter(ChatActivity view) {
        super(view);
    }

    @Override
    protected ChatActivityModel initModel() {
        return null;
    }

    @Override
    public void onDestory() {

    }
}
