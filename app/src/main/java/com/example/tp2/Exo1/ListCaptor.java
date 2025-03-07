package com.example.tp2.Exo1;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.tp2.R;

import java.util.List;

public class ListCaptor extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exo1);
        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        ListView lv = findViewById(R.id.list_item);
        List<Sensor> sensors = sensorManager.getSensorList(Sensor.TYPE_ALL);
        if (sensors != null) {
            lv.setAdapter(new ArrayAdapter<>(this, R.layout.list_item, sensors));
        }
    }
}