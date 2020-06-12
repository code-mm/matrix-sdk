
package org.ms.matrix.app;


import android.app.Application;

import org.ms.matrix.sdk.client.MatrixClient;
import org.ms.module.supper.client.Modules;

public class App extends Application {



    public static String userId;


    @Override
    public void onCreate() {
        super.onCreate();

        Modules.getControlSwitch().setLogOut(true);
        Modules.getControlSwitch().setRequestLog(true);
        Modules.getControlSwitch().setPrintStackTrace(true);
        Modules.getLogModule().setAliyunSend(false);

        // 设置url
        MatrixClient.getInstance().getConfig().setHomeServer("https://matrix.mhw828.com/");


    }
}