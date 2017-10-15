package com.example.ronny.custom_brightness;

/**
 * Created by Ronny on 10/14/2017.
 */

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Vector;

class points {

}

public class Custom_Bright {

    public Vector<Integer> bright_pointsx;
    public Vector<Integer> bright_pointsy;

    public Custom_Bright(Integer[] array) {

        bright_pointsx = new Vector<Integer>(Arrays.asList(0, 10, 20, 30, 40, 50, 60, 70, 80, 90, 100));
        bright_pointsy = new Vector<Integer>(Arrays.asList(array));

        Collections.sort(bright_pointsx);

    }

    public void add_points(Integer a) {
        bright_pointsx.add(a);
        Collections.sort(bright_pointsx);

    }

    public void remove_points(Integer a) {
        bright_pointsx.remove(a);
        Collections.sort(bright_pointsx);

    }

}
