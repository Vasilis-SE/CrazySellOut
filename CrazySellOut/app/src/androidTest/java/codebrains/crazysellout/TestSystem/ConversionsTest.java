package codebrains.crazysellout.TestSystem;

import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import codebrains.crazysellout.System.Conversions;

/**
 * Test class that tests all the methods inside the Conversions java class.
 */
@RunWith(MockitoJUnitRunner.class)
public class ConversionsTest {

    private Conversions conversions;

    @Before
    public void Initialize(){

        conversions = new Conversions();
    }

    @Test(expected = NullPointerException.class)
    public void TestStringToDoubleNullParameter(){

        conversions.ConvertStringToDouble(null);
    }

    @Test(expected = NullPointerException.class)
    public void TestDoubleToStringNullParameter(){

        conversions.ConvertDoubleToString(Double.parseDouble(null));
    }

    @Test(expected = NullPointerException.class)
     public void TestStringToIntegerNullParameter(){

        conversions.ConvertStringToInteger(null);
    }

    @Test(expected = NullPointerException.class)
    public void TestIntegerToStringNullParameter(){

        conversions.ConvertIntegerToString(Integer.parseInt(null));
    }

    @Test
    public void TestConvertStringToDoubleResult(){

        double result = conversions.ConvertStringToDouble("3");
        Assert.assertEquals(result, 3.0);
    }

    @Test
    public void TestConvertDoubleToStringResult(){

        String result = conversions.ConvertDoubleToString(5.0);
        Assert.assertEquals(result, "5.0");
    }

    @Test
    public void TestConvertStringToIntegerResult(){

        int result = conversions.ConvertStringToInteger("5");
        Assert.assertEquals(result, 5);
    }

    @Test
    public void TestConvertIntegerToStringResult(){

        String result = conversions.ConvertIntegerToString(8);
        Assert.assertEquals(result, "8");
    }



    @After
    public void TearDown(){
        conversions = null;
    }




}
