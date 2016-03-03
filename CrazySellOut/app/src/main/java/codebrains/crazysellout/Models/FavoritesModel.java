package codebrains.crazysellout.Models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

/**
 * Class that handles the display of all the favorite products of the user into a list. If the user
 * doesn't have any favorite product registered inside the database then the list is initialized with
 * a single message showing that there are no favorite products registered.
 */
public class FavoritesModel {

    //Constructor
    public FavoritesModel(){
    }

    /**
     * Method that is called whenever the user doesn't have any submitted favorite product into
     * the database.
     * @param message The message to be added into the array list.
     * @return Returns the array list to be dsplayed to the user.
     */
    public ArrayList<String> EmptyFavoriteList(String message){

        ArrayList<String> arrayList = new ArrayList();
        arrayList.add(message);

        return arrayList;
    }

    /**
     * Method that is called to display all the favorite product to the user.
     * @param jsonArray A json array that contains all the favorite records.
     * @return Returns an array list to be displayed to the user.
     * @throws JSONException Exception that is fired when something goes wrong while using json functions.
     */
    public ArrayList<String> SetFavoriteProductList(JSONArray jsonArray) throws JSONException {

        ArrayList<String> arrayList = new ArrayList();
        for(int i=0; i<jsonArray.length(); i++){

            JSONObject jsonObject = (JSONObject) jsonArray.get(i);
            String value = "Product Name : " + jsonObject.get("productName") + "\nStore Name : " +
                    jsonObject.get("storeName") + "\nPrice : " + jsonObject.get("price") +
                    "\nExpire Date : " + jsonObject.get("expireDate");

            arrayList.add(value);
        }


        return arrayList;
    }

}
