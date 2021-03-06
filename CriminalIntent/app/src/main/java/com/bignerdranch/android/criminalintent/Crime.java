package com.bignerdranch.android.criminalintent;

import java.util.Date;
import java.util.UUID;

/*
 *
 * Created by schizophrenia on 30/08/16.
 *
 */

public class Crime
{
    private UUID mId;
    private String mTitle;
    private Date mDate;
    private boolean mSolved;
    private String mSuspect;

    public UUID getmId()
    {
        return mId;
    }
    public String getmTitle()
    {
        return mTitle;
    }
    public void setmTitle(String mTitle)
    {
        this.mTitle = mTitle;
    }
    public Date getDate()
    {
        return mDate;
    }
    public boolean isSolved()
    {
        return mSolved;
    }
    public void setSolved(boolean solved)
    {
        mSolved = solved;
    }
    public void setDate(Date date)
    {
        mDate = date;
    }

    public Crime()
    {
       this(UUID.randomUUID());
    }

    public Crime(UUID id)
    {
        mId = id;
        mDate = new Date();
    }

    public String getSuspect() {
        return mSuspect;
    }

    public void setSuspect(String suspect) {
        mSuspect = suspect;
    }
}
