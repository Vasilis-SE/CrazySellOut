package codebrains.crazysellout.Models;

import android.text.Html;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Vasilhs on 2/9/2016.
 */
public class ProductList {

    private List<String> listDataHeader;

    //Constructor
    public ProductList(){
        listDataHeader = new ArrayList<String>();
    }

    /**
     * Method that reads the array of product data and creates a hash map of data of the products and
     * the headers of each item, that will be listed into the expandable list.
     * @param jsonArray The array of product data in json format.
     * @return Returns a hash map of the product data.
     */
    public HashMap<String, List<String>> ListProductData(JSONArray jsonArray){

        HashMap<String, List<String>> listOfProducts = new HashMap<String, List<String>>();
        List<String> childDataList;

        try {

            for(int i=0; i < jsonArray.length(); i++){

                JSONObject jsonObject = jsonArray.getJSONObject(i);
                childDataList = this.GetProductChildData(jsonObject);
                this.listDataHeader.add(jsonObject.get("productName").toString());

                listOfProducts.put(jsonObject.get("productName").toString(), childDataList);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return listOfProducts;
    }

    /**
     * Method that is called from the ListProductData for every product and creates a list that contains
     * the specific product data.
     * @param jsonObject The json object that contains the product data.
     * @return Returns a list that contain the product data.
     */
    private List<String> GetProductChildData(JSONObject jsonObject){

        List<String> list = new ArrayList<String>();

        try {

            list.add("Store Name : " + jsonObject.get("storeName"));
            list.add("Category : " + jsonObject.get("category"));
            list.add("Price : " + jsonObject.get("price"));
            list.add("Expire Date : " + jsonObject.get("expireDate"));
            list.add("City : " + jsonObject.get("city"));
            list.add("Description : " + jsonObject.get("description"));

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return list;
    }

    /**
     * Method that constructs a list of product items from a specific producer.
     * @param jsonArray The response array of product from server.
     * @return Returns a list of string data, each one is a product item.
     * @throws JSONException Exception that occurs whenever the something goes wrong with the JSON data.
     */
    public ArrayList<String> GetUserProducts(JSONArray jsonArray) throws JSONException{

        ArrayList<String> list = new ArrayList<String>();
        for(int i=0; i < jsonArray.length(); i++){

            JSONObject jsonObject = (JSONObject) jsonArray.get(i);
            String value = "<b>Product Name :</b>" + jsonObject.get("productName") +
                    "\n<b>Price :</b>" + jsonObject.get("price") + "\n<b>Expire Date :</b>" + jsonObject.get("expireDate");

            //Format the above string to html, so it can show show the bold text.
            Html.fromHtml(value);

            list.add(value);
        }

        return list;
    }

    public List<String> GetListOfHeaders(){
        return this.listDataHeader;
    }


}
