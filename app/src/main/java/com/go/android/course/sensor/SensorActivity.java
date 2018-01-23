package com.go.android.course.sensor;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.go.android.course.R;

import java.util.List;

/**
 * Created by go on 2018/1/8.
 */

public class SensorActivity extends AppCompatActivity implements SensorEventListener{



    private boolean mIsLightSensorPresent = false;

    private SensorManager mSensorManager;

    private Sensor mLightSensor;

    private TextView list_sensor;

    private Sensor mSensorProximity;
    private Sensor mSensorLight;

    // TextViews to display current sensor values
    private TextView mTextSensorLight;
    private TextView mTextSensorProximity;

    private ImageView img_beatles;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sensor);

        list_sensor = findViewById(R.id.list_sensor);
        mTextSensorLight = findViewById(R.id.label_light);
        mTextSensorProximity = findViewById(R.id.label_proximity);
        img_beatles = findViewById(R.id.img_beatles);

        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        if (mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD) != null){
            mLightSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);

        }

        mSensorProximity =
                mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        mSensorLight = mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);

        String error = getResources().getString(R.string.error_no_sensor);

        if (mSensorLight == null) {
            mTextSensorLight.setText(error);
        }


        if (mSensorProximity == null) {
            mTextSensorProximity.setText(error);
        }
      List<Sensor> senSorlist = getSensorList();


        StringBuilder sensorText = new StringBuilder();
        for (Sensor currentSensor : senSorlist ) {
            sensorText.append(currentSensor.getName()).append(
                    System.getProperty("line.separator"));
        }

        list_sensor.setText(sensorText);

    }

    private List<Sensor> getSensorList() {
       return mSensorManager.getSensorList(Sensor.TYPE_ALL);

    }


    @Override
    protected void onStart() {
        super.onStart();
        if (mIsLightSensorPresent){
            mSensorManager.registerListener(this, mLightSensor,SensorManager.SENSOR_DELAY_UI);
        }


        if (mSensorProximity != null) {
            mSensorManager.registerListener(this, mSensorProximity,
                    SensorManager.SENSOR_DELAY_NORMAL);
        }
        if (mSensorLight != null) {
            mSensorManager.registerListener(this, mSensorLight,
                    SensorManager.SENSOR_DELAY_NORMAL);
        }


    }


    @Override
    protected void onStop() {
        super.onStop();
        mSensorManager.unregisterListener(this);
    }


    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        int sensorType = sensorEvent.sensor.getType();
        float currentValue = sensorEvent.values[0];

        switch (sensorType){
            case Sensor.TYPE_LIGHT:
                if (currentValue < 50){
                 //   getWindow().getDecorView().setBackgroundColor(Color.BLACK);
                }else {
                  //  getWindow().getDecorView().setBackgroundColor(Color.WHITE);
                }

                mTextSensorLight.setText(getResources().getString(
                        R.string.label_light, currentValue));
                break;
            case Sensor.TYPE_PROXIMITY:
                if (currentValue == 0){
               //     ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(img_beatles, "scaleX",  0.8f);

                    PropertyValuesHolder holder1 = PropertyValuesHolder.ofFloat("scaleX", 0.8f);
                    PropertyValuesHolder holder2 = PropertyValuesHolder.ofFloat("scaleY", 0.8f);


                    final ObjectAnimator objectAnimator2 = ObjectAnimator.ofPropertyValuesHolder(img_beatles, holder1, holder2).setDuration(500);
                    objectAnimator2.start();



                    objectAnimator2.addListener(new Animator.AnimatorListener() {
                        @Override
                        public void onAnimationStart(Animator animator) {

                        }

                        @Override
                        public void onAnimationEnd(Animator animator) {
                            animator.getClass().getSimpleName();
                            Log.i("animation",  "动画结束" +animator.getClass().getSimpleName());
                            Toast.makeText(SensorActivity.this, "动画结束", Toast.LENGTH_SHORT).show();

                        }

                        @Override
                        public void onAnimationCancel(Animator animator) {

                        }

                        @Override
                        public void onAnimationRepeat(Animator animator) {

                        }
                    });


                 //   objectAnimator.setDuration(1000).start();

                //    img_beatles.animate().scaleX(0.8f).scaleY(0.8f);
                }else {
                    ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(img_beatles, "scaleX",  1.0f);
                    objectAnimator.setDuration(1000).start();
                //    img_beatles.animate().scaleX(1.f).scaleY(1.f);
                }
                mTextSensorProximity.setText(getResources().getString(
                        R.string.label_proximity, currentValue));
                break;
        }

        if (sensorType == Sensor.TYPE_LIGHT){


        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
