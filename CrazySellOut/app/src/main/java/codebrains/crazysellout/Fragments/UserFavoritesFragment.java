package codebrains.crazysellout.Fragments;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import codebrains.crazysellout.AsyncTasks.AttemptDisplayFavorites;
import codebrains.crazysellout.Controllers.DisplayFavoritesController;
import codebrains.crazysellout.Interfaces.IAsyncResponse;
import codebrains.crazysellout.R;

import static codebrains.crazysellout.Activities.MainUserActivity.GetUsername;

public class UserFavoritesFragment extends Fragment implements IAsyncResponse {

    private ListView listView;
    private View view;
    private AttemptDisplayFavorites asyncTask;

    private String response;
    private String selectedItem;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_user_favorites_fragment, container, false);

        this.view = view;
        this.listView = (ListView) this.view.findViewById(R.id.listView2);

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


}
