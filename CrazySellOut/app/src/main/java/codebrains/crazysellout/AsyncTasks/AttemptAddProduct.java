package codebrains.crazysellout.AsyncTasks;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import codebrains.crazysellout.Activities.MainProducerActivity;
import codebrains.crazysellout.System.Connectivity;
import codebrains.crazysellout.System.JSONParser;

/**
 * Async class that handles the send and receive of data for the add product process.
 */
public class AttemptAddProduct extends AsyncTask<String, String, String> {

    private ProgressDialog pDialog;
    private Activity mActivity;
    private JSONObject productJSON;
    private JSONParser jsonParser;


    //Constructor
    public AttemptAddProduct(Activity act, JSONObject jObj){
        this.mActivity = act;
        this.productJSON = jObj;
        this.jsonParser = new JSONParser();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        pDialog = new ProgressDialog(this.mActivity);
        pDialog.setMessage("Attempting to add new product...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(true);
        pDialog.show();
    }

    @Override
    protected String doInBackground(String... params) {

        this.productJSON.remove("status");
        this.productJSON.remove("message");

        // Building Parameters
        List<NameValuePair> parameters = new ArrayList<NameValuePair>();
        parameters.add(new BasicNameValuePair("addProductJSON", this.productJSON.toString()));

        //Checking if the remote server is reachable by the application
        if(Connectivity.RemoteServerIsReachable(this.mActivity)) {

            // Getting product details by making HTTP request
            JSONObject json = this.jsonParser.makeHttpRequest("http://crazysellout.comule.com/AddProduct.php",
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

        // display the response from the server.
        if (response != null){
            Toast.makeText(this.mActivity, response, Toast.LENGTH_LONG).show();
        }


    }
}
