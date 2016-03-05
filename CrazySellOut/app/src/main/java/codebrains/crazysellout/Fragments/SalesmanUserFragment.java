package codebrains.crazysellout.Fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import codebrains.crazysellout.R;

public class SalesmanUserFragment extends Fragment {

    private TextView accountTv;
    private EditText passwordEdt, retypeEdt, numberEdt, emailEdt;
    private RadioGroup sexRadioGroup;

    //Constructor
    public SalesmanUserFragment(){
        //Empty Constructor needed
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_salesman_user_fragment, container, false);

        return view;
    }


}
