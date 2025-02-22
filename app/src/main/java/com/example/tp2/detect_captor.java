package com.example.tp2;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class detect_captor extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState)	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detect_captor);
        TextView title = findViewById(R.id.text);
        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        List<Sensor> list_captor = new ArrayList<>();
        list_captor.add(sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION));
        list_captor.add(sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY));
        for(Sensor sensor : list_captor){
            if(sensor==null){
                title.setText(R.string.Exo2_capteur+" "+sensor.getName()+" "+R.string.Exo2_pas_present);
                break;
            }
        }
        if(title.getText().equals("")) title.setText(R.string.Exo2_present);
    }
}
