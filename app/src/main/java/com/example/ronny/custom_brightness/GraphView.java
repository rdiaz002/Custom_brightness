package com.example.ronny.custom_brightness;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;
import android.support.v4.view.VelocityTrackerCompat;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;


import java.util.Vector;
import java.util.logging.Logger;

/**
 * Created by Ronny on 10/14/2017.
 */


public class GraphView extends View {
    private Paint paint;
    private Vector<Integer> pointsx;
    public Vector<Integer> pointsy;
    float graphWidth;
    float graphHeight;
    float paddingy;
    float paddingx;
    int pointx = 0;
    private VelocityTracker mVelo;

    public GraphView(Context context, Vector pointsx, Vector pointsy) {
        super(context);
        this.pointsx = pointsx;
        this.pointsy = pointsy;
        paint = new Paint();
        graphWidth = (float) getWidth();
        graphHeight = (float) getHeight();
        paddingy = 0; //(float)getHeight();
        paddingx = 0;//(float)(getWidth()*0.10);

    }


    @Override
    protected void onDraw(Canvas canvas) {
        int index = pointsx.size();

        paddingy =  (float)(getHeight()*0.02);
        paddingx =  (float)(getWidth()*0.00);
        graphWidth = (float) (getWidth() );
        graphHeight = (float) (getHeight() * 0.95);
        float inx = graphWidth / 100;
        float iny = graphHeight / 100;

        draw_grid(canvas);
        paint.setTextAlign(Align.LEFT);
        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(10);
        for (int i = 0; i < pointsy.size(); i++) {
            canvas.drawCircle(i * 10 * inx + paddingx, graphHeight - (pointsy.get(i) * iny) + paddingy, 10, paint);

        }
        for (int i = 0; i < pointsy.size() - 1; i++) {
            canvas.drawLine(i * 10 * inx + paddingx, graphHeight - (pointsy.get(i) * iny) + paddingy, (i + 1) * 10 * inx + paddingx, graphHeight - (pointsy.get(i + 1) * iny) + paddingy, paint);
        }


    }


    public void draw_grid(Canvas canvas) {
        paint.setStrokeWidth(5);
        paint.setColor(Color.GRAY);

        float inx = graphWidth / 100;
        float iny = graphHeight / 100;
        for (int i = 0; i <= 100; i += 10) {
            canvas.drawLine(i * inx + paddingx, graphHeight + paddingy, i * inx + paddingx, paddingy, paint);
            canvas.drawLine(paddingx, i * iny + paddingy, graphWidth + paddingx, i * iny + paddingy, paint);
        }
    }


    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {

        int pointerId = motionEvent.getPointerId(motionEvent.getActionIndex());
        switch (motionEvent.getAction()) {

            case MotionEvent.ACTION_DOWN:

                pointx = ((int)Math.floor((motionEvent.getRawX() / ((getWidth()/11)))));
                Log.d("tag","" + pointx + " " +motionEvent.getRawX()+ "  "+ graphWidth);
                if (mVelo == null) {
                    mVelo = VelocityTracker.obtain();
                } else {
                    mVelo.clear();
                }
                mVelo.addMovement(motionEvent);
                break;
            case MotionEvent.ACTION_MOVE:
                mVelo.addMovement(motionEvent);
                mVelo.computeCurrentVelocity(1000);
                if (VelocityTrackerCompat.getYVelocity(mVelo, pointerId) < 0) {
                    if (pointsy.get(pointx) < 100) {
                        pointsy.set(pointx, pointsy.get(pointx) + 1);

                    }

                } else if (VelocityTrackerCompat.getYVelocity(mVelo, pointerId) > 0) {
                    if (pointsy.get(pointx) > 0) {
                        pointsy.set(pointx, pointsy.get(pointx) - 1);
                    }
                }

                invalidate();
                break;


        }

        return true;
    }

    public float map(float x, float in_min, float in_max, float out_min, float out_max){
        return (x - in_min) * (out_max - out_min) / (in_max - in_min) + out_min;
    }
    public float get_Brightness ( float val, float max , float g_max ){

        return map(val ,0,max,0,g_max);

    }
}