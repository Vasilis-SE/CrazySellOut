package codebrains.crazysellout.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ExpandableListView;
import java.util.HashMap;
import java.util.List;
import codebrains.crazysellout.Adapters.ExpandableListAdapter;
import codebrains.crazysellout.Adapters.ProducerTabsAdapter;
import codebrains.crazysellout.Fragments.AddItemsFragment;
import codebrains.crazysellout.Fragments.ProductsListActivity;
import codebrains.crazysellout.Models.ProductList;
import codebrains.crazysellout.R;


public class MainProducerActivity extends ActionBarActivity implements  android.support.v7.app.ActionBar.TabListener {

    private ViewPager tabsviewPager;
    private ProducerTabsAdapter mTabsAdapter;
    private AddItemsFragment aif;
    private static String username;

    private ProductsListActivity pla;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_producer);

        //Get the previous intent.
        Intent myIntent = getIntent();
        this.username = myIntent.getStringExtra("username");

        this.aif = new AddItemsFragment();
        this.pla = new ProductsListActivity();

        tabsviewPager = (ViewPager) findViewById(R.id.tabspager);
        mTabsAdapter = new ProducerTabsAdapter(getSupportFragmentManager());
        tabsviewPager.setAdapter(mTabsAdapter);

        this.getSupportActionBar().setHomeButtonEnabled(false);
        this.getSupportActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        ActionBar.Tab addProductsTab = getSupportActionBar().newTab().setText("Add Products").setTabListener(this);
        ActionBar.Tab myProductsTab = getSupportActionBar().newTab().setText("My Products").setTabListener(this);
        ActionBar.Tab productsTab = getSupportActionBar().newTab().setText("Products").setTabListener(this);

        getSupportActionBar().addTab(addProductsTab);
        getSupportActionBar().addTab(myProductsTab);
        getSupportActionBar().addTab(productsTab);

        //This helps in providing swiping effect for v7 compat library
        tabsviewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                // TODO Auto-generated method stub
                getSupportActionBar().setSelectedNavigationItem(position);
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
                // TODO Auto-generated method stub

            }
        });
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    /**
     * Event click that occurs whenever the submit button on add new item fragment is clicked.
     *
     * @param view The view of the activity.
     */
    public void AddNewProductProcess(View view){
        this.aif.AddNewProductProcess(this);
    }

    public void GetCoordinatesProcess(View view){
        this.aif.GetCoordinatesProcess(this);
    }

    public static String GetUsername(){
        return username;
    }

    public void SortProductsEvent(View view){


        this.pla.SortProductsEvent();
    }

}
