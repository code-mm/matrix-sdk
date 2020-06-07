package org.ms.matrix.sdk.client;

import org.ms.matrix.sdk.supper.inter.config.IConfig;
import org.ms.matrix.sdk.supper.Client;
import org.ms.matrix.sdk.supper.inter.user.IUser;

public final class MatrixClient {

    private static final MatrixClient instance = new MatrixClient();

    public static MatrixClient getInstance() {
        return instance;
    }



    private MatrixClient() {

    }

    public IConfig getConfig() {
        return Client.getConfig();
    }

    public IUser getUser(){
        return Client.getUser();
    }


}
