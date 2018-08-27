package be.pxl.com.mybbq.CreateEvent;


import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.EventLog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import be.pxl.com.mybbq.CreateEvent.Interfaces.ICreateEventService;
import be.pxl.com.mybbq.CreateEvent.Model.CreateEvent;
import be.pxl.com.mybbq.CreateEvent.Services.CreateEventService;
import be.pxl.com.mybbq.Global.GlobalUser;
import be.pxl.com.mybbq.Login.Interfaces.ILoginService;
import be.pxl.com.mybbq.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class CreateEventFragment extends Fragment{

    @BindView(R.id.TxtCNameEvent)
    EditText eventName;
    @BindView(R.id.TxtAdress)
    EditText adress;
    @BindView(R.id.TxtNumber)
    EditText number;
    @BindView(R.id.TxtPostcode)
    EditText postcode;
    @BindView(R.id.TxtCity)
    EditText city;
    @BindView(R.id.CComment)
    EditText comment;

    @BindView(R.id.dialog_date_datePicker)
    DatePicker date;
    @BindView(R.id.dialog_time_timePicker)
    TimePicker time;


    final String[] stringForBenji = new String[1];
    List<String> filterList = new ArrayList<>();
    Context myContext;

    private ICreateEventService createEventService;
    public CreateEventFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
            myContext = (FragmentActivity) getActivity();
        this.createEventService = new CreateEventService(myContext);
            // Inflate the layout for this fragment
          View view =  inflater.inflate(R.layout.fragment_create_event, container, false);

            Spinner spinner = (Spinner) view.findViewById(R.id.UserCount_spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this.getActivity(),
                    R.array.userCountArray, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);// Apply the adapter to the spinner
            spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(parent.getContext(), "The time is " +
                        parent.getItemAtPosition(position).toString(), Toast.LENGTH_LONG).show();
                stringForBenji[0] = parent.getItemAtPosition(position).toString();
            }

            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing.
            }
            public int posArray(AdapterView<?> parent, View view, int pos, long id)
            {
                return pos;
            }});
            ButterKnife.bind(this, view);
            return view;
    }

    @OnClick(R.id.BtnPushEvent)
    public void CreateEventClick(View view) {
        Toast.makeText(myContext, stringForBenji[0] , Toast.LENGTH_LONG).show();

        String eventNameString = eventName.getText().toString();
        String adressString = adress.getText().toString();
        String numberString = number.getText().toString();
        String postcodeString = postcode.getText().toString();
        String cityString = city.getText().toString();
        String commentString = comment.getText().toString();

        //datetimepickers
        int   day  = date.getDayOfMonth();
        int   month= date.getMonth();
        int   year = date.getYear();
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String formatedDate = sdf.format(calendar.getTime());

        int min = time.getCurrentMinute();
        int hour = time.getCurrentHour();

        String formatTime = hour + " : " + min;

        if( stringForBenji[0].equals("Meer dan 10"))
        {
            stringForBenji[0] = 100+"";
        }
        String filterString = "";
        for (String filter : filterList) {
            filterString += filter +"/";
        }
        String username = GlobalUser.getInstance().getUserName();
        Toast.makeText(myContext, username, Toast.LENGTH_LONG).show();
        CreateEvent event = new CreateEvent( GlobalUser.getInstance().getUserName(),eventNameString,
                adressString,numberString,cityString,postcodeString, stringForBenji[0],formatedDate,formatTime,
                commentString,filterString , 0+"");
        CreateEvent(event);
    }
    public void CreateEvent(CreateEvent createEvent) {
        createEventService.callLoginService(createEvent, myContext);
    }
    @OnClick({R.id.checkbox_meat,R.id.checkbox_no_meat,
            R.id.checkbox_alchohol,R.id.checkbox_no_alchohol,
            R.id.checkbox_sleep,R.id.checkbox_no_sleep})
    public void onCheckboxClicked(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();

        // Check which checkbox was clicked
        switch(view.getId()) {
            case R.id.checkbox_meat:
                if (checked)
                {
                    if(!filterList.contains("Meat")){
                        filterList.add("Meat");
                    }
                }
                else {
                    if(!filterList.contains("Meat")){
                    filterList.remove("Meat");
                    }
                }
                break;
            case R.id.checkbox_no_meat:
                if (checked)
                {
                    if(!filterList.contains("NoMeat")){
                        filterList.add("NoMeat");
                    }
                }
                else {
                    if(!filterList.contains("NoMeat")){
                        filterList.remove("NoMeat");
                    }
                }
                break;
            case R.id.checkbox_alchohol:
                if (checked)
                {
                    if(!filterList.contains("Alchohol")){
                        filterList.add("Alchohol");
                    }
                }
                else {
                    if(!filterList.contains("Alchohol")){
                        filterList.remove("Alchohol");
                    }
                }
             break;
            case R.id.checkbox_no_alchohol:
                if (checked)
                {
                    if(!filterList.contains("NoAlchohol")){
                        filterList.add("NoAlchohol");
                    }
                }
                else {
                    if(!filterList.contains("NoAlchohol")){
                        filterList.remove("NoAlchohol");
                    }
                }
                break;
            case R.id.checkbox_sleep:
                if (checked)
                {
                    if(!filterList.contains("Sleep")){
                        filterList.add("Sleep");
                    }
                }
                else {
                    if(!filterList.contains("Sleep")){
                        filterList.remove("Sleep");
                    }
                }
                break;
            case R.id.checkbox_no_sleep:
                if (checked)
                {
                    if(!filterList.contains("NoSleep")){
                        filterList.add("NoSleep");
                    }
                }
                else {
                    if(!filterList.contains("NoSleep")){
                        filterList.remove("NoSleep");
                    }
                }
                break;
        }
    }

    public class MyOnItemSelectedListener implements AdapterView.OnItemSelectedListener
    {
        public void onItemSelected(AdapterView<?> parent,View view, int pos, long id) {
            Toast.makeText(parent.getContext(), "The time is " +
                    parent.getItemAtPosition(pos).toString(), Toast.LENGTH_LONG).show();
        }
        public void onNothingSelected(AdapterView<?> parent) {
            // Do nothing.
        }
        public int posArray(AdapterView<?> parent, View view, int pos, long id)
        {
            return pos;
        }
    }

}
