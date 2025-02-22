package com.example.tp2;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

public class location extends Activity implements LocationListener {
    private LocationManager locationManager;
    private TextView text;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.accelerometer);

        text = findViewById(R.id.text);
        TextView titre = findViewById(R.id.title);

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        titre.setText(R.string.Exo7_title);

        // Demande des permissions si nécessaire
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1001);
        } else {
            if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                abonnementGPS();
            } else {
                text.setText(R.string.Exo7_no_gps);
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (locationManager != null && locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            abonnementGPS();
        }
    }

    @Override
    public void onPause(){
        super.onPause();
        desabonnementGPS();
    }

    public void abonnementGPS() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 10, this);
    }

    public void desabonnementGPS() {
        if (locationManager != null) {
            locationManager.removeUpdates(this);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1001) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                text.setText(R.string.Exo7_permission_granted);
                if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                    abonnementGPS();
                } else {
                    text.setText(R.string.Exo7_no_gps);
                }
                Log.d("Permission", "Permission granted");
            } else {
                text.setText("Permission denied");
                Log.d("Permission", "Permission denied");
            }
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        if (location != null) {
            text.setText("Latitude: " + location.getLatitude() + "\nLongitude: " + location.getLongitude());
        } else {
            text.setText(R.string.Exo7_no_gps);
        }
    }
}
