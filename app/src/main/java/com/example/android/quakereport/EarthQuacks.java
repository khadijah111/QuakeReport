package com.example.android.quakereport;

/**
 * Created by khadijah on 7/30/2017.
 */
public class EarthQuacks {
    // @param magnitude earth quake magnitude
    private Double mMagnitude;

    // @param  city location of earthquake
    private String mLocation;

    /** Time of the earthquake */
    private long mTimeInMilliseconds;
    /* URL of the earthquake*/
    private String mUrl;

    public EarthQuacks(Double magnitude, String location, Long date, String url)
    {
        mMagnitude = magnitude;
        mLocation = location;
        mTimeInMilliseconds = date;
        mUrl = url;
    }


    public String getLocationText() {
        return mLocation;
    }
    public Double getMagnitude() {
        return mMagnitude;
    }
    public long getTimeInMilliseconds() {
        return mTimeInMilliseconds;
    }
    public String getUrl() {
        return mUrl;
    }
}
