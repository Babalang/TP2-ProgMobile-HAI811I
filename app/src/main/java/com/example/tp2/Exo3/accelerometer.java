package com.example.tp2.Exo3;

import android.app.Activity;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.tp2.R;

public class accelerometer extends Activity implements SensorEventListener {
    private SensorManager sensorManager;
    private Sensor accelerometer;
    private LinearLayout layout;
    private int currentColor = Color.GREEN;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exo3);

        layout = findViewById(R.id.layout);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);
        TextView titre = findViewById(R.id.title);
        titre.setText(R.string.Exo3_title);
        if (accelerometer == null) {
            TextView title = findViewById(R.id.text);
            title.setText(R.string.Exo3_absence_capteur);
        } else {
            sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_UI);
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_LINEAR_ACCELERATION) {
            float x, y, z;
            TextView text = findViewById(R.id.text);
            x = event.values[0];
            y = event.values[1];
            z = event.values[2];
            float acceleration = (float) Math.sqrt(x * x + y * y + z * z);
            int newColor;
            if (acceleration > 15) {
                newColor = Color.RED; // Mouvement très intense
                text.setText(R.string.Exo3_Mouvement_intense);
            } else if (acceleration > 7) {
                newColor = Color.BLACK; // Mouvement moyen
                text.setText(R.string.Exo3_Mouvement_moyen);
            } else if (acceleration > 0.5) {
                newColor = Color.GREEN; // Mouvement faible ou stable
                text.setText(R.string.Exo3_Mouvement_faible_stable);
            } else {
                newColor = Color.WHITE; // Pas de mouvement
                text.setText(R.string.Exo3_Pas_mouvement);
            }

            if (newColor != currentColor) {
                layout.setBackgroundColor(newColor);
                currentColor = newColor;
            }
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
