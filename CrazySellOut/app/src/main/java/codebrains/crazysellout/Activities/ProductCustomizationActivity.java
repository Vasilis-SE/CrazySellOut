package codebrains.crazysellout.Activities;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Adapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import codebrains.crazysellout.R;
import codebrains.crazysellout.System.Conversions;

public class ProductCustomizationActivity extends Activity {

    private EditText prodName, storeName, price, description;
    private Spinner spinner;
    private DatePicker expireDate;
    private TextView longitude, latitude, city;

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




}
