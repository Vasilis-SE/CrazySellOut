package codebrains.crazysellout.AsyncTasks;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Switch;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import codebrains.crazysellout.Activities.MainActivity;
import codebrains.crazysellout.Activities.MainUserActivity;
import codebrains.crazysellout.System.Connectivity;
import codebrains.crazysellout.System.Encryption;
import codebrains.crazysellout.System.JSONParser;

/**
 * Created by Vasilhs on 1/27/2016.
 */
public class AttemptLogin extends AsyncTask<String, String, String> {

    private ProgressDialog pDialog;
    private MainActivity mainActivity;
    private JSONObject newAccountJSON;
    private JSONParser jsonParser;

    //Constructor
    public AttemptLogin(MainActivity act, JSONObject jObj){
        this.mainActivity = act;
        this.newAccountJSON = jObj;
        this.jsonParser = new JSONParser();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        pDialog = new ProgressDialog(this.mainActivity);
        pDialog.setMessage("Attempting login...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(true);
        pDialog.show();
    }

    @Override
    protected String doInBackground(String... params) {

        try {

            this.newAccountJSON.remove("status");
            this.newAccountJSON.remove("message");

            try {
                //Encrypt the password and the username.
                this.newAccountJSON.put("username", Encryption.SHA1Encryption(
                        this.newAccountJSON.get("username").toString()));
                this.newAccountJSON.put("password", Encryption.SHA1Encryption(
                        this.newAccountJSON.get("password").toString()));

            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

            // Building Parameters
            List<NameValuePair> parameters = new ArrayList<NameValuePair>();
            parameters.add(new BasicNameValuePair("loginJSON", this.newAccountJSON.toString()));

            //Checking if the remote server is reachable by the application
            if(Connectivity.RemoteServerIsReachable(this.mainActivity)) {

                // Getting product details by making HTTP request
                JSONObject json = this.jsonParser.makeHttpRequest("http://crazysellout.comule.com/LoginAccount.php",
                        "POST", parameters);


                if(json.get("success") == 1){

                    Intent i = null;
                    switch(json.get("message").toString()){
                        case "Consumer":
                            i = new Intent(this.mainActivity, MainUserActivity.class);
                        break;
                        case "Producer":
                            //TODO: to implement the opening of producer interface.
                        break;

                    }
                    this.mainActivity.finish();
                    this.mainActivity.startActivity(i);
                }
                else
                    return json.get("message").toString();


            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Method that will be called when the background process is complete.
     *
     * @param response The response from the server that doInBackground method retrieved.
     */
    protected void onPostExecute(String response) {

        // dismiss the dialog once product deleted
        pDialog.dismiss();

        // display the response from the server.
        if (response != null){
            Toast.makeText(this.mainActivity, response, Toast.LENGTH_LONG).show();
        }


    }
}
