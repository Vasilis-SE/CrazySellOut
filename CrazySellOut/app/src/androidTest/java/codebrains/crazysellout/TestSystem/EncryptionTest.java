package codebrains.crazysellout.TestSystem;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import codebrains.crazysellout.System.Encryption;

/**
 * Test class that tests all the methods inside the Encryption java class.
 */
public class EncryptionTest {

    private Encryption encryption;

    @Before
    public void Initialize(){
        encryption = new Encryption();
    }

    @Test
    public void TestSHA1EncryptionReturnsNotNullResult(){

        try {
            String result = encryption.SHA1Encryption("thisIsAMessageToBeEncrypted");
            Assert.assertNotNull(result);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @Test(timeout = 2000) //2 seconds time out
    public void TestSHA1EncryptionConvertsInTime(){

        try {
            String result = encryption.SHA1Encryption("thisIsAMessageToBeEncryptedAndItsALongMessageToBeEncrypted");
            Assert.assertNotNull(result);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void TestSHA1EncryptionCorrectResult(){

        try {
            String result = encryption.SHA1Encryption("thisIsAMessage");
            String expected = "03bde771f25d9cc96f7fdd2695e218f280172ea1";
            Assert.assertEquals(expected, result);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void TearDown(){
        this.encryption = null;
    }

}
