package codebrains.crazysellout.Controllers;

import org.json.JSONException;
import org.json.JSONObject;

import codebrains.crazysellout.Models.CreateAccountModel;

/**
 * Created by Vasilhs on 1/9/2016.
 */
public class CreateAccountController {

    private JSONObject newAccountJSON;

    //Constructor
    public CreateAccountController(JSONObject jObj){
        this.newAccountJSON = jObj;
    }

    /**
     * Main control method that calls all the methods from the model that are necessary to create the
     * new account, in the end it sends a massage back to the view to be displayed to the user.
     *
     * @return Returns a string message that will be displayed to the user.
     */
    public String CreateNewAccountControlMethod(){

        CreateAccountModel cam = new CreateAccountModel(this.newAccountJSON);

        cam.IsTermAndConditionsAgreed();
        cam.FieldEmpty();
        cam.FieldContainsInvalidCharacters();
        cam.FieldsContainTheWrightCharacterLength();
        cam.PasswordAndRepasswordMismatch();


        try {
            if(this.newAccountJSON.get("status") == false){
                return this.newAccountJSON.get("message").toString();
            }
            else{
                return "Account has been created successfully!";
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return "";
    }


}
