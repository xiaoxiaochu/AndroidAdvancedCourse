package com.go.android.course.swipemenu;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.go.android.course.R;

/**
 * Created by go on 2018/2/7.
 */

public class SwipeActivity extends AppCompatActivity {


    private TextView tv_to_top, tv_to_unread, tv_to_delete, tv_content;
    private SwipeMenuLayout swipe_menu;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_my_swipe);
        swipe_menu = findViewById(R.id.swipe_menu);
        tv_to_top = findViewById(R.id.tv_to_top);
        tv_to_unread = findViewById(R.id.tv_to_unread);
        tv_to_delete = findViewById(R.id.tv_to_delete);
        tv_content = findViewById(R.id.tv_content);
        if (swipe_menu.isMenuOpen()) {
            swipe_menu.smoothToCloseMenu();
        }


        //如何没有为子view 设置监听事件，swipe_memu 不能触发onTouchEvent 事件，不能滑动？
//            tv_to_top.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if (swipe_menu.isMenuOpen()) {
//                        swipe_menu.smoothToCloseMenu();
//                    }
//                    Toast.makeText(SwipeActivity.this, "置顶", Toast.LENGTH_SHORT).show();
//                }
//            });
//          tv_to_unread.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if (swipe_menu.isMenuOpen()) {
//                        swipe_menu.smoothToCloseMenu();
//                    }
//                    Toast.makeText(SwipeActivity.this, "标为未读", Toast.LENGTH_SHORT).show();
//                }
//            });
//           tv_to_delete.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if (swipe_menu.isMenuOpen()) {
//                        swipe_menu.smoothToCloseMenu();
//                    }
//                    Toast.makeText(SwipeActivity.this, "删除", Toast.LENGTH_SHORT).show();
//                }
//            });
           swipe_menu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(SwipeActivity.this, "这是第" + "条数据", Toast.LENGTH_SHORT).show();
                }
            });



    }
}
