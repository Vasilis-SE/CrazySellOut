package codebrains.crazysellout.System;

/**
 * Created by Vasilhs on 1/24/2016.
 */
public class ServerAnalytics {


    //Constructor
    public ServerAnalytics(){

    }

    /**
     *
     * @param response
     * @return
     */
    public static String RemoveServerAnalyticsFromResponse(String response){

        String result = response.substring(3).trim();

        return result;
    }

}
