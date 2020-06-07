package org.ms.matrix.sdk.impl.config;

import org.ms.matrix.sdk.supper.inter.config.IConfigAdapter;

public class ConfigImpl extends IConfigAdapter {
    private String homeServer;

    @Override
    public void setHomeServer(String homeServer) {
        this.homeServer = homeServer;
    }

    @Override
    public String getHomeServer() {
        return homeServer;
    }
}
