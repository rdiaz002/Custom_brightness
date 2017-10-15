package com.example.ronny.custom_brightness;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.RunnableFuture;


public class MainActivity extends AppCompatActivity implements Runnable, SensorEventListener{
    private Context mContext;
    private SensorManager LIT;
    private Sensor light;
    private float level;
    private float max_level;
    public Button b1;
    public Button b2;
    public Button b3;
    main_frag frag;
    Integer[] arr1 = {0,0,0,0,0,0,0,0,0,0,0};
    Integer[] arr2 = {0,0,0,0,0,0,0,0,0,0,0};
    Integer[] arr3 = {0,0,0,0,0,0,0,0,0,0,0};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LIT = (SensorManager)getSystemService(SENSOR_SERVICE);
        light = LIT.getDefaultSensor(Sensor.TYPE_LIGHT);
        LIT.registerListener(this,light,SensorManager.SENSOR_DELAY_NORMAL);
        max_level=light.getMaximumRange();
        b1 = (Button) findViewById(R.id.Custom1);
        b2 = (Button) findViewById(R.id.Custom2);
        b3 = (Button) findViewById(R.id.Custom3);

        frag = new main_frag();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
                ft.add(R.id.frag11,frag);
                ft.commit();
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                frag = new main_frag();
                Bundle bud = new Bundle();
                bud.putIntegerArrayList("1", new ArrayList<Integer>(Arrays.asList(arr1)));
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                frag.setArguments(bud);
                ft.replace(R.id.frag11,frag);
                ft.commit();
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                frag = new main_frag();
                Bundle bud = new Bundle();
                bud.putIntegerArrayList("1", new ArrayList<Integer>(Arrays.asList(arr2)));
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                frag.setArguments(bud);
                ft.replace(R.id.frag11,frag);
                ft.commit();
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                frag = new main_frag();
                Bundle bud = new Bundle();
                bud.putIntegerArrayList("1", new ArrayList<Integer>(Arrays.asList(arr3)));
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                frag.setArguments(bud);
                ft.replace(R.id.frag11,frag);
                ft.commit();
            }
        });

        mContext = getApplicationContext();
    }


    @Override
    public void run() {

    }

    @Override
    protected void onPause() {
        super.onPause();
        LIT.unregisterListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        LIT.registerListener(this,light,SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if(sensorEvent.sensor.getType()==Sensor.TYPE_LIGHT){
            WindowManager.LayoutParams s = getWindow().getAttributes();
            s.screenBrightness = frag.getVal(sensorEvent.values[0],max_level/100);
            getWindow().setAttributes(s);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
