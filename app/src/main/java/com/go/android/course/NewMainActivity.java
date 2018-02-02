package com.go.android.course;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.go.android.course.communication.CommunicationMainActivity;
import com.go.android.course.overdraw.GpuOverdrawAtivity;

import com.go.library.Chu$$Chu;
import com.go.library.ZyaoAnnotation;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by go on 2018/1/17.
 */


@ZyaoAnnotation(
        name = "JChu",
        text = "Hello !!! Welcome "
)
public class NewMainActivity extends AppCompatActivity implements OnClickListener{

    @BindView(R.id.btn_main)
   public Button btn_main;

    @BindView(R.id.btn_overdraw)
   public Button btn_overdraw;

    @BindView(R.id.btn_drag)
    public Button btn_drag;

    @BindView(R.id.btn_youtube)
    public Button btn_youtube;

    Chu$$Chu chu$$Chu;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_main);

        ButterKnife.bind(this);


        chu$$Chu = new Chu$$Chu();
//        btn_main = findViewById(R.id.btn_main);
//        btn_overdraw = findViewById(R.id.btn_overdraw);
//        btn_drag =findViewById(R.id.btn_drag);
//        btn_youtube = findViewById(R.id.btn_youtube);
        btn_youtube.setOnClickListener(this);
        btn_drag.setOnClickListener(this);
        btn_main.setOnClickListener(this);
        btn_overdraw.setOnClickListener(this);




    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_main:
                Toast.makeText(NewMainActivity.this, chu$$Chu.getMessage(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(NewMainActivity.this, CommunicationMainActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_overdraw:
                Intent intent1 = new Intent(NewMainActivity.this, GpuOverdrawAtivity.class);
                startActivity(intent1);
                break;
            case R.id.btn_drag:
                Intent intent2 = new Intent(NewMainActivity.this, DragLayoutActivity.class);
                intent2.putExtra("horizontal", true);
                startActivity(intent2);
                break;
            case R.id.btn_youtube:
                Intent intentYoutube = new Intent(NewMainActivity.this, YouTubeActivity.class);
                startActivity(intentYoutube);
                break;

        }
    }
}
