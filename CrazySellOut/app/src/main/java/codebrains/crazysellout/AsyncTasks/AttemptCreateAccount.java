package codebrains.crazysellout.AsyncTasks;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;
import codebrains.crazysellout.Activities.MainActivity;
import codebrains.crazysellout.Models.JSONParser;

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

            // Check for success tag
            int success;

            try {
                // Building Parameters
                List<NameValuePair> parameters = new ArrayList<NameValuePair>();
                parameters.add(new BasicNameValuePair("newAccountJSON", this.newAccountJSON.toString()));

                /*
                 * On server it must be :
                 * $jsonInput = $_POST['json'];
                 * json_decode($jsonInput);
                 */

                // getting product details by making HTTP request
                JSONObject json = this.jsonParser.makeHttpRequest("http://crazysellout.comule.com/test.php",
                        "POST", parameters);

                // json success tag
                success = json.getInt(TAG_SUCCESS);

                if (success == 1) {
                    /*
                    Intent i = new Intent(Login.this, ReadComments.class);
                    finish();
                    startActivity(i);
                    return json.getString(TAG_MESSAGE);
                    */
                }else{
                    return json.getString(TAG_MESSAGE);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        /**
         * Method that will be called when the background process is complete.
         *
         * @param file_url
         */
        protected void onPostExecute(String file_url) {

            // dismiss the dialog once product deleted
            pDialog.dismiss();

        }


}
