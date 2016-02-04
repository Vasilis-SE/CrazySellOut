package codebrains.crazysellout.System;

import android.util.Log;

/**
 * Created by Vasilhs on 2/3/2016.
 */
public class Conversions {

    //Constructor
    public Conversions(){

    }

    public static Double ConvertStringToDouble(String string){

        double data = Double.parseDouble(string);
        return data;
    }

    public static String ConvertDoubleToString(double num){

        Log.d("Started : ", String.valueOf(num));
        String data = String.valueOf(num);
        Log.d("Value of place : ", data);
        return data;
    }

}
