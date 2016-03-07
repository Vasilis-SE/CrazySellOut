package codebrains.crazysellout.System;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import codebrains.crazysellout.Activities.MainActivity;

/**
 * Created by Vasilhs on 1/20/2016.
 */
public class SystemDialogs {

    //Constructor
    public SystemDialogs() {

    }

    /**
     * Method that displays to the user an alert box that on ok selection it disappears (without
     * exiting the application).
     *
     * @param message     The message to be displayed to the user.
     * @param title       The title of the alert box.
     * @param activityObj The object of the activity to be displayed to.
     */
    public static void DisplayInformationAlertBox(String message, String title, Activity activityObj) {

        AlertDialog.Builder builder1 = new AlertDialog.Builder(activityObj);
        builder1.setTitle(title);
        builder1.setMessage(message);
        builder1.setCancelable(true);
        builder1.setNeutralButton(android.R.string.ok,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();

    }

    /**
     * Method that displays to the user an alert box that on ok selection it exits the application.
     *
     * @param message     The message to be displayed to the user.
     * @param title       The title of the alert box.
     * @param activityObj The object of the activity to be displayed to.
     */
    public static void DisplayErrorAlertBox(String message, String title, final Activity activityObj) {

        AlertDialog.Builder builder1 = new AlertDialog.Builder(activityObj);
        builder1.setTitle(title);
        builder1.setMessage(message);
        builder1.setCancelable(true);
        builder1.setNeutralButton(android.R.string.ok,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        activityObj.finish();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();

    }

}
