package codebrains.crazysellout.System;

import android.app.Activity;
import android.content.Context;
import android.net.wifi.WifiManager;

import java.io.IOException;
import java.net.InetAddress;

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

    /**
     * Method that checks whether the server is reachable / available to the application.
     *
     * @param activityObj The object of the activity.
     */
    public static boolean RemoteServerIsReachable(Activity activityObj){

        try {
            if(!InetAddress.getByName("http://crazysellout.comule.com").isReachable(2000)){
                SystemDialogs.DisplayInformationAlertBox("The server is not reachable at the moment.\n" +
                        "Please try again later.", "Server Connection", activityObj);
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return true;
    }


}
