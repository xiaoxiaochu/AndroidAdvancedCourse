package com.go.android.course.thread;

import android.util.Log;




/**
 * Created by go on 2018/4/24.
 */
public class MiroThread implements Runnable {
    @Override
    public void run() {

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.i("chu",  Thread.currentThread().getName());
    }
}
