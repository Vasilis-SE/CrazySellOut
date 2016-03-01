package codebrains.crazysellout.TestSystem;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import codebrains.crazysellout.System.Conversions;

/**
 * Created by Vasilhs on 3/1/2016.
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

        conversions = new Conversions();
        conversions.ConvertStringToDouble(null);
    }

    @Test(expected = NullPointerException.class)
    public void TestDoubleToStringNullParameter(){

        conversions = new Conversions();
        conversions.ConvertDoubleToString(Double.parseDouble(null));
    }

    @Test(expected = NullPointerException.class)
     public void TestStringToIntegerNullParameter(){

        conversions = new Conversions();
        conversions.ConvertStringToInteger(null);
    }

    @Test(expected = NullPointerException.class)
    public void TestIntegerToStringNullParameter(){

        conversions = new Conversions();
        conversions.ConvertIntegerToString(Integer.parseInt(null));
    }





    @After
    public void TearDown(){
        conversions = null;
    }




}
