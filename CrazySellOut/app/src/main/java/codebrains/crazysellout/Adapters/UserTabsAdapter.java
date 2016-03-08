package codebrains.crazysellout.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import codebrains.crazysellout.Fragments.CustomerUserFragment;
import codebrains.crazysellout.Fragments.GoogleMapsFragment;
import codebrains.crazysellout.Fragments.UserFavoritesFragment;
import codebrains.crazysellout.Fragments.UserProductListFragment;


/**
 * Class that initializes the tabs of the user UI.
 */
public class UserTabsAdapter extends FragmentStatePagerAdapter{

    private int TOTAL_TABS = 4;

    public UserTabsAdapter(FragmentManager fm) {
        super(fm);
        // TODO Auto-generated constructor stub
    }

    @Override
    public Fragment getItem(int index) {
        // TODO Auto-generated method stub
        switch (index) {
            case 0:
                return new CustomerUserFragment();
            case 1:
                return new UserProductListFragment();
            case 2:
                return new UserFavoritesFragment();
            case 3:
                return new GoogleMapsFragment();

        }

        return null;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return TOTAL_TABS;
    }

}
