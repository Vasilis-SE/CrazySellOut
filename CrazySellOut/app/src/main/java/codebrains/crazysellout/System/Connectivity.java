package codebrains.crazysellout.System;

import android.app.Activity;
import android.content.Context;
import android.net.wifi.WifiManager;

import codebrains.crazysellout.Activities.MainActivity;

/**
 * Created by Vasilhs on 1/20/2016.
 */
public class Connectivity {

    //Constructor
    public Connectivity(){

    }

    /**
     * Method that checks if the wifi of the mobile phone is enabled. If not it calls the error
     * alert box method to display an error message and exit the application.
     *
     * @param activityObj The object of the activity from which the wifi service will be checked.
     */
    public static void WifiEnabledCheck(Activity activityObj){

        WifiManager mng = (WifiManager) activityObj.getSystemService(Context.WIFI_SERVICE);

        if(!mng.isWifiEnabled()){
            SystemDialogs.DisplayErrorAlertBox("Your wifi is not enabled!\nPlease turn your wifi on" +
                    " and re-open the application.", "Wifi not enabled", activityObj);
        }
    }


}
