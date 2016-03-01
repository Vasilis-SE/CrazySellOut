package codebrains.crazysellout.System;


/**
 * Class that contains methods valid to convert a data format type to another.
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

        double data;

        try {
            data = Double.parseDouble(string);
        }
        catch(NullPointerException ex){
            throw new NullPointerException();
        }

        return data;
    }

    /**
     * Method that convert a double data format to string.
     * @param num The double number to be converted.
     * @return Returns the converted string.
     */
    public static String ConvertDoubleToString(double num){

        String data;

        try {
            data = String.valueOf(num);
        }
        catch(NullPointerException ex){
            throw new NullPointerException();
        }

        return data;
    }

    /**
     * Method that converts a string data format into integer.
     * @param num The string data to be converted.
     * @return Returns the converted integer data.
     */
    public static int ConvertStringToInteger(String num){

        int number;

        try {
            number = Integer.parseInt(num);
        }
        catch(NullPointerException ex){
            throw new NullPointerException();
        }

        return number;
    }

    /**
     * Method that converts an integer data format to string.
     * @param num The integer to be converted.
     * @return Returns the converted string data.
     */
    public static String ConvertIntegerToString(int num){

        String string;

        try {
            string = String.valueOf(num);
        }
        catch (NullPointerException ex){
            throw new NullPointerException();
        }

        return string;
    }

}
