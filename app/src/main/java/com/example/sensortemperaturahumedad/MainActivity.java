package com.example.sensortemperaturahumedad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    private TextView temperaturaTextView;
    private TextView humedadTextView;
    private SensorManager sensorManager;
    private Sensor temperaturaSensor;
    private Sensor humedadSensor; // Agregado el sensor de humedad
    private Boolean temperaturaDisponible;
    private Boolean humedadDisponible;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        temperaturaTextView = findViewById(R.id.Temp);
        humedadTextView = findViewById(R.id.Humed);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        // verifica la disponibilidad de los sensores
        temperaturaDisponible = false;
        humedadDisponible = false;
        if (sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE) != null) {
            temperaturaSensor = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
            temperaturaDisponible = true;
        } else {
            temperaturaTextView.setText("El sensor de temperatura no está disponible");
        }

        // Agregar verificación de disponibilidad del sensor de humedad
        if (sensorManager.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY) != null) {
            humedadSensor = sensorManager.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY);
            humedadDisponible = true;
        } else {
            humedadTextView.setText("El sensor de humedad no está disponible");
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_AMBIENT_TEMPERATURE) {
            // Manejar cambios en la temperatura aquí
        } else if (event.sensor.getType() == Sensor.TYPE_RELATIVE_HUMIDITY) {
            // Manejar cambios en la humedad aquí
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (temperaturaDisponible) {
            sensorManager.registerListener(this, temperaturaSensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
        if (humedadDisponible) {
            sensorManager.registerListener(this, humedadSensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }
}