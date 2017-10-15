package com.example.ronny.custom_brightness;

import android.content.Context;
import android.hardware.Sensor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class main_frag extends Fragment {
    private Integer[] def_array = {0,0,0,0,0,0,0,0,0,0,0};
    private Custom_Bright default_;
    GraphView graph;
    public main_frag() {
        // Required empty public constructor
    }

    public RelativeLayout lay;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main_frag , container,false);
        lay = (RelativeLayout)root.findViewById(R.id.lay);
        Integer [] arr = new Integer[def_array.length];
        if(getArguments()!=null) {
            def_array = getArguments().getIntegerArrayList("1").toArray(arr);
            default_ = new Custom_Bright(def_array);
        }else{
            default_ =new Custom_Bright(def_array);
        }
        graph = new GraphView(getContext(),default_.bright_pointsx,default_.bright_pointsy);
        graph.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,800));
        lay.addView(graph);
        return root;
    }
    public float getVal(float val , float max){
        if(val==max){
            return graph.map(graph.pointsy.elementAt(graph.pointsy.size()),0,100,0,1);
        }
       float index = (val/ max)*10;
        int indexl =((int)Math.floor(index)*10);
        int indexh = indexl +10;
        Log.d("tag"," "+ indexh+ " " + indexl +" " +index);
        float ratio = (graph.pointsy.elementAt(indexh/10)-graph.pointsy.elementAt(indexl/10))/(indexh-indexl);
        int fx = (int)(ratio *(index*10 -indexl) + graph.pointsy.elementAt(indexl/10));
        return graph.map(fx,0,100,0,1);

    }
}
