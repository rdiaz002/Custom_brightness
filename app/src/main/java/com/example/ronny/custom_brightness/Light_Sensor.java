package com.example.ronny.custom_brightness;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

/**
 * Created by Ronny on 10/15/2017.
 */

public class Light_Sensor implements SensorEventListener{
    private Sensor lit_sensor;
    public float lit_level;

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if(sensorEvent.sensor.getType() == Sensor.TYPE_LIGHT){
            lit_sensor = sensorEvent.sensor;
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
    public void setLight(float Sen){
        lit_level = Sen;
    }
    public Sensor return_sensor(){
        return lit_sensor;
    }

}
