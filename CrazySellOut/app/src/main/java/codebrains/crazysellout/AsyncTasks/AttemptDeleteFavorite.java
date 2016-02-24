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

import codebrains.crazysellout.Activities.MainActivity;
import codebrains.crazysellout.Fragments.AddItemsFragment;
import codebrains.crazysellout.System.Connectivity;
import codebrains.crazysellout.System.Encryption;
import codebrains.crazysellout.System.JSONParser;

/**
 * Created by Vasilhs on 2/24/2016.
 */
public class AttemptDeleteFavorite extends AsyncTask<String, String, String> {


    private ProgressDialog pDialog;
    private Activity mActivity;
    private JSONObject selectedFavJSON;
    private JSONParser jsonParser;

    //Constructor
    public AttemptDeleteFavorite(Activity act, JSONObject jObj){
        this.mActivity = act;
        this.selectedFavJSON = jObj;
        this.jsonParser = new JSONParser();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        pDialog = new ProgressDialog(this.mActivity);
        pDialog.setMessage("Deleting Favorite...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(true);
        pDialog.show();
    }

    @Override
    protected String doInBackground(String... params) {

        // Building Parameters
        List<NameValuePair> parameters = new ArrayList<NameValuePair>();
        parameters.add(new BasicNameValuePair("selectedFavJSON", this.selectedFavJSON.toString()));

        //Checking if the remote server is reachable by the application
        if(Connectivity.RemoteServerIsReachable(this.mActivity)) {

            // Getting product details by making HTTP request
            JSONObject json = this.jsonParser.makeHttpRequest("http://crazysellout.comule.com/DeleteFavorite.php",
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
