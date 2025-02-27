package com.example.tp2.Exo2;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import com.example.tp2.R;

import java.util.ArrayList;
import java.util.List;

public class detect_captor extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState)	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exo2);
        TextView title = findViewById(R.id.text);
        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        List<Sensor> list_captor = new ArrayList<>();
        list_captor.add(sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION));
        list_captor.add(sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY));
        for(Sensor sensor : list_captor){
            if(sensor==null){
                title.setText(getString(R.string.Exo2_capteur)+" "+sensor.getName()+" "+getString(R.string.Exo2_pas_present));
                break;
            }
        }
        if(title.getText().equals("")) title.setText(R.string.Exo2_present);
    }
}
