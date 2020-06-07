package org.ms.matrix.sdk.impl.data;

import org.ms.matrix.sdk.supper.inter.data.IAppData;
import org.ms.matrix.sdk.supper.inter.data.IDataAdapter;
import org.ms.matrix.sdk.supper.inter.data.IUserData;

public class DataImpl extends IDataAdapter {



    @Override
    public IAppData getAppData() {
        return  AppDataImpl.getInstance();
    }

    @Override
    public IUserData getUserData() {
        return  UserDataImpl.getInstance();
    }
}
