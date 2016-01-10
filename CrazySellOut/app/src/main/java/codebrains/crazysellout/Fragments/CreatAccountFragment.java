package codebrains.crazysellout.Fragments;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import codebrains.crazysellout.R;

public class CreatAccountFragment extends Fragment {

    private ActivitySubClass activitySubClass;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_creat_account_fragment, container, false);

        this.activitySubClass = new ActivitySubClass();

        return view;
    }


    //Sub-class that extends the activity android class and initializes the spinner items.
    public class ActivitySubClass extends Activity{

        private Spinner spinner;

        public void onCreate() {
            this.spinner = (Spinner) findViewById(R.id.spinner);
            ArrayAdapter<String> adapter;
            List<String> list;

            list = new ArrayList<String>();
            list.add("gmail.com");
            list.add("gmail.gr");
            list.add("hotmail.com");
            list.add("hotmail.gr");
            list.add("yahoo.com");
            list.add("yahoo.gr");
            adapter = new ArrayAdapter<String>(this.getApplicationContext(),
                    android.R.layout.simple_spinner_item, list);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);
        }



    }

}
