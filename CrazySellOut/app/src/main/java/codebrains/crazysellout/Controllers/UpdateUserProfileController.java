package codebrains.crazysellout.Controllers;

import org.json.JSONException;
import org.json.JSONObject;
import codebrains.crazysellout.Models.UserProfileUpdateModel;

/**
 * Control class that handles the check process of the update user profile.
 */
public class UpdateUserProfileController {

    //Constructor
    public UpdateUserProfileController(){

    }

    /**
     * Method that controls the update of user's profile.
     */
    public void UserProfileUpdateControlMethod(JSONObject jsonObject, String[] serviceArray) throws JSONException {

        UserProfileUpdateModel upum = new UserProfileUpdateModel();
        upum.EmailFieldContainsMailServicePart(jsonObject, serviceArray);
        upum.FieldsAreInTheProperRangeOfCharacters(jsonObject);
        upum.NewPasswordAndRetypedPasswordIsTheSame(jsonObject);


    }


}
