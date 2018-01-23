package com.go.android.course.fragment;

import android.annotation.TargetApi;
import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.Toast;

import com.go.android.course.R;

/**
 * Created by go on 2018/1/2.
 */

public class DateFragment extends DialogFragment {





    public static DateFragment newInstance(){
        return new DateFragment();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View roootView = inflater.inflate(R.layout.fragment_date_time, container, false);
//        DatePicker datePicker = roootView.findViewById(R.id.date_picker);
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            datePicker.setOnDateChangedListener(new DatePicker.OnDateChangedListener() {
//                @Override
//                public void onDateChanged(DatePicker datePicker, int i, int i1, int i2) {
//                    Toast.makeText(getContext(), "i:" + i + "i1:"+i1 + "i2:" + i2, Toast.LENGTH_SHORT).show();
//                }
//            });
//        }
        return roootView;
    }


}
