package codebrains.crazysellout.Fragments;

import android.app.Activity;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import codebrains.crazysellout.AsyncTasks.AttemptRetrieveProducts;
import codebrains.crazysellout.Controllers.CalculateDistancesController;
import codebrains.crazysellout.Interfaces.IAsyncResponse;
import codebrains.crazysellout.R;
import codebrains.crazysellout.System.Conversions;
import codebrains.crazysellout.System.Coordinates;

public class GoogleMapsFragment extends Fragment implements IAsyncResponse{

    private MapView mapView;
    private static GoogleMap map;
    private static Spinner spinner;
    private AttemptRetrieveProducts asyncTask;
    private static String response;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.activity_google_maps_fragment, container, false);

        // Gets the MapView from the XML layout and creates it
        mapView = (MapView) v.findViewById(R.id.mapview);
        mapView.onCreate(savedInstanceState);

        // Gets to GoogleMap from the MapView and does initialization stuff
        map = mapView.getMap();

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("sortcategory", "All");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        asyncTask = new AttemptRetrieveProducts(this.getActivity(), jsonObject);
        asyncTask.delegate = this;
        asyncTask.execute();

        spinner = (Spinner) v.findViewById(R.id.spinner4);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                //If the task is finished then you can run the listener code.
                if(asyncTask.getStatus() == AsyncTask.Status.FINISHED) {
                    ConfigureGoogleMaps(GoogleMapsFragment.response);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        return v;
    }



    /**
     * Method that configures all the data to initialize the google map on the activity.
     */
    private void ConfigureGoogleMaps(String output){

        Coordinates coordinates = new Coordinates(this.getContext());

        CalculateDistancesController cdc = new CalculateDistancesController();
        cdc.ControlMethodForCalculatingDistance(output, coordinates.GetLongitude(),
                coordinates.GetLatitude(), spinner.getSelectedItem().toString());
        JSONArray jsonArray = cdc.GetArrayOfProductInArea();

        try {
            this.DisplayMarkersForProductsOnArea(jsonArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // Needs to call MapsInitializer before doing any CameraUpdateFactory calls
        MapsInitializer.initialize(this.getActivity());

        // Updates the location and zoom of the MapView
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(new LatLng(coordinates.GetLatitude(),
                coordinates.GetLongitude()), 10);
        map.animateCamera(cameraUpdate);
    }

    /**
     * Method that displays all the markers in the google map.
     * @param jsonArray An array of json data that contains all the product to be displayed in the map.
     * @throws JSONException Exception that occurs whenever something goes wrong with json data process.
     */
    private void DisplayMarkersForProductsOnArea(JSONArray jsonArray) throws JSONException {

        for(int i=0; i<jsonArray.length(); i++){

            JSONObject jsonObject = (JSONObject) jsonArray.get(i);
            if(jsonObject.get("startOrNot") == true){

                map.addMarker(new MarkerOptions().position(new LatLng(Conversions.ConvertStringToDouble(jsonObject.get("latitude").toString())
                        ,Conversions.ConvertStringToDouble(jsonObject.get("longitude").toString()))).title("Start")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN)));
            }
            else {

                map.addMarker(new MarkerOptions().position(new LatLng(Conversions.ConvertStringToDouble(jsonObject.get("latitude").toString())
                        ,Conversions.ConvertStringToDouble(jsonObject.get("longitude").toString())))
                        .title(jsonObject.get("productName").toString()));
            }
        }

    }

    @Override
    public void onResume() {
        mapView.onResume();
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

    }

    @Override
    public void ProcessFinish(String output, Activity activity) {

        GoogleMapsFragment.response = output;
        ConfigureGoogleMaps(output);
    }
}
