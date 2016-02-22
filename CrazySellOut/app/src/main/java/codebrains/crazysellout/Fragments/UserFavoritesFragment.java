package codebrains.crazysellout.Fragments;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import org.json.JSONException;
import org.json.JSONObject;

import codebrains.crazysellout.Activities.MainProducerActivity;
import codebrains.crazysellout.AsyncTasks.AttemptDisplayFavorites;
import codebrains.crazysellout.AsyncTasks.AttemptDisplayUserProducts;
import codebrains.crazysellout.Interfaces.IAsyncResponse;
import codebrains.crazysellout.R;

import static codebrains.crazysellout.Activities.MainUserActivity.GetUsername;

public class UserFavoritesFragment extends Fragment implements IAsyncResponse {

    private ListView listView;
    private View view;
    private AttemptDisplayFavorites asyncTask;

    private String response;

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

        return view;
    }


    @Override
    public void ProcessFinish(String output, Activity activity) {

    }



}
