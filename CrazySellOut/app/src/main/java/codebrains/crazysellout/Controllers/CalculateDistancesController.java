package codebrains.crazysellout.Controllers;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import codebrains.crazysellout.Models.CalculateDistanceModel;

/**
 * Created by Vasilhs on 2/26/2016.
 */
public class CalculateDistancesController {

    private JSONArray jsonArray;

    //Constructor
    public CalculateDistancesController(){
        this.jsonArray = new JSONArray();
    }

    /**
     * Method that handles the initialization of products that are in the same area the the user
     * selected.
     * @param response The response from the server.
     * @param startLongitude The longitude of the user.
     * @param startLatitude The latitude of the user.
     * @param distanceSelected The distance selected by the user from spinner item.
     */
    public void ControlMethodForCalculatingDistance(String response, double startLongitude, double startLatitude, String distanceSelected){

        try {
            JSONObject responseJSON = new JSONObject(response);

            if(responseJSON.get("status") == false){
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("startOrNot", true);
                jsonObject.put("longitude", startLongitude);
                jsonObject.put("latitude", startLatitude);

                this.jsonArray.put(jsonObject);
            }
            else{

                CalculateDistanceModel cdm = new CalculateDistanceModel();
                this.jsonArray = cdm.CalculateDistanceForMap((JSONArray) responseJSON.get("message"),
                        startLongitude, startLatitude, distanceSelected);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method that returns the initialized json array.
     * @return The json array that contains the products in specific area.
     */
    public JSONArray GetArrayOfProductInArea(){
        return this.jsonArray;
    }


}
