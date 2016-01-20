package codebrains.crazysellout.System;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Vasilhs on 1/20/2016.
 */
public class Encryption {

    //Constructor
    public Encryption(){

    }

    /**
     * Method that converts the byte stream data that the SHA1Encryption method created and converts
     * it to string data type.
     *
     * @param data The stream byte data.
     * @return Returns the string representation of the stream to the SHA1Encryption method.
     */
    private static String ConvertToHex(byte[] data) {

        StringBuilder buf = new StringBuilder();

        for (byte b : data) {

            int halfbyte = (b >>> 4) & 0x0F;
            int two_halfs = 0;

            do {
                buf.append((0 <= halfbyte) && (halfbyte <= 9) ? (char) ('0' + halfbyte) : (char) ('a' + (halfbyte - 10)));
                halfbyte = b & 0x0F;
            } while (two_halfs++ < 1);
        }

        return buf.toString();
    }

    /**
     * Method that converts a string message into message digest SHA-1 encryption.
     *
     * @param text The string message to be encrypted.
     * @return Returns the encrypted message.
     * @throws NoSuchAlgorithmException Exception that occurs when the SHA-1 instance doesn't exist.
     * @throws UnsupportedEncodingException Exception that occurs when the encoding method is not supported
     */
    public static String SHA1Encryption(String text) throws NoSuchAlgorithmException, UnsupportedEncodingException {

        MessageDigest md = MessageDigest.getInstance("SHA-1");
        md.update(text.getBytes("iso-8859-1"), 0, text.length());
        byte[] sha1hash = md.digest();

        return ConvertToHex(sha1hash);
    }

}
