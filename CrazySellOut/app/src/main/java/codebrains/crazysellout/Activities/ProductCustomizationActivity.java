package codebrains.crazysellout.Activities;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import codebrains.crazysellout.AsyncTasks.AttemptAddProduct;
import codebrains.crazysellout.Controllers.AddProductController;
import codebrains.crazysellout.R;
import codebrains.crazysellout.System.Conversions;
import codebrains.crazysellout.System.Coordinates;
import codebrains.crazysellout.System.SystemDialogs;

import static codebrains.crazysellout.Activities.MainProducerActivity.GetUsername;

public class ProductCustomizationActivity extends Activity {

    private EditText prodName, storeName, price, description;
    private Spinner spinner;
    private DatePicker expireDate;
    private TextView longitude, latitude, city;

    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_customization);

        Intent myIntent = getIntent();
        String selectedItem = myIntent.getStringExtra("selectedItem");

        this.prodName = (EditText) findViewById(R.id.prodname);
        this.storeName = (EditText) findViewById(R.id.storename);
        this.price = (EditText) findViewById(R.id.prodprice);
        this.description = (EditText) findViewById(R.id.proddesc);
        this.spinner = (Spinner) findViewById(R.id.spinner2);
        this.expireDate = (DatePicker) findViewById(R.id.datePicker);
        this.longitude = (TextView) findViewById(R.id.longitudetv);
        this.latitude = (TextView) findViewById(R.id.latitudetv);
        this.city = (TextView) findViewById(R.id.storecitytextview);

        try {
            JSONObject selectedItemJSON = new JSONObject(selectedItem);

            this.prodName.setText(selectedItemJSON.get("productName").toString());
            this.storeName.setText(selectedItemJSON.get("storeName").toString());
            this.price.setText(selectedItemJSON.get("price").toString());
            this.description.setText(selectedItemJSON.get("description").toString());
            this.spinner.setSelection(this.GetPreSelectedSpinnerItemPosition(selectedItemJSON.get("category").toString()));
            this.SetExpireDateIntoDatePicker(selectedItemJSON.get("expireDate").toString());
            this.longitude.setText(selectedItemJSON.get("longitude").toString());
            this.latitude.setText(selectedItemJSON.get("latitude").toString());
            this.city.setText(selectedItemJSON.get("city").toString());
            this.username = selectedItemJSON.get("username").toString();

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    /**
     * Method that finds the position of the selected category.
     * @param category The category of the product in string format.
     * @return Returns the position of the selected category in the spinner item.
     */
    private int GetPreSelectedSpinnerItemPosition(String category){

        Adapter adapter = this.spinner.getAdapter();
        int n = adapter.getCount();
        int position = 0;

        for (int i = 0; i < n; i++) {

            if(adapter.getItem(i).equals(category))
                position = i;
        }

        return position;
    }

    /**
     * Method that sets the date to the date picker item in the activity.
     * @param date The date stored in the database in string format.
     */
    private void SetExpireDateIntoDatePicker(String date){

        String[] dateParts = date.split("/");
        int year = Conversions.ConvertStringToInteger(dateParts[2]);
        int month = Conversions.ConvertStringToInteger(dateParts[1]) - 1;
        int day = Conversions.ConvertStringToInteger(dateParts[0]);

        this.expireDate.init(year, month, day, null);
    }

    /**
     * Method that calls the proper class and method to find the longitude, latitude and city
     * from the coordinates.
     * @param view The view of the activity that fired the event.
     */
    public void GetCoordinatesProcess(View view){

        Coordinates gps = new Coordinates(this);

        if(gps.CanGetLocation()){

            double lat = gps.GetLatitude();
            double lon = gps.GetLongitude();
            String city = gps.GetCityFromCoordinates();

            Toast.makeText(this, "Your Location is - \nLat: " + lat +
                    "\nLong: " + lon + " And the city is : " + city, Toast.LENGTH_LONG).show();

            this.longitude.setText(Conversions.ConvertDoubleToString(lon));
            this.latitude.setText(Conversions.ConvertDoubleToString(lat));
            this.city.setText(city);
        }
        else{
            gps.ShowSettingsAlert();
        }

        gps.StopUsingGPS();
    }

    /**
     * Method that handles the update of the selected product.
     * @param view The view of the activity that fired the event.
     */
    public void UpdateProductProcess(View view){

        String productName = this.prodName.getText().toString().trim();
        String stName = this.storeName.getText().toString().trim();
        String category = this.spinner.getSelectedItem().toString();
        String prodPrice = this.price.getText().toString().trim();
        String desc = this.description.getText().toString().trim();

        //The month field is added to it a +1 because the month starts counting from 0.
        int day  = this.expireDate.getDayOfMonth();
        int month= this.expireDate.getMonth()+1;
        int year = this.expireDate.getYear();
        String date = day+"/"+month+"/"+year;

        String longi = this.longitude.getText().toString();
        String latit = this.latitude.getText().toString();
        String storeCity = this.city.getText().toString();

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("productName", productName);
            jsonObject.put("storeName", stName);
            jsonObject.put("category", category);
            jsonObject.put("productPrice", prodPrice);
            jsonObject.put("description", desc);
            jsonObject.put("expireDate", date);
            jsonObject.put("longitude", longi);
            jsonObject.put("latitude", latit);
            jsonObject.put("city", storeCity);
            jsonObject.put("username", username);
            jsonObject.put("customizeType", 1);
            jsonObject.put("status", true);
            jsonObject.put("message", "");

        } catch (JSONException e) {
            e.printStackTrace();
        }

        AddProductController apc = new AddProductController(jsonObject);
        String result = apc.AddProductMainControl();

        if(result.equals("")){
            Log.d("JSON DATA ----", String.valueOf(jsonObject));
            //new AttemptAddProduct(this, jsonObject).execute();
        }
        else{
            SystemDialogs.DisplayInformationAlertBox(result, "Configure Product Dialog", this);
        }

    }

    public void DeleteProductProcess(View view){

    }




}
