package org.ms.matrix.app.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;


public class MatrixService extends Service {
    public MatrixService() {
    }

    @Override
    public IBinder onBind(Intent intent) {


        return null;
    }


    @Override
    public void onCreate() {
        super.onCreate();

    }
}
