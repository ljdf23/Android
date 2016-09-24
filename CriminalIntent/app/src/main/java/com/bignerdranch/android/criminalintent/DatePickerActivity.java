package com.bignerdranch.android.criminalintent;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import java.util.Date;
import java.util.UUID;

/**
 * Created by Luis on 19/09/2016.
 *
 */

public class DatePickerActivity  extends SingleFragmentActivity{
    public static final String EXTRA_DATE = "com.bignerdranch.android.criminalintent.date";
    @Override
    protected Fragment createFragment() {
        Date date = (Date) getIntent().getSerializableExtra(EXTRA_DATE);
        DatePickerFragment fragment = DatePickerFragment.newInstance(date);
        return fragment;
    }

    public static Intent newIntent(Context pacakgeContext, Date date)
    {
        Intent intent = new Intent(pacakgeContext, DatePickerActivity.class);
        intent.putExtra(EXTRA_DATE, date);
        return intent;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(resultCode == Activity.RESULT_OK)
        {
            setResult(resultCode, data);
            finish();
        }
    }
}
