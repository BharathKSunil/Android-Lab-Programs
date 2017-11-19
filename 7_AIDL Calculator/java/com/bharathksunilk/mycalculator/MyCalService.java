package com.bharathksunilk.mycalculator;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

public class MyCalService extends Service {
    public MyCalService() {
    }

    @Override
    public IBinder onBind(Intent arg0) {
        return stub;
    }
    IMyCalService.Stub stub=new IMyCalService.Stub() {
        @Override
        public int sub(int a, int b) throws RemoteException {
            return a-b;
        }
        @Override
        public int mul(int a, int b) throws RemoteException {
            return a*b;
        }
        @Override
        public int add(int a, int b) throws RemoteException {
            return a+b;
        }
    };
}
