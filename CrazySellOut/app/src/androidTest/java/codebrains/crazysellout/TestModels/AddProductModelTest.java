package codebrains.crazysellout.TestModels;

import junit.framework.Assert;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import codebrains.crazysellout.Models.AddProductModel;

import static codebrains.crazysellout.Activities.MainProducerActivity.GetUsername;

/**
 * Test class that test each and every method inside the AddProductModel java class.
 */
public class AddProductModelTest {

    private JSONObject jsonObject;

    @Before
    public void Initialize(){

        jsonObject = new JSONObject();
        try {
            jsonObject.put("productName", "productName");
            jsonObject.put("storeName", "storeName");
            jsonObject.put("category", "");
            jsonObject.put("productPrice", "productPrice");
            jsonObject.put("description", "description");
            jsonObject.put("expireDate", "expireDate");
            jsonObject.put("longitude", "Longitude");
            jsonObject.put("latitude", "Latitude");
            jsonObject.put("city", "Store City");
            jsonObject.put("username", "username");
            jsonObject.put("status", true);
            jsonObject.put("message", "");
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void TestFieldInProductAdditionIsEmpty(){

        AddProductModel apm = new AddProductModel(this.jsonObject);
        apm.FieldIsEmptyCheck();

        try {
            String result = this.jsonObject.get("message").toString();
            String expected = "One or more fields in the form is empty!\nPlease " +
                    "fill all the fields properly.";

            Assert.assertEquals(expected, result);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Test(expected = NullPointerException.class)
    public void TestNullJSONOnEmptyFieldCheck(){

        AddProductModel apm = new AddProductModel(null);
        apm.FieldIsEmptyCheck();
    }

    @Test
    public void TestCoordinatesInFormNotSetted(){

        AddProductModel apm = new AddProductModel(this.jsonObject);
        apm.CoordinatesDataNotSetted();

        try {
            String result = this.jsonObject.get("message").toString();
            String expected = "Coordinate data (Longitude, Latitude, City) are not " +
                    "initialized! Please press the button 'GET COORDINATES' to retrieve the data.";

            Assert.assertEquals(expected, result);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Test(expected = NullPointerException.class)
    public void TestNullJSONOnSetCoordinatesCheck(){

        AddProductModel apm = new AddProductModel(null);
        apm.CoordinatesDataNotSetted();
    }


    @After
    public void TearDown(){
        this.jsonObject = null;
    }

}
