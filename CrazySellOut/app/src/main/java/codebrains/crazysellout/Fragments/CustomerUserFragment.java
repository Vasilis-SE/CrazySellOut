package codebrains.crazysellout.Fragments;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import org.json.JSONException;
import org.json.JSONObject;
import codebrains.crazysellout.Activities.MainUserActivity;
import codebrains.crazysellout.AsyncTasks.AttemptToDeleteAccount;
import codebrains.crazysellout.AsyncTasks.AttemptToRetrieveUserInfo;
import codebrains.crazysellout.AsyncTasks.AttemptUpdateAccount;
import codebrains.crazysellout.Controllers.UpdateUserProfileController;
import codebrains.crazysellout.Interfaces.IAsyncResponse;
import codebrains.crazysellout.R;
import codebrains.crazysellout.System.SystemDialogs;

import static codebrains.crazysellout.Activities.MainUserActivity.GetUsername;


public class CustomerUserFragment extends Fragment implements IAsyncResponse {

    private TextView accountTv;
    private static EditText passwordEdt, retypeEdt, numberEdt, emailEdt;
    private static RadioGroup sexRadioGroup;
    private ImageView imageView;

    private AttemptToRetrieveUserInfo asyncTask;
    private AttemptUpdateAccount asyncTaskUpdated;
    private AttemptToDeleteAccount asyncAccountDelete;

    //Constructor
    public CustomerUserFragment(){
        //Empty Constructor needed
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_customer_user_fragment, container, false);

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("username", GetUsername());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        asyncTask = new AttemptToRetrieveUserInfo((Activity) view.getContext(), jsonObject);
        asyncTask.delegate = this;
        asyncTask.execute();

        return view;
    }

    /**
     * Method that handles the display of user info into the user profile from the response of the
     * server.
     * @param jsonObject The json object that contains all the user info needed.
     * @param activity The activity object that called the method.
     */
    private void DisplayUserInfoIntoProfile(JSONObject jsonObject, Activity activity){

        accountTv = (TextView) activity.findViewById(R.id.customerAccountTv);
        passwordEdt = (EditText) activity.findViewById(R.id.customerPasswordEdt);
        retypeEdt = (EditText) activity.findViewById(R.id.customerRetypePassEdt);
        numberEdt = (EditText) activity.findViewById(R.id.customerNumberEdt);
        emailEdt = (EditText) activity.findViewById(R.id.customerEmailEdt);
        sexRadioGroup = (RadioGroup) activity.findViewById(R.id.customerSexRdGp);
        imageView = (ImageView) activity.findViewById(R.id.customerImageView);

        try {

            accountTv.setText(jsonObject.get("username").toString());
            passwordEdt.setText(jsonObject.get("password").toString());
            numberEdt.setText(jsonObject.get("number").toString());
            emailEdt.setText(jsonObject.get("email").toString());

            if(jsonObject.get("sex").equals("Male")){
                sexRadioGroup.check(R.id.maleRadioBtn);
                imageView.setBackgroundResource(R.drawable.male_profile_image);
            }
            else {
                sexRadioGroup.check(R.id.femaleRadioBtn);
                imageView.setBackgroundResource(R.drawable.female_profile_image);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    /**
     * Event on click that occurs when the update button on the profile of the user is pressed.
     * @param view The view of the activity that fired the event.
     */
    public void UpdateCustomerProfileProcess(View view){

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("username", MainUserActivity.GetUsername());
            jsonObject.put("password", passwordEdt.getText().toString().trim());
            jsonObject.put("retypePassword", retypeEdt.getText().toString());
            jsonObject.put("number", numberEdt.getText().toString().trim());
            jsonObject.put("email", emailEdt.getText().toString().trim());

            int radioButtonID = this.sexRadioGroup.getCheckedRadioButtonId();
            View radioButton = this.sexRadioGroup.findViewById(radioButtonID);
            int idx = this.sexRadioGroup.indexOfChild(radioButton);
            RadioButton sexRadioButton = (RadioButton) this.sexRadioGroup.getChildAt(idx);
            jsonObject.put("sex", sexRadioButton.getText().toString());
            jsonObject.put("status", true);
            jsonObject.put("message", "");

            if(retypeEdt.getText().toString().equals(""))
                jsonObject.put("passwordChange", false);
            else
                jsonObject.put("passwordChange", true);

            String[] array = view.getResources().getStringArray(R.array.items);

            UpdateUserProfileController uupc = new UpdateUserProfileController();
            uupc.UserProfileUpdateControlMethod(jsonObject, array);

            if(jsonObject.get("status") == false){
                SystemDialogs.DisplayInformationAlertBox(jsonObject.get("message").toString(),
                        "Update Dialog", (Activity) view.getContext());
            }
            else {
                asyncTaskUpdated = new AttemptUpdateAccount((Activity) view.getContext(), jsonObject);
                asyncTaskUpdated.execute();
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    /**
     * Event on click that occurs when the delete button on the profile of the user is pressed.
     * @param view The view of the activity that fired the event.
     */
    public void DeleteCustomerAccountProcess(final View view){

        new AlertDialog.Builder(view.getContext())
            .setMessage("Are you sure you want to delete your account ?")
            .setCancelable(false)
            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {

                    JSONObject jsonObject = new JSONObject();
                    try {
                        jsonObject.put("username", MainUserActivity.GetUsername());
                        jsonObject.put("type", "customer");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    asyncAccountDelete = new AttemptToDeleteAccount((Activity) view.getContext(), jsonObject);
                    asyncAccountDelete.execute();

                }
            })
            .setNegativeButton("No", null)
            .show();
    }

    @Override
    public void ProcessFinish(String output, Activity activity) {

        try {
            JSONObject jsonObject = new JSONObject(output);

            if(jsonObject.get("status") == false){
                SystemDialogs.DisplayInformationAlertBox(jsonObject.get("message").toString(),
                        "User Profile Dialog", activity);
            }
            else{
                DisplayUserInfoIntoProfile(jsonObject, activity);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


}
