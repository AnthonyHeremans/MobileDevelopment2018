package be.pxl.com.mybbq.Newsfeed;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import be.pxl.com.mybbq.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CustomEventListLayout extends Fragment {


    public CustomEventListLayout() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_custom_event_list_layout, container, false);
    }

}
