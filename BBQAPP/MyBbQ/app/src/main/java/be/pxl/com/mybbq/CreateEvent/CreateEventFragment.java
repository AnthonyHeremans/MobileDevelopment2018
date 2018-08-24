package be.pxl.com.mybbq.CreateEvent;


import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import be.pxl.com.mybbq.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class CreateEventFragment extends Fragment{

//    @BindView(R.id.UserCount_spinner)
//    Spinner spinnerUserCount;

    Context myContext;
    public CreateEventFragment() {
        // Required empty public constructor
    }
    final String[] stringForBenji = new String[1];

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
            myContext = (FragmentActivity) getActivity();

            // Inflate the layout for this fragment
          View view =  inflater.inflate(R.layout.fragment_create_event, container, false);

            Spinner spinner = (Spinner) view.findViewById(R.id.UserCount_spinner);
    // Create an ArrayAdapter using the string array and a default spinner layout
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this.getActivity(),
                    R.array.userCountArray, android.R.layout.simple_spinner_item);
    // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    // Apply the adapter to the spinner
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
