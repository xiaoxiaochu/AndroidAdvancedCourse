package com.go.android.course.sensor;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.Surface;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.go.android.course.R;

/**
 * Created by go on 2018/1/9.
 */

public class MotionAndPositionActivity extends AppCompatActivity  implements SensorEventListener{



    private SensorManager mSensorManager;

    private Sensor accelerometerSensor;

    private Sensor mapnetometerSensor;

    private float[] mAccelerometerData = new float[3];
    private float[] mMagnetometerData = new float[3];


    private TextView tv_muth;
    private TextView tv_pitch;
    private TextView tv_roll;

    private ImageView mSpotTop;
    private ImageView mSpotBottom;
    private ImageView mSpotLeft;
    private ImageView mSpotRight;

    private Display mDisplay;

    private static final float VALUE_DRIFT = 0.05f;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

     //   setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sensor_motion);

        tv_muth = findViewById(R.id.tv_amuth);
        tv_pitch = findViewById(R.id.tv_pitch);
        tv_roll = findViewById(R.id.tv_roll);

        mSpotTop = (ImageView) findViewById(R.id.spot_top);
        mSpotBottom = (ImageView) findViewById(R.id.spot_bottom);
        mSpotLeft = (ImageView) findViewById(R.id.spot_left);
        mSpotRight = (ImageView) findViewById(R.id.spot_right);

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);


        accelerometerSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        mapnetometerSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);

        WindowManager wm = (WindowManager) getSystemService(WINDOW_SERVICE);
        mDisplay = wm.getDefaultDisplay();


    }





    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        int sensorType = sensorEvent.sensor.getType();

        switch (sensorType) {
            case Sensor.TYPE_ACCELEROMETER:
                mAccelerometerData = sensorEvent.values.clone();
                break;
            case Sensor.TYPE_MAGNETIC_FIELD:
                mMagnetometerData = sensorEvent.values.clone();
                break;
            default:
                return;
        }

        float[] rotationMatrix = new float[9];
        boolean rotationOK = SensorManager.getRotationMatrix(rotationMatrix,
                null, mAccelerometerData, mMagnetometerData);

        float orientationValues[] = new float[3];

        if (rotationOK) {
            SensorManager.getOrientation(rotationMatrix, orientationValues);
        }

        float[] rotationMatrixAdjusted = new float[9];

        switch (mDisplay.getRotation()){
            case Surface.ROTATION_0:
                rotationMatrixAdjusted = rotationMatrix.clone();
                break;
            case Surface.ROTATION_90:
                SensorManager.remapCoordinateSystem(rotationMatrix,
                        SensorManager.AXIS_Y, SensorManager.AXIS_MINUS_X,
                        rotationMatrixAdjusted);
                break;
            case Surface.ROTATION_180:
                SensorManager.remapCoordinateSystem(rotationMatrix,
                        SensorManager.AXIS_MINUS_X, SensorManager.AXIS_MINUS_Y,
                        rotationMatrixAdjusted);
                break;
            case Surface.ROTATION_270:
                SensorManager.remapCoordinateSystem(rotationMatrix,
                        SensorManager.AXIS_MINUS_Y, SensorManager.AXIS_X,
                        rotationMatrixAdjusted);
                break;

        }

     //   float orientationValues[] = new float[3];
        if (rotationOK) {
            SensorManager.getOrientation(rotationMatrixAdjusted,
                    orientationValues);
        }

        float azimuth = orientationValues[0];
        float pitch = orientationValues[1];
        float roll = orientationValues[2];

        if (Math.abs(pitch) < VALUE_DRIFT) {
            pitch = 0;
        }
        if (Math.abs(roll) < VALUE_DRIFT) {
            roll = 0;
        }
        mSpotTop.setAlpha(0f);
        mSpotBottom.setAlpha(0f);
        mSpotLeft.setAlpha(0f);
        mSpotRight.setAlpha(0f);

        if (pitch > 0) {
            mSpotBottom.setAlpha(pitch);
        } else {
            mSpotTop.setAlpha(Math.abs(pitch));
        }
        if (roll > 0) {
            mSpotLeft.setAlpha(roll);
        } else {
            mSpotRight.setAlpha(Math.abs(roll));
        }

        tv_muth.setText(getResources().getString(
                R.string.value_format, azimuth));
        tv_pitch.setText(getResources().getString(
                R.string.value_format, pitch));
        tv_roll.setText(getResources().getString(
                R.string.value_format, roll));







    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    protected void onStart() {
        super.onStart();

        if (mapnetometerSensor != null) {
            mSensorManager.registerListener(this, mapnetometerSensor,
                    SensorManager.SENSOR_DELAY_NORMAL);
        }
        if (accelerometerSensor != null) {
            mSensorManager.registerListener(this, accelerometerSensor,
                    SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        mSensorManager.unregisterListener(this);
    }


}
