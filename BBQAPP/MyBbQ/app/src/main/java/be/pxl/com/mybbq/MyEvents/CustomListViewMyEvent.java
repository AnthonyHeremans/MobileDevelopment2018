package be.pxl.com.mybbq.MyEvents;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import be.pxl.com.mybbq.MyEvents.Models.myEventClass;
import be.pxl.com.mybbq.Newsfeed.CustomListViewNewsfeed;
import be.pxl.com.mybbq.Newsfeed.Models.EventClass;
import be.pxl.com.mybbq.R;
import butterknife.BindView;
import butterknife.ButterKnife;

public class CustomListViewMyEvent  extends ArrayAdapter<String> {

    private String[] date;
    private String[] time;
    private String[] title;
    private String[] shortMessage;
    private String[] city;
    private Activity context;

    public CustomListViewMyEvent(Activity context, String[] title, List<myEventClass> eventClassList){
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
        CustomListViewMyEvent.ViewHolder viewHolder = null;

        if (r == null) {
            LayoutInflater layoutInflater = context.getLayoutInflater();

            r = layoutInflater.inflate(R.layout.fragment_custom_event_list_layout, null, true);


            viewHolder = new CustomListViewMyEvent.ViewHolder(r);
            r.setTag(viewHolder);
        } else {
            viewHolder = (CustomListViewMyEvent.ViewHolder) r.getTag();
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
        @BindView(R.id.lblDate)
        TextView textViewDate;
        @BindView(R.id.lblTime) TextView textViewTime;
        @BindView(R.id.lblcity) TextView textViewCity;
        @BindView(R.id.lblTitel) TextView textViewTitle;
        @BindView(R.id.lblShortDesc) TextView textViewShort;

        ViewHolder(View v) {
            ButterKnife.bind(this, v);
        }
    }
}
