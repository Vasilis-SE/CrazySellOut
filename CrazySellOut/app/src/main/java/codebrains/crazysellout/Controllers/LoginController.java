package codebrains.crazysellout.Controllers;

import org.json.JSONException;
import org.json.JSONObject;

import codebrains.crazysellout.Models.LoginModel;

/**
 * Created by Vasilhs on 1/25/2016.
 */
public class LoginController {

    private JSONObject loginJSON;

    //Constructor
    public LoginController(JSONObject jObj){
        this.loginJSON = jObj;
    }

    /**
     * Main control mehtod of login process.
     *
     * @return Returns a string message that indicates the status of the process.
     */
    public String LoginControlMethod(){

        LoginModel lm = new LoginModel(this.loginJSON);

        lm.FieldEmpty();
        lm.FieldContainsInvalidCharacters();

        try {
            if(this.loginJSON.get("status") == false){
                return this.loginJSON.get("message").toString();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return "";
    }

}
