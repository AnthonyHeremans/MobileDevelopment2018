package be.pxl.com.mybbq.Newsfeed.Services;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;

import be.pxl.com.mybbq.EventDetail.EventDetailFragment;
import be.pxl.com.mybbq.Newsfeed.CustomEventListLayout;
import be.pxl.com.mybbq.Newsfeed.CustomListViewNewsfeed;
import be.pxl.com.mybbq.Newsfeed.Interfaces.INewsfeedService;
import be.pxl.com.mybbq.Newsfeed.Interfaces.IRetrofitNewsfeedService;
import be.pxl.com.mybbq.Newsfeed.Models.EventClass;
import be.pxl.com.mybbq.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NewsfeedService implements INewsfeedService {
    private IRetrofitNewsfeedService newsfeedService;
    private String[] title;
    private String apiUrl;
    private Context context;

    public NewsfeedService(Context context) {
        setContext(context);
        apiUrl = getContext().getResources().getString(R.string.APIURL);
        Retrofit retrofit = new Retrofit.Builder().baseUrl(apiUrl).addConverterFactory(GsonConverterFactory.create()).build();

        setNewsfeedService(retrofit.create(IRetrofitNewsfeedService.class));
    }

    public void setNewsfeedService(IRetrofitNewsfeedService newsfeedService) {
        this.newsfeedService = newsfeedService;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

//    @Override
//    public void onActivityCreated(Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//    }
    @Override
    public void callNewsfeedMessageService(final Activity context, final ListView lst) {
        Call<List<EventClass>> newsfeedCall = newsfeedService.getAllNewsfeeds();
        newsfeedCall.enqueue(new Callback<List<EventClass>>() {
            @Override
            public void onResponse(Call<List<EventClass>> call, Response<List<EventClass>> response) {
                if (response.isSuccessful()) {
                    List<EventClass> newsfeedMessageListRes = response.body();
                    List<EventClass> reverseList = reverseList(newsfeedMessageListRes);
                   // newsfeedWrapper.setNewsfeedMessageList(reverseList);
                    proceed(reverseList, context, lst);
                } else {
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context,"Een fout van het ophalen van de newsfeed", duration);
                    toast.show();
                }
            }

            @Override
            public void onFailure(Call<List<EventClass>> call, Throwable t) {
                if (t instanceof SocketTimeoutException) {
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context,"Event Service currently unavailble try again later", duration);
                    toast.show();

                } else {
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context,"Something went wrong", duration);
                    toast.show();
                }
            }
        });
    }
    private void proceed(final List<EventClass> newsfeedMessageListRes, final Activity context, ListView lst)
    {
        title = new String[newsfeedMessageListRes.size()];
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
               android.support.v4.app.Fragment mfragment = new EventDetailFragment();
                    EventClass newsObj = newsfeedMessageListRes.get(position);
                    Bundle bundle = new Bundle();

                    Gson gson = new Gson();
                    // 2. Java object to JSON, and assign to a String
                     String jsonInString = gson.toJson(newsObj);
                  bundle.putString("eventObject",jsonInString);
//                bundle.putLong("Date",newsObj.getTimestamp());
//                bundle.putString("Admin",newsObj.getName());
//                bundle.putString("longMessage", newsObj.getFullDescription());
//
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

