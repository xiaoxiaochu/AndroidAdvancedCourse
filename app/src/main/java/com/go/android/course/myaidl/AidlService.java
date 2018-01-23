package com.go.android.course.myaidl;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import com.go.android.course.IMyAidlInterface;

/**
 * Created by go on 2018/1/4.
 */

public class AidlService extends Service {


    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new MyBinder();
    }


  class MyBinder extends IMyAidlInterface.Stub{

      @Override
      public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

      }

      @Override
      public String getGame() throws RemoteException {
          return "Hello world!";
      }
  }

}
