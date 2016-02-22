package codebrains.crazysellout.Controllers;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

import codebrains.crazysellout.Models.FavoritesModel;

/**
 * Created by Vasilhs on 2/22/2016.
 */
public class DisplayFavoritesController {

    private String response;

    //Constructor
    public DisplayFavoritesController(String response){
        this.response = response;
    }

    /**
     * Method that handles the display of favorite products to the user.
     * @return Returns an array list that will be displayed to user.
     */
    public ArrayList<String> SetFavoritesListForDisplay(){

        ArrayList<String> arrayList = null;
        try {
            JSONObject jsonObject = new JSONObject(this.response);

            if(jsonObject.get("status") == false){

                FavoritesModel fm = new FavoritesModel();
                arrayList = fm.EmptyFavoriteList(jsonObject.get("message").toString());
            }
            else{

                JSONArray jsonArray = (JSONArray) jsonObject.get("message");
                FavoritesModel fm = new FavoritesModel();
                arrayList = fm.SetFavoriteProductList(jsonArray);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }


        return arrayList;
    }



}
