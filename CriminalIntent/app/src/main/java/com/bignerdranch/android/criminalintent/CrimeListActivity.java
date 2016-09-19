package com.bignerdranch.android.criminalintent;

import android.support.v4.app.Fragment;

/**
 ** Created by Luis on 13/09/2016.
 **
 **/

public class CrimeListActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new CrimeListFragment();
    }
}
