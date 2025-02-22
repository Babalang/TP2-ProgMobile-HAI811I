package com.example.tp2;

import static android.content.ContentValues.TAG;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.graphics.Camera;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class lampe extends Activity implements SensorEventListener {
    private SensorManager sensorManager;
    private Sensor accelerometer;
    private Camera cam = new Camera();
    private List<Float> init = new ArrayList<>();
    private LinearLayout layout;
    private CameraManager cameraManager;
    private String cameraId;
    private boolean isFlashlightOn = false;
    private static final float SHAKE_THRESHOLD = 3.25f;
    private static final int MIN_TIME_BETWEEN_SHAKES_MILLISECS = 1000;
    private long mLastShakeTime;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.accelerometer);

        layout = findViewById(R.id.layout);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);
        TextView titre = findViewById(R.id.title);
        titre.setText(R.string.Exo5_title);
        if (accelerometer == null) {
            TextView title = findViewById(R.id.text);
            title.setText(R.string.Exo4_acce_pas_present);
        } else {
            sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_UI);
        }
        boolean hasFlash = getApplicationContext().getPackageManager()
                .hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);

        if (!hasFlash) {
            TextView title = findViewById(R.id.text);
            title.setText(R.string.Exo5_pas_flash);
        } else {
            cameraManager = (CameraManager) getSystemService(getApplicationContext().CAMERA_SERVICE);
            try {
                cameraId = cameraManager.getCameraIdList()[0];
            } catch (CameraAccessException e) {
                Log.e(TAG, "Error getting camera ID", e);
            }
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_LINEAR_ACCELERATION) {
            long curTime = System.currentTimeMillis();
            if ((curTime - mLastShakeTime) > MIN_TIME_BETWEEN_SHAKES_MILLISECS) {

                float x = event.values[0];
                float y = event.values[1];
                float z = event.values[2];

                double acceleration = Math.sqrt(Math.pow(x, 2) +
                        Math.pow(y, 2) +
                        Math.pow(z, 2)) - SensorManager.GRAVITY_EARTH;
                if (acceleration > SHAKE_THRESHOLD) {
                    mLastShakeTime = curTime;
                    try {
                        toggleFlashlight();
                    } catch (CameraAccessException e) {
                        throw new RuntimeException(e);
                    }

                }
            }
        }
    }
    private void toggleFlashlight() throws CameraAccessException {
        if (isFlashlightOn) {
            cameraManager.setTorchMode(cameraId, false);
            isFlashlightOn = false;
            TextView title = findViewById(R.id.text);
            title.setText(R.string.Exo5_Flash_off);
        }
        else{
            cameraManager.setTorchMode(cameraId, true);
            isFlashlightOn = true;
            TextView title = findViewById(R.id.text);
            title.setText(R.string.Exo5_Flash_on);
        }
    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Ignoré pour le moment
    }

    @Override
    protected void onPause() {
        sensorManager.unregisterListener(this, accelerometer);
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (accelerometer != null) {
            sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_UI);
        }
    }
}
