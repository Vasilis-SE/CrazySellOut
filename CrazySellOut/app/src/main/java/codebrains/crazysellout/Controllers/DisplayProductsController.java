package codebrains.crazysellout.Controllers;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import codebrains.crazysellout.Models.ProductList;

/**
 * Created by Vasilhs on 2/9/2016.
 */
public class DisplayProductsController {

    private HashMap<String, List<String>> listDataChild;
    private List<String> listDataHeader;

    //Constructor
    public DisplayProductsController(JSONArray jsonArray){

        listDataChild = new HashMap<String, List<String>>();
        listDataHeader = new ArrayList<String>();
        this.SetProductListDataForDisplay(jsonArray);
    }

    /**
     * Main control method for displaying the products to the user.
     * @param jsonArray The array of product data in json format.
     */
    private void SetProductListDataForDisplay(JSONArray jsonArray){

        ProductList pl = new ProductList();
        this.listDataChild = pl.ListProductData(jsonArray);
        this.listDataHeader = pl.GetListOfHeaders();

    }

    public HashMap<String, List<String>> GetListOfProducts(){
        return this.listDataChild;
    }

    public List<String> GetListOfHeaders(){
        return this.listDataHeader;
    }


}
