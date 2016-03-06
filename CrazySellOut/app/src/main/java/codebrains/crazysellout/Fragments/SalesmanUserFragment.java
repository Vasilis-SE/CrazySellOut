package codebrains.crazysellout.Fragments;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import org.json.JSONException;
import org.json.JSONObject;
import codebrains.crazysellout.AsyncTasks.AttemptToRetrieveUserInfo;
import codebrains.crazysellout.Interfaces.IAsyncResponse;
import codebrains.crazysellout.R;
import codebrains.crazysellout.System.SystemDialogs;

import static codebrains.crazysellout.Activities.MainProducerActivity.GetUsername;

public class SalesmanUserFragment extends Fragment implements IAsyncResponse{

    private TextView accountTv;
    private EditText passwordEdt, retypeEdt, numberEdt, emailEdt;
    private RadioGroup sexRadioGroup;
    private ImageView imageView;

    private AttemptToRetrieveUserInfo asyncTask;

    //Constructor
    public SalesmanUserFragment(){
        //Empty Constructor needed
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_salesman_user_fragment, container, false);

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

        accountTv = (TextView) activity.findViewById(R.id.textView19);
        passwordEdt = (EditText) activity.findViewById(R.id.editText2);
        retypeEdt = (EditText) activity.findViewById(R.id.editText8);
        numberEdt = (EditText) activity.findViewById(R.id.editText9);
        emailEdt = (EditText) activity.findViewById(R.id.editText10);
        sexRadioGroup = (RadioGroup) activity.findViewById(R.id.sexRdGp);
        imageView = (ImageView) activity.findViewById(R.id.imageView);

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