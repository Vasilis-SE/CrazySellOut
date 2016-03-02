package codebrains.crazysellout.TestSystem;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import codebrains.crazysellout.System.ServerAnalytics;

/**
 * Test class that test all the methods inside the Analytics java class.
 */
public class ServerAnalyticsTest {

    private ServerAnalytics serverAnalytics;

    @Before
    public void Initialize(){
        this.serverAnalytics = new ServerAnalytics();
    }

    @Test
    public void TestServerAnalyticsRemovalWorksCorrectly1(){

        String result = this.serverAnalytics.RemoveServerAnalyticsFromResponse("$%@message");
        String expected = "message";
        Assert.assertEquals(expected, result);
    }

    @Test
    public void TestServerAnalyticsRemovalWorksCorrectly2(){

        String result = this.serverAnalytics.RemoveServerAnalyticsFromResponse("&@(@*message");
        String expected = "@*message";
        Assert.assertEquals(expected, result);
    }

    @Test(expected = NullPointerException.class)
    public void TestServerAnalyticsRemovalNullParameter(){

        String result = this.serverAnalytics.RemoveServerAnalyticsFromResponse(null);
    }

    @After
    public void TearDown(){
        this.serverAnalytics = null;
    }

}
