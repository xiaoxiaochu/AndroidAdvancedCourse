package com.go.mvvmdemo.ui;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import com.go.mvvmdemo.R;

import com.go.mvvmdemo.databinding.ActivityUserDatabindingBinding;
import com.go.mvvmdemo.databinding.EvenListener;
import com.go.mvvmdemo.databinding.Presenter;
import com.go.mvvmdemo.databinding.Task;
import com.go.mvvmdemo.databinding.User;

/**
 * Created by go on 2018/2/28.
 */

public class UserActivity extends AppCompatActivity {


    private ActivityUserDatabindingBinding binding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  ActivityUserDatabindingBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_user_databinding);

     binding =   DataBindingUtil.setContentView(this, R.layout.activity_user_databinding);
        User user = new User();
        user.setFirstName("jack");
        user.setLastName("chu");

        final Task task = new Task("data", "2018", false);
        EvenListener listener = new EvenListener(this);
        Presenter presenter = new Presenter();

        binding.setPresenter(presenter);
        binding.setTask(task);

        binding.setHandlers(listener);
        binding.setUser(user);

       // task.setData("newData");


        Button button = binding.btnChange;



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            //    task.setData("newData");

                task.mData.set("newData");
                Toast.makeText(UserActivity.this, "ffff", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
