package be.pxl.com.mybbq.MyEvents;


import android.app.Activity;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import be.pxl.com.mybbq.CreateEvent.CreateEventFragment;
import be.pxl.com.mybbq.R;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyEventsFragment extends Fragment {

    private FragmentActivity myContext;
    private View view;

    public MyEventsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Activity activity) {
        myContext=(FragmentActivity) activity;
        super.onAttach(activity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_my_events, container, false);
        ButterKnife.bind(this, view);
        myContext = (FragmentActivity) getActivity();
        return view;
    }

    @OnClick(R.id.BtnCreateEvent)
    public void CreateEventClick(View view) {
        initFragment(new CreateEventFragment(),"CreateEventService","Creëer een event");
    }

    private void initFragment(Fragment toload, String tag, String title){
        myContext.setTitle(title);

        FragmentTransaction fragmentTransaction = myContext.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fram, toload, tag);
        fragmentTransaction.commit();
//        AppCompatActivity activity = (AppCompatActivity) view.getContext();
//        Fragment myFragment = new CreateEventFragment();
//        activity.getSupportFragmentManager().beginTransaction().replace(R.id.fram, myFragment).addToBackStack(null).commit();
    }
}
