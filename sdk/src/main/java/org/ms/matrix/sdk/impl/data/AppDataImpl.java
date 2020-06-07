package org.ms.matrix.sdk.impl.data;

import org.ms.matrix.sdk.supper.inter.data.IAppData;

public class AppDataImpl implements IAppData {


    private static final AppDataImpl instance = new AppDataImpl();

    public static AppDataImpl getInstance() {
        return instance;
    }
}
