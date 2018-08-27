package be.pxl.com.mybbq.CreateEvent.Services;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Toast;

import java.net.SocketTimeoutException;

import be.pxl.com.mybbq.CreateEvent.CreateEventFragment;
import be.pxl.com.mybbq.CreateEvent.Interfaces.ICreateEventService;
import be.pxl.com.mybbq.CreateEvent.Interfaces.IRetrofitCreateEventService;
import be.pxl.com.mybbq.CreateEvent.Model.CreateEvent;
import be.pxl.com.mybbq.Login.Interfaces.IRetrofitLoginService;
import be.pxl.com.mybbq.Login.Models.User;
import be.pxl.com.mybbq.Newsfeed.NewsfeedFragment;
import be.pxl.com.mybbq.R;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CreateEventService implements ICreateEventService {
    private IRetrofitCreateEventService createEventService;
    private String apiUrl;
    private Context context;

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public CreateEventService(Context context)
    {   setContext(context);
        apiUrl = getContext().getResources().getString(R.string.APIURL);
        Retrofit retrofit = new Retrofit.Builder().baseUrl(apiUrl).addConverterFactory(GsonConverterFactory.create()).build();

        setCreateEventService(retrofit.create(IRetrofitCreateEventService.class));
    }

    public void setCreateEventService(IRetrofitCreateEventService createEventService) {
        this.createEventService = createEventService;
    }
    private void proceed(Context context){
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context,"EVENT IS TOEGEVOEGD", duration);
        toast.show();

    }

    @Override
    public void callLoginService(CreateEvent createEvent, final Context context)
    {
        Call<ResponseBody> profileCall = createEventService.PostNewEvent(createEvent);
        profileCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful())
                {
                    ResponseBody eventResponse = response.body();
                    proceed(context);
                }
                else
                {
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context,"Fout in het toevoegen van Event", duration);
                    toast.show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                if (t instanceof SocketTimeoutException) {

                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context,"Event Service currently unavailble try again later", duration);
                    toast.show();

                }
                else {
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context,"Something went wrong", duration);
                    toast.show();
                }
            }


        });
    }
}
