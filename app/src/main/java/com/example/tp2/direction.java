package com.example.tp2;

import static android.content.ContentValues.TAG;

import android.app.Activity;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class direction extends Activity implements SensorEventListener {
    private SensorManager sensorManager;
    private Sensor accelerometer;
    private List<Float> init = new ArrayList<>();
    private LinearLayout layout;
    private int currentColor = Color.GREEN;
    private float seuilMouvement = 0.7f;
    private float seuilTemps = 0.7f;
    private long lastUpdate = System.currentTimeMillis();
    private float deltaTime = 0.0f;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.accelerometer);

        layout = findViewById(R.id.layout);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);
        TextView titre = findViewById(R.id.title);
        titre.setText(R.string.Exo4_title);
        if (accelerometer == null) {
            TextView title = findViewById(R.id.text);
            title.setText(R.string.Exo4_acce_pas_present);
        } else {
            sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_UI);
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_LINEAR_ACCELERATION) {
            float x, y, z;
            TextView text = findViewById(R.id.text);

            long current = System.currentTimeMillis();
            deltaTime += (float) (current - lastUpdate) /1000;
            lastUpdate = current;
            Log.d(TAG, "onSensorChanged: "+deltaTime);


            x = event.values[0];
            y = event.values[1];
            z = event.values[2];

            if(x<-seuilMouvement && !text.getText().equals(getText(R.string.Exo4_gauche)) && deltaTime>seuilTemps){text.setText(R.string.Exo4_gauche); deltaTime=0f;}
            else if(x>seuilMouvement && !text.getText().equals(getText(R.string.Exo4_droite)) && deltaTime>seuilTemps){text.setText(R.string.Exo4_droite); deltaTime=0f;}
            else if(y>seuilMouvement && !text.getText().equals(getText(R.string.Exo4_haut)) && deltaTime>seuilTemps){text.setText(R.string.Exo4_haut); deltaTime=0f;}
            else if(y<-seuilMouvement && !text.getText().equals(getText(R.string.Exo4_bas)) && deltaTime>seuilTemps){text.setText(R.string.Exo4_bas); deltaTime=0f;}
            else if(z>seuilMouvement && !text.getText().equals(getText(R.string.Exo4_avant)) && deltaTime>seuilTemps){text.setText(R.string.Exo4_avant); deltaTime=0f;}
            else if(z<-seuilMouvement && !text.getText().equals(getText(R.string.Exo4_arriere)) && deltaTime>seuilTemps){text.setText(R.string.Exo4_arriere); deltaTime=0f;}
            else if(!text.getText().equals(getText(R.string.Exo4_pas_dir)) && deltaTime>seuilTemps){text.setText(R.string.Exo4_pas_dir); deltaTime=seuilTemps;}

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
