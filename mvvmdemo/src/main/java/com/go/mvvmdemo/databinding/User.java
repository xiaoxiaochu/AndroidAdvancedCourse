package com.go.mvvmdemo.databinding;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableField;

import com.go.mvvmdemo.BR;

/**
 * Created by go on 2018/2/28.
 */

public class User extends BaseObservable {

    private  String firstName;
    private  String lastName;




    public ObservableField<String> mFirstName = new ObservableField<>();

    public ObservableField<String> mLastName = new ObservableField<>();


    public User() {
    }

    public User(String name, String lastname) {
        mFirstName.set(name);
        mLastName.set(lastname);

    }

    @Bindable
    public String getFirstName() {
        return this.firstName;
    }

    @Bindable
    public String getLastName() {
        return this.lastName;
    }


    public void setFirstName(String firstName) {
        this.firstName = firstName;
        notifyPropertyChanged(BR.firstName);
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
        notifyPropertyChanged(BR.lastName);
    }
}
