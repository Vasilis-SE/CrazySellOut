package codebrains.crazysellout.Fragments;

import android.app.Activity;
import android.content.Context;
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
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import codebrains.crazysellout.R;
import codebrains.crazysellout.System.Coordinates;
import codebrains.crazysellout.System.SystemDialogs;

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

    public void GetCoordinationsProcess(Activity activity){

        Coordinates coord = new Coordinates(activity);

        if(coord.CanGetLocation()){
            double lat = coord.GetLatitude();
            double lon = coord.GetLongitude();
            String city = coord.GetCityFromCoordinates();
            Toast.makeText(activity, "Your Location is - \nLat: " + lat +
                    "\nLong: " + lon + " And the city is : "+city, Toast.LENGTH_LONG).show();
        }
        else{
            coord.ShowSettingsAlert();
        }

        coord.StopUsingGPS();

        /*
        try {

            JSONObject jObj = coord.DeviceCoordinations();

            if(jObj.get("status") == true){
                this.longtitude.setText(jObj.get("longtitude").toString());
                this.latitude.setText(jObj.get("latitude").toString());
                this.storeCity.setText(jObj.get("city").toString());
            }
            else{
                SystemDialogs.DisplayErrorAlertBox(jObj.get("message").toString(),
                        "Coordination Error", activity);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        */

    }

    public void AddNewProductProcess(){

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

        String longtitude = this.longtitude.getText().toString();
        String latitude = this.latitude.getText().toString();
        String city = this.storeCity.getText().toString();

        Log.d("Data : ", prodName+" "+storeName+" "+category+" "+prodPrice+" "+description+" "+
                " "+formatedDate+" "+longtitude+" "+latitude+" "+city);
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
