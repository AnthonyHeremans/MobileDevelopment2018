package be.pxl.com.mybbq.Newsfeed;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatDelegate;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import be.pxl.com.mybbq.CreateEvent.Services.CreateEventService;
import be.pxl.com.mybbq.Newsfeed.Interfaces.INewsfeedService;
import be.pxl.com.mybbq.Newsfeed.Services.NewsfeedService;
import be.pxl.com.mybbq.R;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewsfeedFragment extends Fragment {

    @BindView(R.id.lstNewsfeed)
    ListView list;

    private FragmentActivity myContext;
    private INewsfeedService newsfeedService;

    public NewsfeedFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_newsfeed, container, false);
        ButterKnife.bind(this, view);
        try {
            myContext = (FragmentActivity) getActivity();
            this.newsfeedService = new NewsfeedService(myContext);
            newsfeedService.callNewsfeedMessageService((FragmentActivity)getActivity(), list);

        } catch (Exception e) {
            int duration = Toast.LENGTH_LONG;
            Toast toast = Toast.makeText(getActivity(),e.toString(), duration);
            toast.show();
        }
        return view;
    }

}
