package codebrains.crazysellout.Models;

import android.location.Location;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import codebrains.crazysellout.System.Conversions;

/**
 * Created by Vasilhs on 2/26/2016.
 */
public class CalculateDistanceModel {

    //Constructor
    public CalculateDistanceModel(){

    }

    /**
     * Method that initializes a JSON array which contains all the product that are in the range area
     * that the user selected.
     * @param responseArray The response json array that contains all the products.
     * @param startLongitude The longitude of the user.
     * @param startLatitude The latitude of the user.
     * @param distanceSelected The distance selected by the user from the spinner in string format.
     * @return Returns a json array which contains all the product in the selected range.
     * @throws JSONException Exception that occurs whenever something goes wrong with a json process.
     */
    public JSONArray CalculateDistanceForMap(JSONArray responseArray, double startLongitude,
                                             double startLatitude, String distanceSelected) throws JSONException {
        JSONArray jsonArray = new JSONArray();

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("startOrNot", true);
        jsonObject.put("longitude", startLongitude);
        jsonObject.put("latitude", startLatitude);
        jsonArray.put(jsonObject);

        //Setting the start location (the location of the user).
        Location locationA = new Location("point A");
        locationA.setLatitude(startLatitude);
        locationA.setLongitude(startLongitude);

        double selectedDistance = this.ConvertSelectedDistanceToRealDistance(distanceSelected);

        for(int i=0; i<responseArray.length(); i++){

            JSONObject productJSON = (JSONObject) responseArray.get(i);
            double distance = this.DistanceBetweenTwoPoints(locationA, Conversions.ConvertStringToDouble(productJSON.get("latitude").toString()),
                    Conversions.ConvertStringToDouble(productJSON.get("longitude").toString()));

            //If the distance between the user and the product is in the selected area then its good to go.
            if(distance <= selectedDistance){
                productJSON.put("startOrNot", false);
                jsonArray.put(productJSON);
            }
        }

        return jsonArray;
    }

    /**
     * Method that calculates the distance between the location of the user and tha location of each
     * product.
     * @param locationA The location of the user.
     * @param latitudeB The latitude of the product.
     * @param longitudeB The longitude of the product.
     * @return Returns the distance between the user and the product.
     */
    private double DistanceBetweenTwoPoints(Location locationA, double latitudeB, double longitudeB){

        Location locationB = new Location("point B");

        locationB.setLatitude(latitudeB);
        locationB.setLongitude(longitudeB);

        double distance = locationA.distanceTo(locationB);
        return distance;
    }

    /**
     * Method that creates a variable representing the distance the user selected in the spinner.
     * @param selectedDistance The selected distance from the spinner object in string format.
     * @return Returns a double variable which represent the real distance selected.
     */
    private double ConvertSelectedDistanceToRealDistance(String selectedDistance){

        double distance = 0.0;
        switch(selectedDistance){

            case "100m": distance = 100.0;
            break;

            case "500m": distance = 500.0;
            break;

            case "1km": distance = 1000.0;
            break;

            case "2km": distance = 2000.0;
            break;

            case "5km": distance = 5000.0;
            break;
        }

        return distance;
    }

}
