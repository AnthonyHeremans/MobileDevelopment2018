package be.pxl.com.mybbq.Newsfeed;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import be.pxl.com.mybbq.Newsfeed.Models.EventClass;
import be.pxl.com.mybbq.R;
import butterknife.BindView;
import butterknife.ButterKnife;

public class CustomListViewNewsfeed extends ArrayAdapter<String> {

    private String[] date;
    private String[] time;
    private String[] title;
    private String[] shortMessage;
    private String[] city;
    private Activity context;

    public CustomListViewNewsfeed(Activity context, String[] title, List<EventClass> eventClassList){
        //check dit na!
        super(context, R.layout.fragment_custom_event_list_layout, title);

        date = new String[eventClassList.size()];
        time = new String[eventClassList.size()];
        shortMessage  = new String[eventClassList.size()];
        city  = new String[eventClassList.size()];

        this.context = context;
        this.title = title;

        for (int i = 0; i < eventClassList.size(); i++) {
            this.date[i] = eventClassList.get(i).getDate();
            this.time[i] = eventClassList.get(i).getTime();
            this.city[i] =  eventClassList.get(i).getPostalCode() + " " + eventClassList.get(i).getCity();
            this.shortMessage[i] = eventClassList.get(i).getComment();

        }}

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            View r = convertView;
            ViewHolder viewHolder = null;

            if (r == null) {
                LayoutInflater layoutInflater = context.getLayoutInflater();

                r = layoutInflater.inflate(R.layout.fragment_custom_event_list_layout, null, true);


                viewHolder = new ViewHolder(r);
                r.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) r.getTag();
            }

            viewHolder.textViewShort.setText(shortMessage[position]);
            viewHolder.textViewDate.setText(date[position]);
            viewHolder.textViewTitle.setText(title[position]);
            viewHolder.textViewTime.setText(time[position]);
            viewHolder.textViewCity.setText(city[position]);

            return r;
        }
    //optimalization
    class ViewHolder {
        @BindView(R.id.lblDate) TextView textViewDate;
        @BindView(R.id.lblTime) TextView textViewTime;
        @BindView(R.id.lblcity) TextView textViewCity;
        @BindView(R.id.lblTitel) TextView textViewTitle;
        @BindView(R.id.lblShortDesc) TextView textViewShort;

        ViewHolder(View v) {
            ButterKnife.bind(this, v);
        }
    }
}

