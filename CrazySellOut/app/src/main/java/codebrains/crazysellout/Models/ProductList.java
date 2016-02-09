package codebrains.crazysellout.Models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

    public List<String> GetListOfHeaders(){
        return this.listDataHeader;
    }


}
