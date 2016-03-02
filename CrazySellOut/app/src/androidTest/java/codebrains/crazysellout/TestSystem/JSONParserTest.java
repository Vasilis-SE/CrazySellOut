package codebrains.crazysellout.TestSystem;

import junit.framework.Assert;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import codebrains.crazysellout.System.JSONParser;

/**
 * Test class that test all the methods inside the JSONParser java class.
 */
public class JSONParserTest {

    private JSONParser jsonParser;
    private JSONObject jsonObject;

    @Before
    public void Initialize(){

        jsonParser = new JSONParser();
        jsonObject = new JSONObject();

        try {
            jsonObject.put("username", "e27fa9cc2dea604047933aae3ed2294713f30649");
            jsonObject.put("password", "2a79f3771181c1e90381c13bca2e4abbfff2d42a");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void TestMakeHttpRequestMethodsWorksProperly(){

        // Building Parameters
        List<NameValuePair> parameters = new ArrayList<NameValuePair>();
        parameters.add(new BasicNameValuePair("loginJSON", this.jsonObject.toString()));

        JSONObject result = this.jsonParser.makeHttpRequest("http://crazysellout.comule.com/LoginAccount.php",
                "POST", parameters);

        Assert.assertNotNull(result);
    }

    @Test(timeout = 4000) //4 seconds timeout
    public void TestMakeHttpRequestMethodGetsResultsInTime(){

        // Building Parameters
        List<NameValuePair> parameters = new ArrayList<NameValuePair>();
        parameters.add(new BasicNameValuePair("loginJSON", this.jsonObject.toString()));

        JSONObject result = this.jsonParser.makeHttpRequest("http://crazysellout.comule.com/LoginAccount.php",
                "POST", parameters);
    }

    @Test
    public void TestMakeHttpRequestReturnsTheCorrectResults(){

        try {
            jsonObject.put("password", "sadjsaodsajdklsajdlsk");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // Building Parameters
        List<NameValuePair> parameters = new ArrayList<NameValuePair>();
        parameters.add(new BasicNameValuePair("loginJSON", this.jsonObject.toString()));

        JSONObject result = this.jsonParser.makeHttpRequest("http://crazysellout.comule.com/LoginAccount.php",
                "POST", parameters);

        try {
            Assert.assertEquals("The account does not exist! ", result.get("message").toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Test(expected = NullPointerException.class)
    public void TestMakeHttpRequestNullPointerException(){
        // Building Parameters
        List<NameValuePair> parameters = new ArrayList<NameValuePair>();
        parameters.add(new BasicNameValuePair("loginJSON", this.jsonObject.toString()));

        JSONObject result = this.jsonParser.makeHttpRequest(null, "POST", parameters);
    }


    @After
    public void TearDown(){
        this.jsonParser = null;
        this.jsonObject = null;
    }

}
