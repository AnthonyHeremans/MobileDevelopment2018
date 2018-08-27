package be.pxl.com.mybbq.QREventList;


import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import be.pxl.com.mybbq.EventDetail.EventDetailFragment;
import be.pxl.com.mybbq.Newsfeed.CustomListViewNewsfeed;
import be.pxl.com.mybbq.Newsfeed.Models.EventClass;
import be.pxl.com.mybbq.R;
import be.pxl.com.mybbq.SQLLite.FeedDatabase;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class EventsIGoTOFragment extends Fragment {

    private FragmentActivity myContext;
    @BindView(R.id.lstMyList)
    ListView list;
    public EventsIGoTOFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_events_igo_to, container, false);
        ButterKnife.bind(this, view);
        FeedDatabase.FeedDatabaseDbHelper mDbHelper = new FeedDatabase.FeedDatabaseDbHelper(getContext());
       // FeedReaderDbHelper mDbHelper = new FeedReaderDbHelper(getContext());
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                FeedDatabase.FeedEntry._ID,
                FeedDatabase.FeedEntry.COLUMN_NAME_USERNAME,
                FeedDatabase.FeedEntry.COLUMN_NAME_EVENTNAME,
                FeedDatabase.FeedEntry.COLUMN_NAME_ADRESS,
                FeedDatabase.FeedEntry.COLUMN_NAME_HOUSENUMBER,
                FeedDatabase.FeedEntry.COLUMN_NAME_CITY,
                FeedDatabase.FeedEntry.COLUMN_NAME_POSTALCODE,
                FeedDatabase.FeedEntry.COLUMN_NAME_MAXATTENDEES,
                FeedDatabase.FeedEntry.COLUMN_NAME_ATTENDEES,
                FeedDatabase.FeedEntry.COLUMN_NAME_DATE,
                FeedDatabase.FeedEntry.COLUMN_NAME_TIME,
                FeedDatabase.FeedEntry.COLUMN_NAME_COMMENT,
                FeedDatabase.FeedEntry.COLUMN_NAME_FILTERS
        };

        String table = FeedDatabase.FeedEntry.TABLE_NAME;

        ArrayList<EventClass> myObjectList = new ArrayList<EventClass>();

        try {

            Cursor  cursor = db.rawQuery("select * from " + table,null);
            try {

                // looping through all rows and adding to list
                if (cursor.moveToFirst()) {
                    do {
                        EventClass obj = new EventClass();
                        //only one column
                        obj.setUsername(cursor.getString(1));
                        obj.setEventName(cursor.getString(2));
                        obj.setAdress(cursor.getString(3));
                        obj.setHouseNumber(cursor.getString(4));
                        obj.setCity(cursor.getString(5));
                        obj.setPostalCode(cursor.getString(6));
                        obj.setNumberOfMaxAttendees(cursor.getString(7));
                        obj.setNumberOfAttendees(cursor.getString(8));
                        obj.setDate(cursor.getString(9));
                        obj.setTime(cursor.getString(10));
                        obj.setComment(cursor.getString(11));
                        obj.setFilters(cursor.getString(12));

                        //you could add additional columns here..

                        myObjectList.add(obj);
                    } while (cursor.moveToNext());
                }

            } finally {
                try { cursor.close(); } catch (Exception ignore) {}
            }

        } finally {
            try { db.close(); } catch (Exception ignore) {}
        }

        List<EventClass> revList = reverseList(myObjectList);
        myContext = (FragmentActivity) getActivity();
        proceed(revList, myContext,list);
        for(int l=0; l<=myObjectList.size(); l++) {
            Toast.makeText(getContext(), "The time is " + myObjectList.get(1).getFilters(), Toast.LENGTH_LONG).show();
        }
        return view;
    }
    private void proceed(final List<EventClass> newsfeedMessageListRes, final Activity context, ListView lst)
    {
        String[] title = new String[newsfeedMessageListRes.size()];
        for (int i = 0; i < newsfeedMessageListRes.size(); i++) {
            title[i] = newsfeedMessageListRes.get(i).getEventName();
        }
        CustomListViewNewsfeed customListView = new CustomListViewNewsfeed(context, title, newsfeedMessageListRes);
        lst.setAdapter(customListView);
        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context,"OnClickWorks", duration);
                toast.show();

                FragmentTransaction transaction = ((FragmentActivity)context).getSupportFragmentManager().beginTransaction();
                android.support.v4.app.Fragment mfragment = new QRview();
                EventClass newsObj = newsfeedMessageListRes.get(position);
                Bundle bundle = new Bundle();

                Gson gson = new Gson();
                // 2. Java object to JSON, and assign to a String
                String jsonInString = gson.toJson(newsObj);
                bundle.putString("eventObject",jsonInString);

                mfragment.setArguments(bundle);
                transaction.replace(R.id.fram, mfragment);
                transaction.commit();
            }
        });
    }
    public List<EventClass> reverseList(List<EventClass> list) {
        List<EventClass> revertedList = new ArrayList<>();
        int size = list.size() - 1;
        for (int i = size; i >= 0; i--) {
            revertedList.add(list.get(i));
        }
        return revertedList;
    }

}
