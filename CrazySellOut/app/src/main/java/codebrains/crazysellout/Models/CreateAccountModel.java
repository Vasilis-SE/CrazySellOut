package codebrains.crazysellout.Models;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Vasilhs on 1/9/2016.
 */
public class CreateAccountModel {

    private JSONObject newAccountJSON;

    //Constructor
    public CreateAccountModel(JSONObject jObj){
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











}
