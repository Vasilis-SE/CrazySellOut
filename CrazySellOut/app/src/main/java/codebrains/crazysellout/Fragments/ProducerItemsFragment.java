package codebrains.crazysellout.Fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import codebrains.crazysellout.Activities.MainProducerActivity;
import codebrains.crazysellout.AsyncTasks.AttemptDisplayUserProducts;
import codebrains.crazysellout.Controllers.DisplayProductsController;
import codebrains.crazysellout.Interfaces.IAsyncResponse;
import codebrains.crazysellout.R;


public class ProducerItemsFragment extends Fragment implements IAsyncResponse{

    private ListView listView;
    private View view;
    private AttemptDisplayUserProducts asyncTask;

    private String response;

    //Constructor
    public ProducerItemsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_producer_items_fragment, container, false);
        this.view = view;

        this.listView = (ListView) this.view.findViewById(R.id.listView);

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("username", MainProducerActivity.GetUsername());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        asyncTask = new AttemptDisplayUserProducts((Activity) this.view.getContext(), jsonObject);
        asyncTask.delegate = this;
        asyncTask.execute();

        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    /**
     * Method that controls the display of user products to the user by setting the lis view item on
     * the activity.
     * @param response The string response of the server.
     */
    private void ProcessOutput(String response){

        DisplayProductsController dpc = new DisplayProductsController();
        ArrayList<String> list = dpc.SetListOfUserProductsForDisplay(response, (Activity) this.view.getContext());

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getContext(), R.layout.simpe_list_row_item, list);
        Html.fromHtml(adapter.toString());
        this.listView.setAdapter(adapter);

    }

    public void SetResponseFromServer(String response){
        this.response = response;
    }


    @Override
    public void ProcessFinish(String output) {

        response = output;
        this.ProcessOutput(response);
    }
}
