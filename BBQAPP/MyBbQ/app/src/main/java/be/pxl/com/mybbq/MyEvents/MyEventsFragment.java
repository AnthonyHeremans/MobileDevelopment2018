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
import android.widget.ListView;
import android.widget.Toast;

import be.pxl.com.mybbq.CreateEvent.CreateEventFragment;
import be.pxl.com.mybbq.MyEvents.Interfaces.IMyEventService;
import be.pxl.com.mybbq.MyEvents.Services.MyEventService;
import be.pxl.com.mybbq.Newsfeed.Services.NewsfeedService;
import be.pxl.com.mybbq.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyEventsFragment extends Fragment {

    @BindView(R.id.lstMyList)
    ListView list;

    private FragmentActivity myContext;
    private View view;

    private IMyEventService myEventService;

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

        try {
            myContext = (FragmentActivity) getActivity();
            this.myEventService = new MyEventService(myContext);
            myEventService.callNewsfeedMessageService((FragmentActivity)getActivity(), list);

        } catch (Exception e) {
            int duration = Toast.LENGTH_LONG;
            Toast toast = Toast.makeText(getActivity(),e.toString(), duration);
            toast.show();
        }
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
    }
}
