package codebrains.crazysellout.Models;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Vasilhs on 1/9/2016.
 */
public class AccountModel {

    private JSONObject newAccountJSON;

    //Constructor
    public AccountModel(JSONObject jObj){
        this.newAccountJSON = jObj;
    }

    /**
     * Method that checks if the user agreed on the terms and conditions.
     */
    public void IsTermAndConditionsAgreed(){

        try {
            if(this.newAccountJSON.get("terms") == false){

                this.newAccountJSON.put("status", false);
                this.newAccountJSON.put("message", "You must agree to the terms and conditions\n" +
                        "in order to use this application!");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method that checks whether any of the fields are empty.
     */
    public void FieldEmpty(){

        try {
            if(this.newAccountJSON.get("username").equals("") || this.newAccountJSON.get("password").equals("") ||
                    this.newAccountJSON.get("repassword").equals("") || this.newAccountJSON.get("number").equals("") ||
                    this.newAccountJSON.get("emailName").equals("")) {

                this.newAccountJSON.put("status", false);
                this.newAccountJSON.put("message", "One or more field's of the form are empty!\n" +
                        "Please fill all the required fields and submit again.");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    /**
     * Method that checks if any of the fields contain invalid characters.
     */
    public void FieldContainsInvalidCharacters(){

        //Regex string pattern that allows letters and numbers only.
        String pattern= "^[a-zA-Z0-9]*$";
        try {
            if(!this.newAccountJSON.get("username").toString().matches(pattern) ||
                    !this.newAccountJSON.get("password").toString().matches(pattern) ||
                    !this.newAccountJSON.get("emailName").toString().matches(pattern)) {

                this.newAccountJSON.put("status", false);
                this.newAccountJSON.put("message", "One or more fields contain invalid characters!\n" +
                        "Please fill all the required fields properly.");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method that checks if any of the fields contain less characters than the required.
     * Username filed must contain at least 8 characters, password at least 6 characters and number
     * at least 10.
     */
    public void FieldsContainTheWrightCharacterLength(){

        try {
            if(this.newAccountJSON.get("username").toString().length() < 8 ||
                    this.newAccountJSON.get("password").toString().length() < 6 ||
                    this.newAccountJSON.get("number").toString().length() < 10 ) {

                this.newAccountJSON.put("status", false);
                this.newAccountJSON.put("message", "One or more fields contain less characters from" +
                        " the required length!\n Please fill all the fields properly.");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    public void PasswordAndRepasswordMismatch(){

        try {
            if(!this.newAccountJSON.get("password").toString().equals(
                    this.newAccountJSON.get("repassword").toString())){

                this.newAccountJSON.put("status", false);
                this.newAccountJSON.put("message", "The password field and re-type password field mismatch!");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }













}
