package codebrains.crazysellout.Fragments;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import codebrains.crazysellout.Interfaces.IAsyncResponse;
import codebrains.crazysellout.R;

public class CustomerUserFragment extends Fragment implements IAsyncResponse {

    //Constructor
    public CustomerUserFragment(){
        //Empty Constructor needed
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_customer_user_fragment, container, false);

        return view;
    }

    @Override
    public void ProcessFinish(String output, Activity activity) {

    }
}
