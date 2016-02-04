package codebrains.crazysellout.Controllers;

import org.json.JSONException;
import org.json.JSONObject;

import codebrains.crazysellout.Models.AddProductModel;

/**
 * Created by Vasilhs on 2/4/2016.
 */
public class AddProductController {

    private JSONObject productJSON;

    //Constructor
    public AddProductController(JSONObject jsonObject){
        this.productJSON = jsonObject;
    }

    /**
     * Main method for add product process. Calls all the necessary methods to check the progress of
     * the product addition.
     *
     * @return Returns a string message that will be displayed to the user.
     */
    public String AddProductMainControl(){

        AddProductModel apm = new AddProductModel(this.productJSON);

        apm.FieldIsEmptyCheck();
        apm.CoordinatesDataNotSetted();

        try {
            if(this.productJSON.get("status") == false){
                return this.productJSON.get("message").toString();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return "";
    }

}
