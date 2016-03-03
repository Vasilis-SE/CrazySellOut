package codebrains.crazysellout.TestModels;

import junit.framework.Assert;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import codebrains.crazysellout.Models.LoginModel;

/**
 * Test class that test all the methods inside the LoginModel java class.
 */
public class LoginModelTest {

    private JSONObject jsonObject;

    @Before
    public void Initialize(){
        this.jsonObject = new JSONObject();

        try {
            this.jsonObject.put("username", "");
            this.jsonObject.put("password", "newacc^&^* SHKJD&(ount123");
            this.jsonObject.put("status", true);
            this.jsonObject.put("message", "");
            this.jsonObject.put("terms", true);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void TestLoginAccountIsEmpty(){

        LoginModel lm = new LoginModel(this.jsonObject);
        lm.FieldEmpty();

        try {
            String result = this.jsonObject.get("message").toString();
            String expected = "One or more field's of the form are empty!\n" +
                    "Please fill all the required fields and submit again.";

            Assert.assertEquals(expected, result);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Test(expected = NullPointerException.class)
    public void TestNullJSONOnLoginCheck(){

        LoginModel lm = new LoginModel(this.jsonObject);
        lm.FieldEmpty();
    }

    @Test
    public void TestInvalidCharactersInLoginAccount(){

        LoginModel lm = new LoginModel(this.jsonObject);
        lm.FieldContainsInvalidCharacters();

        try {
            String result = this.jsonObject.get("message").toString();
            String expected = "One or more fields contain invalid characters!\n" +
                    "Please fill all the required fields properly.";

            Assert.assertEquals(expected, result);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Test(expected = NullPointerException.class)
    public void TestNullJSONOnInvalidCharactersInLoginAccount(){

        LoginModel lm = new LoginModel(this.jsonObject);
        lm.FieldContainsInvalidCharacters();
    }

    @After
    public void TearDown(){
        this.jsonObject = null;
    }


}
