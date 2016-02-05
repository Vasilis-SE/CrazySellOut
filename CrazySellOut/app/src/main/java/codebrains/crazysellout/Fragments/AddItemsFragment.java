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
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import codebrains.crazysellout.AsyncTasks.AttemptAddProduct;
import codebrains.crazysellout.Controllers.AddProductController;
import codebrains.crazysellout.R;
import codebrains.crazysellout.System.Conversions;
import codebrains.crazysellout.System.Coordinates;
import codebrains.crazysellout.System.JSONParser;
import codebrains.crazysellout.System.SystemDialogs;

import static codebrains.crazysellout.Activities.MainProducerActivity.GetUsername;


public class AddItemsFragment extends Fragment {

    private ActivitySubClass activitySubClass;

    private EditText productName, storeName, productPrice, productDiscription;
    private TextView longitude, latitude, storeCity;
    private Spinner spinner;
    private DatePicker datePicker;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_add_items_fragment, container, false);

        this.activitySubClass = new ActivitySubClass();

        return view;
    }

    /**
     * Method that enables the gps settings in order to retrieve the longitude, latitude and city
     * name from coordinates. Then the results are displayed on the appropriate text views.
     *
     * @param activity The activity object of MainProducerActivity activity.
     */
    public void GetCoordinatesProcess(Activity activity){

        Coordinates gps = new Coordinates(activity);
        this.longitude = (TextView) activity.findViewById(R.id.longitudetv);
        this.latitude = (TextView) activity.findViewById(R.id.latitudetv);
        this.storeCity = (TextView) activity.findViewById(R.id.storecitytextview);

        if(gps.CanGetLocation()){

            double lat = gps.GetLatitude();
            double lon = gps.GetLongitude();
            String city = gps.GetCityFromCoordinates();

            Toast.makeText(activity, "Your Location is - \nLat: " + lat +
                    "\nLong: " + lon + " And the city is : "+city, Toast.LENGTH_LONG).show();

            this.longitude.setText(Conversions.ConvertDoubleToString(lon));
            this.latitude.setText(Conversions.ConvertDoubleToString(lat));
            this.storeCity.setText(city);
        }
        else{
            gps.ShowSettingsAlert();
        }

        gps.StopUsingGPS();
    }

    /**
     * Method that handles the addition of a new product (check the values given by user and finally
     * calling the server to update the server).
     *
     * @param activity The object of MainProducerActivity activity.
     */
    public void AddNewProductProcess(Activity activity){

        //Initializing objects
        this.productName = (EditText) activity.findViewById(R.id.prodname);
        this.storeName = (EditText) activity.findViewById(R.id.storename);
        this.productPrice = (EditText) activity.findViewById(R.id.prodprice);
        this.productDiscription = (EditText) activity.findViewById(R.id.proddesc);

        this.longitude = (TextView) activity.findViewById(R.id.longitudetv);
        this.latitude = (TextView) activity.findViewById(R.id.latitudetv);
        this.storeCity = (TextView) activity.findViewById(R.id.storecitytextview);

        this.spinner = (Spinner) activity.findViewById(R.id.spinner2);
        this.datePicker = (DatePicker) activity.findViewById(R.id.datePicker);

        String prodName = this.productName.getText().toString().trim();
        String storeName = this.storeName.getText().toString().trim();
        String category = this.spinner.getSelectedItem().toString();
        String prodPrice = this.productPrice.getText().toString().trim();
        String description = this.productDiscription.getText().toString().trim();

        //The month field is added to it a +1 because the month starts counting from 0.
        int   day  = this.datePicker.getDayOfMonth();
        int   month= this.datePicker.getMonth()+1;
        int   year = this.datePicker.getYear();
        String date = day+"/"+month+"/"+year;

        String longtitude = this.longitude.getText().toString();
        String latitude = this.latitude.getText().toString();
        String city = this.storeCity.getText().toString();

        JSONObject jObj = new JSONObject();
        try {
            jObj.put("productName", prodName);
            jObj.put("storeName", storeName);
            jObj.put("category", category);
            jObj.put("productPrice", prodPrice);
            jObj.put("description", description);
            jObj.put("expireDate", date);
            jObj.put("longitude", longtitude);
            jObj.put("latitude", latitude);
            jObj.put("city", city);
            jObj.put("username", GetUsername());
            jObj.put("status", true);
            jObj.put("message", "");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        AddProductController apc = new AddProductController(jObj);
        String result = apc.AddProductMainControl();

        if(result.equals("")){
            new AttemptAddProduct(activity, jObj).execute();
        }
        else{
            SystemDialogs.DisplayInformationAlertBox(result, "Add Product Dialog", activity);
        }

    }


    //Sub-class that extends the activity android class and initializes the spinner items.
    public class ActivitySubClass extends Activity {

        private Spinner spinner;

        @Override
        public void onCreate(Bundle savedInstanceState) {

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
