package be.pxl.com.mybbq.EventDetail;


import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import be.pxl.com.mybbq.Global.GlobalUser;
import be.pxl.com.mybbq.Newsfeed.Models.EventClass;
import be.pxl.com.mybbq.R;
import be.pxl.com.mybbq.SQLLite.FeedDatabase;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * A simple {@link Fragment} subclass.
 */
public class EventDetailFragment extends Fragment {

    @BindView(R.id.LblEventNameString)
    TextView eventName;
    @BindView(R.id.LblAdressString)
    TextView adress;
    @BindView(R.id.LblHousenumberString)
    TextView houseNumber;
    @BindView(R.id.LblPostalCodeString)
    TextView postalCode;
    @BindView(R.id.LblCityString)
    TextView city;
    @BindView(R.id.LblAvaibleAttendeesString)
    TextView availbleAttendees;
    @BindView(R.id.LblDateString)
    TextView dateString;
    @BindView(R.id.LblTimeString)
    TextView timeString;
    @BindView(R.id.LblCommentString)
    TextView comment;
    @BindView(R.id.lblFilterString)
    TextView filterString;


    public EventDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view = inflater.inflate(R.layout.fragment_event_detail, container, false);
        ButterKnife.bind(this, view);

       Bundle bundle = getArguments();
        EventClass myObject = new EventClass();

        String jsonString = bundle.getString("eventObject");
        if (jsonString != null)
        {
            myObject = new Gson().fromJson(jsonString, EventClass.class);

        }
        eventName.setText(myObject.getEventName());
        adress.setText(myObject.getAdress());
        houseNumber.setText(myObject.getHouseNumber());
        postalCode.setText(myObject.getPostalCode());
        city.setText(myObject.getCity());
        availbleAttendees.setText(myObject.getNumberOfMaxAttendees());
        dateString.setText(myObject.getDate());
        timeString.setText(myObject.getTime());
        comment.setText(myObject.getComment());
        filterString.setText(myObject.getFilters());
        return view;
    }
    @OnClick(R.id.BtnPushGoEvent)
    public void goToEventClick(View view)
    {
        //create DB
        FeedDatabase.FeedDatabaseDbHelper mDbHelper = new FeedDatabase.FeedDatabaseDbHelper(getContext());

        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        String username = GlobalUser.getInstance().getUserName();
        values.put(FeedDatabase.FeedEntry.COLUMN_NAME_USERNAME, username);
        values.put(FeedDatabase.FeedEntry.COLUMN_NAME_EVENTNAME, eventName.getText().toString());
        values.put(FeedDatabase.FeedEntry.COLUMN_NAME_ADRESS, adress.getText().toString());
        values.put(FeedDatabase.FeedEntry.COLUMN_NAME_HOUSENUMBER, houseNumber.getText().toString());
        values.put(FeedDatabase.FeedEntry.COLUMN_NAME_CITY, city.getText().toString());
        values.put(FeedDatabase.FeedEntry.COLUMN_NAME_POSTALCODE, postalCode.getText().toString());
        values.put(FeedDatabase.FeedEntry.COLUMN_NAME_MAXATTENDEES, availbleAttendees.getText().toString());
        values.put(FeedDatabase.FeedEntry.COLUMN_NAME_ATTENDEES, "");
        values.put(FeedDatabase.FeedEntry.COLUMN_NAME_DATE, dateString.getText().toString());
        values.put(FeedDatabase.FeedEntry.COLUMN_NAME_TIME, timeString.getText().toString());
        values.put(FeedDatabase.FeedEntry.COLUMN_NAME_COMMENT, comment.getText().toString());
        values.put(FeedDatabase.FeedEntry.COLUMN_NAME_FILTERS, filterString.getText().toString());

        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(FeedDatabase.FeedEntry.TABLE_NAME, null, values);
    }


}
