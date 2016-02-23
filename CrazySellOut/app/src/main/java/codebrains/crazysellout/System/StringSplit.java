package codebrains.crazysellout.System;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vasilhs on 2/23/2016.
 */
public class StringSplit {

    //Constructor
    public StringSplit(){

    }

    /**
     * Method that splits a string into lines.
     * @param string The string to be splitted.
     * @return Returns an array of string, each row is a line of the string.
     */
    private static String[] SplitStringIntoLines(String string){

        String[] stringInLines = string.split("\n");
        return stringInLines;
    }

    /**
     * Method that handles the split process of the selected favorite item from the list of favorites.
     * @param string The string to be splitted.
     * @return Returns a json object with the values of the selected item.
     * @throws JSONException Exception that occurs when there is a json error.
     */
    public static JSONObject FavoriteListSelectedItemSplit(String string) throws JSONException {

        JSONObject jsonObject = new JSONObject();
        String[] stringInLines = StringSplit.SplitStringIntoLines(string);
        List<String> valuesList = new ArrayList<String>();

        for(int i=0; i<stringInLines.length; i++){

            String[] lineInParts = stringInLines[i].split(":");
            valuesList.add(lineInParts[1].trim());
        }

        jsonObject.put("productName", valuesList.get(0));
        jsonObject.put("storeName", valuesList.get(1));
        jsonObject.put("price", valuesList.get(2));
        jsonObject.put("expireDate", valuesList.get(3) );

        return jsonObject;
    }



}
