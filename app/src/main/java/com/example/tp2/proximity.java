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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class proximity extends Activity implements SensorEventListener {
    private SensorManager sensorManager;
    private Sensor proximity;
    private List<Float> init = new ArrayList<>();
    private LinearLayout layout;
    private ImageView image;

    private static final int SENSOR_SENSITIVITY = 4;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.accelerometer);
        image = findViewById(R.id.image);
        layout = findViewById(R.id.layout);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        proximity = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        TextView titre = findViewById(R.id.title);
        titre.setText(R.string.Exo6_title);
        if (proximity == null) {
            TextView title = findViewById(R.id.text);
            title.setText(R.string.Exo6_pas_prox);
        } else {
            sensorManager.registerListener(this, proximity, SensorManager.SENSOR_DELAY_UI);
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_PROXIMITY) {
            TextView text = findViewById(R.id.text);
            float distance = event.values[0];
            if (distance >= -SENSOR_SENSITIVITY && distance <= SENSOR_SENSITIVITY) {
                layout.setBackgroundColor(Color.RED);
                text.setText(distance + " cm");
                image.setImageResource(R.drawable.close);
            } else {
                layout.setBackgroundColor(Color.GREEN);
                text.setText(distance + " cm");
                image.setImageResource(R.drawable.far);
            }
        }
    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Ignoré pour le moment
    }

    @Override
    protected void onPause() {
        sensorManager.unregisterListener(this, proximity);
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (proximity != null) {
            sensorManager.registerListener(this, proximity, SensorManager.SENSOR_DELAY_UI);
        }
    }
}
