package codebrains.crazysellout.Models;

import org.json.JSONException;
import org.json.JSONObject;
import org.mockito.internal.matchers.Null;

/**
 * Class that contains proper methods to check the integrity of the new product before it is added
 * to the database.
 */
public class LoginModel {

    private JSONObject newAccountJSON;

    //Constructor
    public LoginModel(JSONObject jObj){
        this.newAccountJSON = jObj;
    }

    /**
     * Method that checks whether any of the fields are empty.
     */
    public void FieldEmpty(){

        try {
            if(this.newAccountJSON.get("username").equals("") || this.newAccountJSON.get("password").equals("")) {

                this.newAccountJSON.put("status", false);
                this.newAccountJSON.put("message", "One or more field's of the form are empty!\n" +
                        "Please fill all the required fields and submit again.");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            throw new NullPointerException();
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
                    !this.newAccountJSON.get("password").toString().matches(pattern)) {

                this.newAccountJSON.put("status", false);
                this.newAccountJSON.put("message", "One or more fields contain invalid characters!\n" +
                        "Please fill all the required fields properly.");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            throw new NullPointerException();
        }
    }



}
