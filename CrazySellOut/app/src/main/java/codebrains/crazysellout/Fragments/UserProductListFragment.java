package codebrains.crazysellout.Fragments;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;

import codebrains.crazysellout.Adapters.ExpandableListAdapter;
import codebrains.crazysellout.Adapters.ProductsExpandableListAdapter;
import codebrains.crazysellout.AsyncTasks.AttemptDisplayProducts;
import codebrains.crazysellout.AsyncTasks.AttemptToAddFavorite;
import codebrains.crazysellout.Controllers.DisplayProductsController;
import codebrains.crazysellout.Interfaces.IAsyncResponse;
import codebrains.crazysellout.R;
import codebrains.crazysellout.System.SystemDialogs;

import static codebrains.crazysellout.Activities.MainUserActivity.GetUsername;

public class UserProductListFragment extends Fragment implements IAsyncResponse {

    private View view;
    private AttemptDisplayProducts asyncTask;
    private ProductsExpandableListAdapter listAdapter;
    private ExpandableListView expListView;
    private HashMap<String, List<String>> listDataChild;
    private List<String> listDataHeader;

    private EditText sortingEdtText;
    private Spinner spinner;

    private String response;

    //Constructor
    public UserProductListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        this.view = inflater.inflate(R.layout.activity_user_product_list_fragment, container, false);

        // get the listview
        expListView = (ExpandableListView) view.findViewById(R.id.lvExp);


        JSONObject jObj = new JSONObject();
        try {
            jObj.put("sortcategory", "All");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        asyncTask = new AttemptDisplayProducts((Activity) view.getContext(), jObj);

        //This to set delegate/listener back to this class
        asyncTask.delegate = this;
        asyncTask.execute();

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);
    }

    /**
     * Method that handles the display of the product items into the expandable list.
     * @param response The response string from the server.
     */
    private void ProcessOutput(String response){

        try {
            JSONObject responseJSON = new JSONObject(response);

            if(responseJSON.get("status") == false){
                SystemDialogs.DisplayInformationAlertBox(responseJSON.get("message").toString(),
                        "Products Message", (Activity) view.getContext());
            }
            else{
                DisplayProductsController dpc = new DisplayProductsController();
                dpc.SetProductListDataForDisplay((JSONArray) responseJSON.get("message"));
                this.listDataChild = dpc.GetListOfProducts();
                this.listDataHeader = dpc.GetListOfHeaders();

                listAdapter = new ProductsExpandableListAdapter(view.getContext(), listDataHeader, listDataChild);

                // setting list adapter
                expListView.setAdapter(listAdapter);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * Interface method that retrieves the results from the async task.
     * @param output The result from the onPostExecute.
     */
    @Override
    public void ProcessFinish(String output, Activity activity) {

        response = output;
        this.ProcessOutput(response);
    }

    /**
     * Method that handles the sorting of the product with the given sort text by the user.
     * @param activity The activity object of the parent class that called this method.
     */
    public void SortProductsEvent(View view, Activity activity){

        this.view = view;
        sortingEdtText = (EditText) activity.findViewById(R.id.editText);
        spinner = (Spinner) activity.findViewById(R.id.spinner3);

        String sortingText = sortingEdtText.getText().toString().trim();
        String sortingCategory = spinner.getSelectedItem().toString();

        JSONObject jObj = new JSONObject();
        try {
            jObj.put("sortcategory", sortingCategory);
            jObj.put("sortingText", sortingText);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if(sortingText.equals("") && !sortingCategory.equals("All")){

            SystemDialogs.DisplayInformationAlertBox("The sorting text field is empty! Please fill " +
                    "the specific field in order to sort the result with the parameter that you have " +
                    "given.", "Sorting Error", activity);
        }
        else {

            asyncTask = new AttemptDisplayProducts(activity, jObj);

            //This to set delegate/listener back to this class
            asyncTask.delegate = this;
            asyncTask.execute();

            // get the listview
            expListView = (ExpandableListView) activity.findViewById(R.id.lvExp);
        }

    }

    /**
     * Method that handles the add of favorites in the favorites table in database.
     * @param view The view of the activity that fired the event.
     * @param activity The activity that called the method.
     */
    public void AddProductToFavorites(View view, Activity activity){

        LinearLayout l1 = (LinearLayout) view.getParent();
        TextView tv1 = (TextView) l1.findViewById(R.id.lblListHeader);
        String headerText = tv1.getText().toString().trim();

        Log.d("header Text ---------- ", headerText);

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("productName", headerText);
            jsonObject.put("username", GetUsername());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        new AttemptToAddFavorite((Activity) view.getContext(), jsonObject).execute();

    }


}
