package codebrains.crazysellout.TestModels;

import junit.framework.Assert;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import codebrains.crazysellout.Models.AccountModel;

/**
 * Test class that test the account model java class.
 */
public class AccountModelTest {

    private JSONObject jsonObject;
    private AccountModel accountModel;

    @Before
    public void Initialize(){
        this.jsonObject = new JSONObject();

        try {
            this.jsonObject.put("username", "newaccount");
            this.jsonObject.put("password", "newaccount123");
            this.jsonObject.put("status", true);
            this.jsonObject.put("message", "");
            this.jsonObject.put("terms", true);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void TestTermsAndConditionsAreEnabled(){

        this.accountModel = new AccountModel(this.jsonObject);
        this.accountModel.IsTermAndConditionsAgreed();

        try {
            String result = jsonObject.get("message").toString();
            Assert.assertEquals("", result);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void TestTermsAndConditionsAreNotEnabled(){

        try {
            this.jsonObject.put("terms", false);
            this.accountModel = new AccountModel(this.jsonObject);
            this.accountModel.IsTermAndConditionsAgreed();

            String result = jsonObject.get("message").toString();
            String expected = "You must agree to the terms and conditions\n" +
                    "in order to use this application!";

            Assert.assertEquals(expected, result);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void TestFieldInAccountFormIsEmpty(){

        try {
            this.jsonObject.put("username", "");
            this.accountModel = new AccountModel(this.jsonObject);
            this.accountModel.FieldEmpty();

            String result = jsonObject.get("message").toString();
            String expected = "One or more field's of the form are empty!\n" +
                    "Please fill all the required fields and submit again.";

            Assert.assertEquals(expected, result);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Test(expected = NullPointerException.class)
    public void TestEmptyFieldNullJSON() {

        this.accountModel = new AccountModel(null);
        this.accountModel.FieldEmpty();
    }

    @Test
    public void TestFieldInAccountContainInvalidCharacters(){

        try {
            this.jsonObject.put("username", "dsad^&*^& &^*dsakdsbha");
            this.accountModel = new AccountModel(this.jsonObject);
            this.accountModel.FieldContainsInvalidCharacters();

            String result = jsonObject.get("message").toString();
            String expected = "One or more fields contain invalid characters!\n" +
                    "Please fill all the required fields properly.";

            Assert.assertEquals(expected, result);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Test(expected = NullPointerException.class)
    public void TestNullJSONInCharacterCheck() {

        this.accountModel = new AccountModel(null);
        this.accountModel.FieldContainsInvalidCharacters();
    }

    @Test
    public void TestFieldInAccountContainWrightCharacterLength(){

        try {
            this.jsonObject.put("username", "ds");
            this.accountModel = new AccountModel(this.jsonObject);
            this.accountModel.FieldsContainTheWrightCharacterLength();

            String result = jsonObject.get("message").toString();
            String expected = "One or more fields contain less characters from" +
                    " the required length!\n Please fill all the fields properly.";

            Assert.assertEquals(expected, result);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Test(expected = NullPointerException.class)
    public void TestNullJSONInCharacterLengthCheck() {

        this.accountModel = new AccountModel(null);
        this.accountModel.FieldsContainTheWrightCharacterLength();
    }

    @Test
    public void TestPasswordAndRetypePasswordMismatch(){

        try {
            this.jsonObject.put("repassword", "slkdjsajkdhsak3hb4k3h");
            this.accountModel = new AccountModel(this.jsonObject);
            this.accountModel.PasswordAndRepasswordMismatch();

            String result = jsonObject.get("message").toString();
            String expected = "The password field and re-type password field mismatch!";

            Assert.assertEquals(expected, result);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Test(expected = NullPointerException.class)
    public void TestNullJSONInPasswordCheck() {

        this.accountModel = new AccountModel(null);
        this.accountModel.PasswordAndRepasswordMismatch();
    }


    @After
    public void TearDown(){
        this.accountModel = null;
        this.jsonObject = null;
    }

}
