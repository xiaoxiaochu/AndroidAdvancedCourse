package com.go.mvvmdemo.databinding;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;

import com.go.mvvmdemo.BR;

/**
 * Created by go on 2018/2/28.
 */

public class Task extends BaseObservable {


    public ObservableField<String> mData = new ObservableField<>();

    public ObservableField<String> mTime = new ObservableField<>();

    public ObservableBoolean mIsTask = new ObservableBoolean(false);




    private String data;

    private String time;


    private boolean isTask;



    public Task(String data, String time, boolean isTask) {

        mData.set(data);
        mTime.set(time);
        mIsTask.set(isTask);
    }


    @Bindable
    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
        notifyPropertyChanged(BR.data);

    }

    @Bindable
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
        notifyPropertyChanged(BR.time);
    }

    @Bindable
    public boolean isTask() {
        return isTask;
    }

    public void setTask(boolean task) {
        isTask = task;
        notifyPropertyChanged(BR.task);
    }
}
