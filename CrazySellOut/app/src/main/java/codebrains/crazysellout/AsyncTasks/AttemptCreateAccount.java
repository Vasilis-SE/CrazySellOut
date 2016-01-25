package codebrains.crazysellout.AsyncTasks;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;
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
import codebrains.crazysellout.System.Connectivity;
import codebrains.crazysellout.System.JSONParser;
import codebrains.crazysellout.System.Encryption;

/**
 * AsyncTask is a seperate thread than the thread that runs the GUI Any type of networking
 * should be done with asynctask.
 */
public class AttemptCreateAccount extends AsyncTask<String, String, String> {

        private ProgressDialog pDialog;
        private MainActivity mainActivity;
        private JSONObject newAccountJSON;
        private JSONParser jsonParser;


        //Constructor
        public AttemptCreateAccount(MainActivity act, JSONObject jObj){
            this.mainActivity = act;
            this.newAccountJSON = jObj;
            this.jsonParser = new JSONParser();
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            pDialog = new ProgressDialog(this.mainActivity);
            pDialog.setMessage("Attempting creating account...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        @Override
        protected String doInBackground(String... params) {

            try {

                String fullEmailAddress = this.newAccountJSON.get("emailName")+"@"+this.newAccountJSON.get("emailService");

                //Removing all the unnecessary data from JSON Object
                this.newAccountJSON.remove("repassword");
                this.newAccountJSON.remove("emailName");
                this.newAccountJSON.remove("emailService");
                this.newAccountJSON.remove("terms");
                this.newAccountJSON.remove("status");
                this.newAccountJSON.remove("message");

                this.newAccountJSON.put("emailAddress", fullEmailAddress);

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
                parameters.add(new BasicNameValuePair("newAccountJSON", this.newAccountJSON.toString()));

                Log.e("mainToPost", "mainToPost" + parameters.toString());
                /*
                 * On server it must be :
                 * $jsonInput = $_POST['json'];
                 * json_decode($jsonInput);
                 */

                //Checking if the remote server is reachable by the application
                if(Connectivity.RemoteServerIsReachable(this.mainActivity)) {

                    // getting product details by making HTTP request
                    JSONObject json = this.jsonParser.makeHttpRequest("http://crazysellout.comule.com/CreateAccount.php",
                            "POST", parameters);


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
         * @param response
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
