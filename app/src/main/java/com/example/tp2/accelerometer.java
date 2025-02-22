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

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class accelerometer extends Activity implements SensorEventListener {
    private SensorManager sensorManager;
    private Sensor accelerometer;
    private List<Float> init = new ArrayList<>();
    private LinearLayout layout;
    private int currentColor = Color.GREEN;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.accelerometer);

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

            // Extraire uniquement l'accélération due aux mouvements (filtre passe-haut)
            x = event.values[0];
            y = event.values[1];
            z = event.values[2];

            // Calculer la norme de l'accélération

            float acceleration = (float) Math.sqrt(x * x + y * y + z * z);


            // Changer la couleur selon l'intensité du mouvement
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
