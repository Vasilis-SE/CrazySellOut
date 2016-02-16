package codebrains.crazysellout.System;

import android.util.Log;

/**
 * Created by Vasilhs on 2/3/2016.
 */
public class Conversions {

    //Constructor
    public Conversions(){

    }

    /**
     * Method that converts a string format data to double.
     * @param string The string to be converted.
     * @return Returns the converted double format data.
     */
    public static Double ConvertStringToDouble(String string){

        double data = Double.parseDouble(string);
        return data;
    }

    /**
     * Method that convert a double data format to string.
     * @param num The double number to be converted.
     * @return Returns the converted string.
     */
    public static String ConvertDoubleToString(double num){

        String data = String.valueOf(num);
        return data;
    }

    /**
     * Method that converts a string data format into integer.
     * @param num The string data to be converted.
     * @return Returns the converted integer data.
     */
    public static int ConvertStringToInteger(String num){

        int number = Integer.parseInt(num);
        return number;
    }

    /**
     * Method that converts an integer data format to string.
     * @param num The integer to be converted.
     * @return Returns the converted string data.
     */
    public static String ConvertIntegerToString(int num){

        String string = String.valueOf(num);
        return string;
    }

}
