package org.ms.matrix.app.ui.activity.login;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import org.ms.matrix.app.db.User;

public class LoginActivityViewModel extends ViewModel {


    private MutableLiveData<User> userMutableLiveData = new MutableLiveData<>();

    public MutableLiveData<User> getUserMutableLiveData() {
        return userMutableLiveData;
    }
}
