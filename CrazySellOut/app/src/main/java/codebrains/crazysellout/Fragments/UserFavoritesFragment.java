package codebrains.crazysellout.Fragments;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

import codebrains.crazysellout.AsyncTasks.AttemptDeleteFavorite;
import codebrains.crazysellout.AsyncTasks.AttemptDisplayFavorites;
import codebrains.crazysellout.Controllers.DisplayFavoritesController;
import codebrains.crazysellout.Interfaces.IAsyncResponse;
import codebrains.crazysellout.R;
import codebrains.crazysellout.System.StringSplit;
import codebrains.crazysellout.System.SystemDialogs;

import static codebrains.crazysellout.Activities.MainUserActivity.GetUsername;

public class UserFavoritesFragment extends Fragment implements IAsyncResponse {

    private ListView listView;
    private View view;
    private AttemptDisplayFavorites asyncTask;

    private String response;
    private static String selectedItem;
    private int previousPosition;
    private View previousView;

    //Constructor
    public UserFavoritesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_user_favorites_fragment, container, false);

        this.view = view;
        this.listView = (ListView) this.view.findViewById(R.id.listView2);
        selectedItem = null;
        previousPosition = -1;

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("username", GetUsername());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        asyncTask = new AttemptDisplayFavorites((Activity) this.view.getContext(), jsonObject);
        asyncTask.delegate = this;
        asyncTask.execute();

        //List view click item listener.
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                view.setBackgroundColor(getResources().getColor(R.color.listItemSelected));
                selectedItem = parent.getItemAtPosition(position).toString();

                if(position != previousPosition && previousPosition != -1){
                    previousView.setBackgroundColor(getResources().getColor(R.color.transparentBackground));
                }

                previousPosition = position;
                previousView = view;
            }
        });

        return view;
    }


    @Override
    public void ProcessFinish(String output, Activity activity) {

        response = output;
        this.ProcessOutput(response, activity);

    }

    /**
     * Method that processes the response from the database to be display to the user.
     * @param response The response of the database in string format.
     * @param activity The activity that called the method.
     */
    private void ProcessOutput(String response, Activity activity){

        DisplayFavoritesController dfc = new DisplayFavoritesController(response);
        ArrayList<String> arrayList = dfc.SetFavoritesListForDisplay();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(activity, R.layout.favorites_list_row, arrayList);
        if(listView == null)
            listView = (ListView) activity.findViewById(R.id.listView2);

        listView.setAdapter(adapter);
    }

    /**
     * Event on click that occurs whenever the refresh button on the favorite list tab is pressed.
     * @param view The view of the activity that fired the event.
     */
    public void RefreshFavoriteListProcess(View view){

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("username", GetUsername());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        asyncTask = new AttemptDisplayFavorites((Activity) view.getContext(), jsonObject);
        asyncTask.delegate = this;
        asyncTask.execute();
    }

    /**
     * Event on click that occurs whenever the delete button on the favorite list tab is pressed.
     * @param view The view of the activity that fired the event.
     */
    public void DeleteFavoriteFromListProcess(View view){

        if(selectedItem == null){
            SystemDialogs.DisplayInformationAlertBox("You haven't selected any item from the list " +
                    "to be deleted!", "Delete Favorite", (Activity) view.getContext());
        }
        else {

            try {
                JSONObject jsonObject = StringSplit.FavoriteListSelectedItemSplit(selectedItem);
                jsonObject.put("username", GetUsername());
                Log.d("Item Selected JSON Obj ------ ", jsonObject.toString());

                new AttemptDeleteFavorite((Activity) view.getContext(), jsonObject).execute();

                //Refresh the list after deletion
                this.RefreshFavoriteListProcess(view);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }

}
