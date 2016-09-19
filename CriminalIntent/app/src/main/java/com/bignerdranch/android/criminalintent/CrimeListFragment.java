package com.bignerdranch.android.criminalintent;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Luis on 13/09/2016.
 */

public class CrimeListFragment extends Fragment {

    private static final int REQUEST_CRIME = 1;
    private static final int NO_ELEMENT = -1;
    private RecyclerView mCrimeRecyclerView;
    private CrimeAdapter mAdapter;
    private int lastItemClick = NO_ELEMENT;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_CRIME)
        {

        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_crime_list, container, false);
        mCrimeRecyclerView = (RecyclerView) view.findViewById(R.id.crime_recycler_view);
        mCrimeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return  view;
    }

    //when the user presses the back button in crimeactivity, this resume this fragment
    //this is the safest place to  take action to update a fragment's view
    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    private void updateUI()
    {
        CrimeLab crimeLab = CrimeLab.get(getActivity());
        List<Crime> crimes = crimeLab.getCrimes();

        if(mAdapter == null) {
            mAdapter = new CrimeAdapter(crimes);
            mCrimeRecyclerView.setAdapter(mAdapter);
        }
        else {
            if(lastItemClick == NO_ELEMENT)
                mAdapter.notifyDataSetChanged();
            else
                mAdapter.notifyItemChanged(lastItemClick);
        }
    }

    private class CrimeHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        private TextView mTitleTextView;
        private CheckBox mSolvedCheckBox;
        private TextView mDateTextView;
        private Crime mCrime;

        public CrimeHolder (View itemView)
        {
            super(itemView);
            itemView.setOnClickListener(this);
            mDateTextView = (TextView) itemView.findViewById(R.id.list_item_crime_date_text_view);
            mSolvedCheckBox = (CheckBox) itemView.findViewById(R.id.list_item_crime_solved_check_box);
            mTitleTextView = (TextView) itemView.findViewById(R.id.list_item_crime_title_text_view);
        }

        public void bindCrime(Crime crime)
        {
            mCrime = crime;
            mTitleTextView.setText(mCrime.getmTitle());
            mDateTextView.setText(mCrime.getDate().toString());
            mSolvedCheckBox.setChecked(mCrime.isSolved());
        }

        @Override
        public void onClick(View v)
        {
            lastItemClick = mCrimeRecyclerView.getChildAdapterPosition(v);
            Intent intent = CrimePagerActivity.newIntent(getActivity(), mCrime.getmId());
            startActivityForResult(intent, REQUEST_CRIME);
        }
    }

    private class CrimeAdapter extends RecyclerView.Adapter<CrimeHolder>
    {
        public List<Crime> mCrimes;
        public CrimeAdapter(List<Crime> crimes)
        {
            mCrimes = crimes;
        }

        @Override
        //recyclerview calls it when it needs a new view
        public CrimeHolder onCreateViewHolder(ViewGroup parent, int viewType)
        {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.list_item_crime, parent, false);
            return new CrimeHolder(view);
        }

        @Override
        public void onBindViewHolder(CrimeHolder holder, int position)
        {
            Crime crime = mCrimes.get(position);
            holder.bindCrime(crime);
            //holder.mTitleTextView.setText(crime.getmTitle());
        }

        @Override
        public int getItemCount()
        {
            return mCrimes.size();
        }
    }
}
