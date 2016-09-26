package com.bignerdranch.android.criminalintent;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.bignerdranch.android.criminalintent.CrimeDbSchema.CrimeTable.Cols.*;

/*
 * Created by Luis on 12/09/2016.
 *
 */

public class CrimeLab {

    private static CrimeLab sCrimeLab;
    //private List<Crime> mCrimes;

    private Context mContext;
    private SQLiteDatabase mDatabase;

    public static CrimeLab get(Context context)
    {
        if(sCrimeLab == null) {
            sCrimeLab = new CrimeLab(context);
        }
        return sCrimeLab;
    }

    private CrimeLab(Context context)
    {
        //Obtenemos el contexto de application porque siempre existira, en vez que pasarle el contexto de una actividad.
        //si le pasaramos una actividad como contexto a este singleton, esa actividad no morira porque este objetos singleton
        // tiene una referencia a ese contexto
        mContext = context.getApplicationContext();
        mDatabase = new CrimeBaseHelper(mContext).getWritableDatabase();

        //mCrimes = new ArrayList<>();
        for (int i=0; i <1; i++)
        {
            Crime crime = new Crime();
            crime.setmTitle("Crime #" + i);
            crime.setSolved(i%2 == 0);
            //mCrimes.add(crime);
        }
    }

    public List<Crime> getCrimes()
    {
        List<Crime> crimes = new ArrayList<>();
        CrimeCursorWrapper cursor = queryCrimes(null,null);

        try {
            cursor.moveToFirst();
            while(!cursor.isAfterLast()){
                crimes.add(cursor.getCrime());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }

        return crimes;
    }

    public Crime getCrime(UUID id)
    {
        /*  for (Crime crime : mCrimes) {
            if(crime.getmId().equals(id))
            {
                return crime;
            }
        }*/

        CrimeCursorWrapper cursor = queryCrimes(UUID + " = ?", new String[] {id.toString()});

        try
        {
            if(cursor.getCount() == 0)
            {
                return null;
            }
            cursor.moveToFirst();
            return cursor.getCrime();
        }
        finally {
            cursor.close();
        }
    }

    public void addCrime(Crime c)
    {
        ContentValues values = getContentValues(c);
        mDatabase.insert(CrimeDbSchema.CrimeTable.NAME, null, values);
       // mCrimes.add(c);
    }

    public void deleteCrime(Crime crime)
    {
       // mCrimes.remove(c);
        String uuidString = crime.getmId().toString();
        mDatabase.delete(CrimeDbSchema.CrimeTable.NAME,  UUID + " = ?", new String[] {uuidString});
    }

    private static ContentValues getContentValues(Crime crime)
    {
        ContentValues values = new ContentValues();
        values.put(UUID, crime.getmId().toString());
        values.put(TITLE, crime.getmTitle());
        values.put(DATE, crime.getDate().getTime());
        values.put(SOLVED, crime.isSolved() ? 1: 0);
        return values;
    }

    public void updateCrime(Crime crime)
    {
        String uuidString = crime.getmId().toString();
        ContentValues values = getContentValues(crime);

        mDatabase.update(CrimeDbSchema.CrimeTable.NAME, values, UUID + " = ?", new String[] {uuidString});
    }

    private CrimeCursorWrapper queryCrimes(String whereClause, String[] whereArgs)
    {
        Cursor cursor = mDatabase.query(CrimeDbSchema.CrimeTable.NAME, null, whereClause, whereArgs,null,null,null);
        return new CrimeCursorWrapper(cursor);
    }


}
