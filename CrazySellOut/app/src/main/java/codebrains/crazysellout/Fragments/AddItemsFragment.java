package codebrains.crazysellout.Fragments;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import codebrains.crazysellout.R;

public class AddItemsFragment extends Fragment {

    private ActivitySubClass activitySubClass;

    private EditText productName, storeName, productPrice, productDiscription;
    private TextView longtitude, latitude, storeCity;
    private Spinner spinner;
    private DatePicker datePicker;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_add_items_fragment, container, false);

        this.activitySubClass = new ActivitySubClass();

        //Initializing objects
        this.productName = (EditText) view.findViewById(R.id.prodname);
        this.storeName = (EditText) view.findViewById(R.id.storename);
        this.productPrice = (EditText) view.findViewById(R.id.prodprice);
        this.productDiscription = (EditText) view.findViewById(R.id.proddesc);
        this.longtitude = (TextView) view.findViewById(R.id.longtitudetextview);
        this.latitude = (TextView) view.findViewById(R.id.latitudetextview);
        this.storeCity = (TextView) view.findViewById(R.id.storecitytextview);
        this.spinner = (Spinner) view.findViewById(R.id.spinner2);
        this.datePicker = (DatePicker) view.findViewById(R.id.datePicker);

        return view;
    }

    public void AddNewProduct(){

        String prodName = this.productName.getText().toString().trim();
        String storeName = this.storeName.getText().toString().trim();
        String category = this.spinner.getSelectedItem().toString();
        String prodPrice = this.productPrice.getText().toString().trim();
        String description = this.productDiscription.getText().toString().trim();

        int   day  = this.datePicker.getDayOfMonth();
        int   month= this.datePicker.getMonth();
        int   year = this.datePicker.getYear();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String formatedDate = sdf.format(new Date(day, month, year));

    }

    //Sub-class that extends the activity android class and initializes the spinner items.
    public class ActivitySubClass extends Activity {

        private Spinner spinner;

        public void onCreate() {

            this.spinner = (Spinner) findViewById(R.id.spinner2);
            ArrayAdapter<String> adapter;
            List<String> list;

            list = new ArrayList<String>();
            list.add("Food");
            list.add("Cleaning");
            list.add("Beverage");
            list.add("Personal Care");
            list.add("Clothing");
            list.add("House Hold");
            list.add("Toys");

            adapter = new ArrayAdapter<String>(this.getApplicationContext(),
                    android.R.layout.simple_spinner_item, list);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            spinner.setAdapter(adapter);
        }



    }

}
