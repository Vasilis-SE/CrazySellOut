package codebrains.crazysellout.Models;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Vasilhs on 2/4/2016.
 */
public class AddProductModel {

    private JSONObject productJSON;

    //Constructor
    public AddProductModel(JSONObject jsonObject){
        this.productJSON = jsonObject;
    }

    /**
     * Method that checks whether any of the form fields are empty.
     */
    public void FieldIsEmptyCheck(){

        try {
            if(this.productJSON.get("productName").equals("") || this.productJSON.get("storeName").equals("") ||
                    this.productJSON.get("productPrice").equals("") || this.productJSON.get("description").equals("")){

                this.productJSON.put("status", false);
                this.productJSON.put("message", "One or more fields in the form is empty!\nPlease " +
                        "fill all the fields properly.");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method that checks if the coordinate data are initialized (meaning that the user has pressed
     * the get coordinates button).
     */
    public void CoordinatesDataNotSetted(){

        try {
            if(this.productJSON.get("longitude").equals("Longitude") ||
                    this.productJSON.get("latitude").equals("Latitude") ||
                    this.productJSON.get("city").equals("Store City")){

                this.productJSON.put("status", false);
                this.productJSON.put("message", "Coordinate data (Longitude, Latitude, City) are not " +
                        "initialized! Please press the button 'GET COORDINATES' to retrieve the data.");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }




}
