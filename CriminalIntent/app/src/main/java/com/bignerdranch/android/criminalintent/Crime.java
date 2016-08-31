package com.bignerdranch.android.criminalintent;

import java.util.UUID;

/**
 * Created by schizophrenia on 30/08/16.
 */
public class Crime {

    public UUID getmId() {
        return mId;
    }

    private UUID mId;

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    private String mTitle;


    public Crime()
    {
        //Generate unique identifier
        mId = UUID.randomUUID();
    }

}
