package codebrains.crazysellout.Controllers;

import android.app.Activity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import codebrains.crazysellout.Models.ProductList;
import codebrains.crazysellout.System.SystemDialogs;

/**
 * Created by Vasilhs on 2/9/2016.
 */
public class DisplayProductsController {

    private HashMap<String, List<String>> listDataChild;
    private List<String> listDataHeader;

    //Constructor
    public DisplayProductsController(){

        listDataChild = new HashMap<String, List<String>>();
        listDataHeader = new ArrayList<String>();
    }

    /**
     * Main control method for displaying the products to the user.
     * @param jsonArray The array of product data in json format.
     */
    public void SetProductListDataForDisplay(JSONArray jsonArray){

        ProductList pl = new ProductList();
        this.listDataChild = pl.ListProductData(jsonArray);
        this.listDataHeader = pl.GetListOfHeaders();

    }

    /**
     * Method that handles the process of constructing the display of user products to the list view.
     * @param response The string response from the server.
     * @param activity The activity that called the method.
     * @return Returns a list of product to be displayed to the user.
     */
    public ArrayList<String> SetListOfUserProductsForDisplay(String response, Activity activity){

        ArrayList<String> list = null;
        try {
            JSONObject jsonObject = new JSONObject(response);

            if(jsonObject.get("status") == false){

                ProductList pl = new ProductList();
                list = pl.ArrayListForEmptyUserProductList(jsonObject.get("message").toString());
            }
            else{

                JSONArray jsonArray = (JSONArray) jsonObject.get("message");
                ProductList pl = new ProductList();
                list = pl.GetUserProducts(jsonArray);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return list;
    }


    public HashMap<String, List<String>> GetListOfProducts(){
        return this.listDataChild;
    }

    public List<String> GetListOfHeaders(){
        return this.listDataHeader;
    }


}
