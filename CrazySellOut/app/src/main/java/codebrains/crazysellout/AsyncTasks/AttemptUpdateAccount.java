package codebrains.crazysellout.AsyncTasks;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.widget.Toast;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import codebrains.crazysellout.System.Connectivity;
import codebrains.crazysellout.System.Encryption;
import codebrains.crazysellout.System.JSONParser;

/**
 * Async class that handles the update of user's account.
 */
public class AttemptUpdateAccount extends AsyncTask<String, String, String> {

    private ProgressDialog pDialog;
    private Activity mActivity;
    private JSONObject accountJSON;
    private JSONParser jsonParser;

    //Constructor
    public AttemptUpdateAccount(Activity act, JSONObject jObj){
        this.mActivity = act;
        this.accountJSON = jObj;
        this.jsonParser = new JSONParser();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        pDialog = new ProgressDialog(this.mActivity);
        pDialog.setMessage("Updating Account...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(true);
        pDialog.show();
    }

    @Override
    protected String doInBackground(String... params) {

        accountJSON.remove("retypePassword");
        accountJSON.remove("message");
        accountJSON.remove("status");

        try {
            accountJSON.put("password", Encryption.SHA1Encryption(accountJSON.get("password").toString()));
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        // Building Parameters
        List<NameValuePair> parameters = new ArrayList<NameValuePair>();
        parameters.add(new BasicNameValuePair("accountJSON", this.accountJSON.toString()));

        //Checking if the remote server is reachable by the application
        if(Connectivity.RemoteServerIsReachable(this.mActivity)) {

            // Getting product details by making HTTP request
            JSONObject json = this.jsonParser.makeHttpRequest("http://crazysellout.comule.com/UpdateAccount.php",
                    "POST", parameters);

            try {
                return json.get("message").toString();
            } catch (JSONException e) {
                e.printStackTrace();
            }
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

        Toast.makeText(this.mActivity, response, Toast.LENGTH_LONG).show();
    }

}
