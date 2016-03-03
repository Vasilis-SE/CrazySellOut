package codebrains.crazysellout.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import codebrains.crazysellout.Fragments.AddItemsFragment;
import codebrains.crazysellout.Fragments.ProducerItemsFragment;
import codebrains.crazysellout.Fragments.ProductsListActivity;

/**
 * Class that initializes the tabs on the producer UI
 */
public class ProducerTabsAdapter extends FragmentStatePagerAdapter {

    private int TOTAL_TABS = 3;

    public ProducerTabsAdapter(FragmentManager fm) {
        super(fm);
        // TODO Auto-generated constructor stub
    }

    @Override
    public Fragment getItem(int index) {

        switch (index) {
            case 0:
                return new AddItemsFragment();

            case 1:
                return new ProducerItemsFragment();

            case 2:
                return new ProductsListActivity();

        }

        return null;
    }

    @Override
    public int getCount() {
        return TOTAL_TABS;
    }

}
