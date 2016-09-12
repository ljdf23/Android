package com.bignerdranch.android.criminalintent;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

/**
 * Created by Luis on 06/09/2016.
 *
 **/

public class CrimeFragment extends Fragment {
    private Crime mCrime;
    private EditText mTitleField;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCrime = new Crime();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_crime, container, false);
        mTitleField = (EditText) v.findViewById(R.id.crime_title);
        mTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mCrime.setmTitle(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        return v;
    }

    @Override
    public void onPause() {
        Log.d("fragment", "onpause");
        super.onPause();
    }

    @Override
    public void onStop() {
        Log.d("fragment", "onstop");
        super.onStop();
    }

    @Override
    public void onDestroy() {
        Log.d("fragment", "ondestroy");
        super.onDestroy();
    }

    @Override
    public void onStart() {
        Log.d("fragment", "onstart");
        super.onStart();
    }

    @Override
    public void onResume() {
        Log.d("fragment", "onresume");
        super.onResume();
    }
}
