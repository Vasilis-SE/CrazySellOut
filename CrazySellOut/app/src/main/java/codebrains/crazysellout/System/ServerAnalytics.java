package codebrains.crazysellout.System;

/**
 * Created by Vasilhs on 1/24/2016.
 */
public class ServerAnalytics {


    //Constructor
    public ServerAnalytics(){

    }

    /**
     * Method that removes the analytics characters from the php server response.
     *
     * @param response The response string message from the server.
     * @return Returns a string with the analytics removed (the string is a JSON).
     */
    public static String RemoveServerAnalyticsFromResponse(String response){

        String result = response.substring(3).trim();

        return result;
    }

}
