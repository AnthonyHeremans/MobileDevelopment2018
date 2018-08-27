package be.pxl.com.mybbq.MyEvents.Services;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;

import be.pxl.com.mybbq.Global.GlobalUser;
import be.pxl.com.mybbq.MyEvents.CustomListViewMyEvent;
import be.pxl.com.mybbq.MyEvents.Interfaces.IMyEventService;
import be.pxl.com.mybbq.MyEvents.Interfaces.IRetrofitMyEventService;
import be.pxl.com.mybbq.MyEvents.Models.myEventClass;
import be.pxl.com.mybbq.Newsfeed.CustomListViewNewsfeed;
import be.pxl.com.mybbq.Newsfeed.Interfaces.IRetrofitNewsfeedService;
import be.pxl.com.mybbq.Newsfeed.Models.EventClass;
import be.pxl.com.mybbq.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyEventService implements IMyEventService{
    private IRetrofitMyEventService myEventService;
    private String[] title;
    private String apiUrl;
    private Context context;
    public MyEventService(Context context) {
        setContext(context);
        apiUrl = getContext().getResources().getString(R.string.APIURL);
        Retrofit retrofit = new Retrofit.Builder().baseUrl(apiUrl).addConverterFactory(GsonConverterFactory.create()).build();

        setMyEventService(retrofit.create(IRetrofitMyEventService.class));
    }

    public void setMyEventService(IRetrofitMyEventService newsfeedService) {
        this.myEventService = newsfeedService;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public void callNewsfeedMessageService(final Activity context, final ListView lst) {
        String username = GlobalUser.getInstance().getUserName();
        Call<List<myEventClass>> newsfeedCall = myEventService.getEventByPerson(username);
        newsfeedCall.enqueue(new Callback<List<myEventClass>>() {
            @Override
            public void onResponse(Call<List<myEventClass>> call, Response<List<myEventClass>> response) {
                if (response.isSuccessful()) {
                    List<myEventClass> newsfeedMessageListRes = response.body();
                    List<myEventClass> reverseList = reverseList(newsfeedMessageListRes);
                    proceed(reverseList, context, lst);
                } else {
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context,"Een fout van het ophalen van jouw events", duration);
                    toast.show();
                }
            }

            @Override
            public void onFailure(Call<List<myEventClass>> call, Throwable t) {
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
    public void proceed(final List<myEventClass> newsfeedMessageListRes, final Activity context, ListView lst)
    {
        title = new String[newsfeedMessageListRes.size()];
        for (int i = 0; i < newsfeedMessageListRes.size(); i++) {
            title[i] = newsfeedMessageListRes.get(i).getEventName();
        }
        CustomListViewMyEvent customListView = new CustomListViewMyEvent(context, title, newsfeedMessageListRes);
        lst.setAdapter(customListView);
        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context,"OnClickWorks", duration);
                toast.show();
            }
        });
    }
    public List<myEventClass> reverseList(List<myEventClass> list) {
        List<myEventClass> revertedList = new ArrayList<>();
        int size = list.size() - 1;
        for (int i = size; i >= 0; i--) {
            revertedList.add(list.get(i));
        }
        return revertedList;
    }

}
