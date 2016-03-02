package codebrains.crazysellout.TestSystem;

import android.content.Context;
import android.test.AndroidTestCase;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import codebrains.crazysellout.System.Coordinates;

/**
 * Test class that tests the methods inside the Coordinates java class.
 */
@RunWith(MockitoJUnitRunner.class)
public class CoordinatesTest extends AndroidTestCase{

    private Context context;
    private Coordinates coordinates;

    @Before
    public void Initialize(){
        context = this.getContext();

    }

    @Test(expected = Exception.class)
    public void TestCoordinatesContextNullParameter(){

        Coordinates coordinatesObj = new Coordinates(null);
    }

    @Test(timeout = 2000)
    public void TestCoordinatesAreSettedProperlyInTime(){
        coordinates = new Coordinates(this.context);
    }

    @Test
    public void TestLongitudeIsSet(){

        double longitude = coordinates.GetLatitude();
        Assert.assertNotNull(longitude);
    }

    @Test
    public void TestLatitudeIsSet(){

        double latitude = coordinates.GetLatitude();
        Assert.assertNotNull(latitude);
    }

    @Test
    public void TestCanGetCityFromCoordinates(){

        String city = coordinates.GetCityFromCoordinates();
        Assert.assertNotNull(city);
    }

    @After
    public void TearDown(){

    }

}
