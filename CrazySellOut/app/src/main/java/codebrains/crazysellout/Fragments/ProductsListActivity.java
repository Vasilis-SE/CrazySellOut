package codebrains.crazysellout.Fragments;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.Spinner;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.List;
import codebrains.crazysellout.Adapters.ExpandableListAdapter;
import codebrains.crazysellout.AsyncTasks.AttemptDisplayProducts;
import codebrains.crazysellout.Controllers.DisplayProductsController;
import codebrains.crazysellout.Interfaces.IAsyncResponse;
import codebrains.crazysellout.R;
import codebrains.crazysellout.System.SystemDialogs;

public class ProductsListActivity extends Fragment implements IAsyncResponse{

    private View view;
    private AttemptDisplayProducts asyncTask;
    private ExpandableListAdapter listAdapter;
    private ExpandableListView expListView;
    private HashMap<String, List<String>> listDataChild;
    private List<String> listDataHeader;

    private EditText sortingEdtText;
    private Spinner spinner;

    private JSONObject responseJSON;

    //Constructor
    public ProductsListActivity() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        this.view = inflater.inflate(R.layout.activity_products_list_avtivity, container, false);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);

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

        // get the listview
        expListView = (ExpandableListView) view.findViewById(R.id.lvExp);

    }

    /**
     * Interface method that retrieves the results from the async task.
     * @param output The result from the onPostExecute.
     */
    @Override
    public void ProcessFinish(String output) {

        try {

            this.responseJSON = new JSONObject(output);

            if(this.responseJSON.get("status") == false){
                SystemDialogs.DisplayInformationAlertBox(this.responseJSON.get("message").toString(),
                        "Products Message", (Activity) view.getContext());
            }
            else{
                DisplayProductsController dpc = new DisplayProductsController();
                dpc.SetProductListDataForDisplay((JSONArray) this.responseJSON.get("message"));
                this.listDataChild = dpc.GetListOfProducts();
                this.listDataHeader = dpc.GetListOfHeaders();

                listAdapter = new ExpandableListAdapter(view.getContext(), listDataHeader, listDataChild);

                // setting list adapter
                expListView.setAdapter(listAdapter);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }


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


}
