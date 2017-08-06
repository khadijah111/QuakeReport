package com.example.android.quakereport;

import android.app.Activity;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by khadijah on 7/30/2017.
 */
public class EarthQuackAdapter extends ArrayAdapter<EarthQuacks>{
    private static final String LOG_TAG = EarthQuacks.class.getSimpleName();

    public EarthQuackAdapter(Activity context, ArrayList<EarthQuacks> earthQuacks) {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for three TextViews , the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context, 0, earthQuacks);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // return super.getView(position, convertView, parent);

        String primaryLocation;
        String locationOffset;

        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.quack_list_item, parent, false);
        }

        // Get the {@link AndroidFlavor} object located at this position in the list
        EarthQuacks currentQuack = getItem(position);

        // Get the original location string from the Earthquake object,
        // which can be in the format of "5km N of Cairo, Egypt" or "Pacific-Antarctic Ridge".
        String originalLocation = currentQuack.getLocationText();

        // If the original location string (i.e. "5km N of Cairo, Egypt") contains
        // a primary location (Cairo, Egypt) and a location offset (5km N of that city)
        // then store the primary location separately from the location offset in 2 Strings,
        // so they can be displayed in 2 TextViews.

        // Check whether the originalLocation string contains the " of " text
        if (originalLocation.contains(" of")) {
            String[] parts = originalLocation.split(" of");
            locationOffset = parts[0] + " of";
            primaryLocation = parts[1];
        } else {
            // Otherwise, there is no " of " text in the originalLocation string.
            // Hence, set the default location offset to say "Near the".
            locationOffset = "Near the ";
            primaryLocation = originalLocation;
        }
        //NOW finish the splitting the string *****************
        // Find the TextView in the quack_list_item.xml layout with the ID locationText
        TextView locationTextView = (TextView) listItemView.findViewById(R.id.location_offset);
        // Display the location of the current earthquake in that TextView
        locationTextView.setText(locationOffset);

        // Find the TextView in the quack_list_item.xml layout with the ID locationText
        TextView primaryLocationTextView = (TextView) listItemView.findViewById(R.id.primary_location);
        // Display the location of the current earthquake in that TextView
        primaryLocationTextView.setText(primaryLocation);

        // Find the TextView in the quack_list_item.xml layout with the ID magnitudeText
        TextView magnitudeTextView = (TextView) listItemView.findViewById(R.id.magnitudeText);
        // Get the location name from the current EarthQuack object and
        // set this text on the name TextView
        magnitudeTextView.setText(currentQuack.getMagnitude().toString());

        // Set the proper background color on the magnitude circle.
        // Fetch the background from the TextView, which is a GradientDrawable.
        GradientDrawable magnitudeCircle = (GradientDrawable) magnitudeTextView.getBackground();
        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = getMagnitudeColor(currentQuack.getMagnitude());
        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);

        // Find the TextView in the quack_list_item.xml layout with the ID DateText
        TextView dateTextView = (TextView) listItemView.findViewById(R.id.DateText);
        // Get the date time from the current EarthQuack object and
        // set this text on the name TextView
        // Create a new Date object from the time in milliseconds of the earthquake
        dateTextView.setText(FormatDate(currentQuack.getTimeInMilliseconds()));

        // Find the TextView in the quack_list_item.xml layout with the ID magnitudeText
        TextView TimeTextView = (TextView) listItemView.findViewById(R.id.TimeText);
        // Get the date time from the current EarthQuack object and
        // set this text on the name TextView
        TimeTextView.setText(FormatTime(currentQuack.getTimeInMilliseconds()));
        // Return the whole list item layout (containing 3 TextViews)
        // so that it can be shown in the ListView
        return listItemView;
    }

    /**
     * Return the color for the magnitude circle based on the intensity of the earthquake.
     *
     * @param magnitude of the earthquake
     */
    private int getMagnitudeColor(double magnitude) {
        int magnitudeColorResourceId;
        int magnitudeFloor = (int) Math.floor(magnitude);
        switch (magnitudeFloor) {
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;
        }

        return ContextCompat.getColor(getContext(), magnitudeColorResourceId);
    }
    public String FormatDate(Long milliSecondDate)
    {
        Date dateObject = new Date(milliSecondDate);
        SimpleDateFormat dateFormatter = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormatter.format(dateObject);
    }

    public String FormatTime(Long milliSecondDate)
    {
        Date dateObject = new Date(milliSecondDate);
        SimpleDateFormat dateFormatter = new SimpleDateFormat("h:mm a");
        return dateFormatter.format(dateObject);
    }
}
