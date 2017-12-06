package com.bharathksunilk.aidlservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

public class MyCalService extends Service {
    public MyCalService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    private final IMyAidlInterface.Stub mBinder = new IMyAidlInterface.Stub(){

        @Override
        public int add(int a, int b) throws RemoteException {
            return a + b;
        }

        @Override
        public int multiply(int a, int b) throws RemoteException {
            return a * b;
        }

        @Override
        public int subtract(int a, int b) throws RemoteException {
            return a - b;
        }
    };
}
