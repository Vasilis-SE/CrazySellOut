package codebrains.crazysellout.AsyncTasks;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import codebrains.crazysellout.Activities.MainActivity;

/**
 * AsyncTask is a seperate thread than the thread that runs the GUI Any type of networking
 * should be done with asynctask.
 */
public class AttemptCreateAccount extends AsyncTask<String, String, String> {

        private ProgressDialog pDialog;
        private MainActivity mainActivity;

        //Constructor
        public AttemptCreateAccount(MainActivity act){
            this.mainActivity = act;
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
            return null;
        }

        /**
         * Method that will be called when the background process is complete.
         *
         * @param file_url
         */
        protected void onPostExecute(String file_url) {

        }


}
