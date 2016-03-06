package codebrains.crazysellout.AsyncTasks;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import codebrains.crazysellout.Interfaces.IAsyncResponse;
import codebrains.crazysellout.System.Connectivity;
import codebrains.crazysellout.System.JSONParser;

/**
 * Async class that handles the send / get of data from server for the purpose of displaying the
 * user info to the user's profile.
 */
public class AttemptToRetrieveUserInfo extends AsyncTask<String, String, String> {

    private ProgressDialog pDialog;
    private Activity mActivity;
    private JSONObject userInfoJSON;
    private JSONParser jsonParser;

    public IAsyncResponse delegate = null;

    //Constructor
    public AttemptToRetrieveUserInfo(Activity act, JSONObject jObj){
        this.mActivity = act;
        this.userInfoJSON = jObj;
        this.jsonParser = new JSONParser();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        pDialog = new ProgressDialog(this.mActivity);
        pDialog.setMessage("Retrieving Data...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(true);
        pDialog.show();
    }

    @Override
    protected String doInBackground(String... params) {


        // Building Parameters
        List<NameValuePair> parameters = new ArrayList<NameValuePair>();
        parameters.add(new BasicNameValuePair("userInfoJSON", this.userInfoJSON.toString()));

        //Checking if the remote server is reachable by the application
        if(Connectivity.RemoteServerIsReachable(this.mActivity)) {

            // Getting product details by making HTTP request
            JSONObject json = this.jsonParser.makeHttpRequest("http://crazysellout.comule.com/UserDataRetrieve.php",
                    "POST", parameters);

            return json.toString();
        }

        return null;
    }

    /**
     * Method that will be called when the background process is complete.
     *
     * @param response The response from the server that doInBackground method retrieved.
     */
    public void onPostExecute(String response) {

        //Sets as an output to the interface the response of the server.
        delegate.ProcessFinish(response, mActivity);

        pDialog.dismiss();

    }

}
