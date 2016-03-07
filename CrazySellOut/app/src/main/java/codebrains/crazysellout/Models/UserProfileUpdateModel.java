package codebrains.crazysellout.Models;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Class that contains methods valid for checking the update user profile data.
 */
public class UserProfileUpdateModel {

    //Constructor
    public UserProfileUpdateModel(){

    }

    /**
     * Method that checks if the fields of the user profile is in the right character length.
     * @param jsonObject The json object that contains all the user profile data.
     * @throws JSONException Exception that occurs whenever there is a problem while processing json data.
     */
    public void FieldsAreInTheProperRangeOfCharacters(JSONObject jsonObject) throws JSONException {

        if(jsonObject.get("retypePassword").toString().length() < 6 || jsonObject.get("number").toString().length() < 10 ) {

            jsonObject.put("status", false);
            jsonObject.put("message", "One or more fields contain less characters from" +
                    " the required length! Please fill all the fields properly.");
        }
    }

    /**
     * Method that checks firstly if the user wants to update his password and the if the new password
     * is the same with the one in the retype password field.
     * @param jsonObject The json object that contains all the user profile data.
     * @throws JSONException Exception that occurs whenever there is a problem while processing json data.
     */
    public void NewPasswordAndRetypedPasswordIsTheSame(JSONObject jsonObject) throws JSONException {

        if(!jsonObject.get("retypePassword").equals("") && !jsonObject.get("password").equals("")){
            if(!jsonObject.get("retypePassword").equals(jsonObject.get("password"))){

                jsonObject.put("status", false);
                jsonObject.put("message", "Password and Retype Password fields mismatch!");
            }
        }

    }

    /**
     * Method that checks if the email field has been changed, and if it did change then if it contains
     * the wright email service name and the annotation character '@'.
     * @param jsonObject The json object that contains all the user profile data.
     * @param serviceArray An array of string that contain all the valid mail service names.
     * @throws JSONException Exception that occurs whenever there is a problem while processing json data.
     */
    public void EmailFieldContainsMailServicePart(JSONObject jsonObject, String[] serviceArray) throws JSONException {

        boolean check = false;
        for(String service : serviceArray){

            if(jsonObject.get("email").toString().contains("@" + service)){
                check = true;
            }
        }

        jsonObject.put("status", check);
        jsonObject.put("message", "Email field must contain valid email service name, such as : gmail.com," +
                " outlook.com, yahoo.gr etc.");
    }


}
