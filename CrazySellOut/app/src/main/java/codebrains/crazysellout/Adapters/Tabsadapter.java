package codebrains.crazysellout.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import codebrains.crazysellout.Fragments.CreatAccountFragment;
import codebrains.crazysellout.Fragments.LoginFragment;

/**
 * Class that initializes that tabs of the main activity UI.
 */
public class Tabsadapter  extends FragmentStatePagerAdapter{

    private int TOTAL_TABS = 2;

    public Tabsadapter(FragmentManager fm) {
        super(fm);
        // TODO Auto-generated constructor stub
    }

    @Override
    public Fragment getItem(int index) {
        // TODO Auto-generated method stub
        switch (index) {
            case 0:
                return new LoginFragment();

            case 1:
                return new CreatAccountFragment();
            
        }

        return null;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return TOTAL_TABS;
    }

}