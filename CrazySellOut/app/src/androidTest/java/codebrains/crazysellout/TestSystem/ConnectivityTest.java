package codebrains.crazysellout.TestSystem;

import android.app.Activity;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import codebrains.crazysellout.System.Connectivity;

import static junit.framework.Assert.assertTrue;

/**
 * Created by Vasilhs on 2/29/2016.
 */

@RunWith(MockitoJUnitRunner.class)
public class ConnectivityTest {

    @Before
    public void Initialize(){

    }

    @Test
    public void TestServerIsReachable(){

        Connectivity connectivity = new Connectivity();
        boolean result = connectivity.RemoteServerIsReachable(null);

        assertTrue(result);
    }

    @Test(expected = NullPointerException.class)
    public void TestWifiNotEnabled(){

        Connectivity connectivity = new Connectivity();
        connectivity.WifiEnabledCheck(null);
    }

    @Test(expected = ClassCastException.class)
    public void TestActivityObjectInvalidInWifiEnabled(){

        Connectivity connectivity = new Connectivity();
        connectivity.WifiEnabledCheck((Activity) new Object());
    }


    @After
    public void TearDown(){

    }


}
