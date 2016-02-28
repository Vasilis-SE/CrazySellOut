package codebrains.crazysellout.Activities;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.Tab;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import codebrains.crazysellout.Adapters.UserTabsAdapter;
import codebrains.crazysellout.Fragments.UserFavoritesFragment;
import codebrains.crazysellout.Fragments.UserProductListFragment;
import codebrains.crazysellout.R;

public class MainUserActivity extends ActionBarActivity implements  android.support.v7.app.ActionBar.TabListener{

    private ViewPager tabsviewPager;
    private static String username;
    private UserTabsAdapter mTabsAdapter;

    private UserProductListFragment uplf;
    private UserFavoritesFragment uff;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_user);

        tabsviewPager = (ViewPager) findViewById(R.id.tabspager);
        mTabsAdapter = new UserTabsAdapter(getSupportFragmentManager());
        tabsviewPager.setAdapter(mTabsAdapter);


        //Get the previous intent.
        Intent myIntent = getIntent();
        this.username = myIntent.getStringExtra("username");

        this.uplf = new UserProductListFragment();
        this.uff = new UserFavoritesFragment();

        this.getSupportActionBar().setHomeButtonEnabled(false);
        this.getSupportActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        Tab productsTab = getSupportActionBar().newTab().setText("Products").setTabListener(this);
        Tab favoritesTab = getSupportActionBar().newTab().setText("Favorites").setTabListener(this);
        Tab mapTab = getSupportActionBar().newTab().setText("Map").setTabListener(this);

        getSupportActionBar().addTab(productsTab);
        getSupportActionBar().addTab(favoritesTab);
        getSupportActionBar().addTab(mapTab);

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

        //Change the current tab, with the one selected.
        tabsviewPager.setCurrentItem(tab.getPosition());

    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    public static String GetUsername(){
        return username;
    }

    /**
     * Event on click for sort products button.
     * @param view The activity that fired the event.
     */
    public void SortProductsEvent(View view){
        this.uplf.SortProductsEvent(view, this);
    }

    /**
     * Event on click that occurs whenever the add favorite button is clicked.
     * @param view The view of the activity that fired the event.
     */
    public void AddProductToFavorites(View view){
        this.uplf.AddProductToFavorites(view, this);
    }

    /**
     * Event on click that occurs whenever the refresh button on the favorite list tab is pressed.
     * @param view The view of the activity that fired the event.
     */
    public void RefreshFavoriteListProcess(View view){
        this.uff.RefreshFavoriteListProcess(view);
    }

    /**
     * Event on click that occurs whenever the delete button on the favorite list tab is pressed.
     * @param view The view of the activity that fired the event.
     */
    public void DeleteFavoriteFromListProcess(View view){
        this.uff.DeleteFavoriteFromListProcess(view);
    }

}
