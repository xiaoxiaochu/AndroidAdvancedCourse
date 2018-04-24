package com.go.mvvmdemo.databinding;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

/**
 * Created by go on 2018/2/28.
 */

public class EvenListener {


    private Context context;

    public EvenListener(Context context) {
        this.context = context;
    }

    public void onclick(View view){

        Toast.makeText(context,"toast",Toast.LENGTH_SHORT).show();

        Log.i("chu", "binding onclick is trigged");


}

}
