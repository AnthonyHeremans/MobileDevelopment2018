package be.pxl.com.mybbq.Login.Services;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import java.net.SocketTimeoutException;

import be.pxl.com.mybbq.Login.Interfaces.ILoginService;
import be.pxl.com.mybbq.Login.Interfaces.IRetrofitLoginService;
import be.pxl.com.mybbq.Login.Models.User;
import be.pxl.com.mybbq.MainActivity;
import be.pxl.com.mybbq.R;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginService implements ILoginService {
    private IRetrofitLoginService loginService;
    private String apiUrl;
    private Context context;

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public LoginService(Context context)
    {   setContext(context);
        apiUrl = getContext().getResources().getString(R.string.APIURL);
        Retrofit retrofit = new Retrofit.Builder().baseUrl(apiUrl).addConverterFactory(GsonConverterFactory.create()).build();

        setLoginService(retrofit.create(IRetrofitLoginService.class));
    }

    public void setLoginService(IRetrofitLoginService loginService) {
        this.loginService = loginService;
    }

    @Override
    public void callLoginService(String username, String password, final Context context)
    {
        User loginTryUser = new User(username,password);
        Call<ResponseBody> profileCall = loginService.getUser(loginTryUser);
        //Call<User> profileCall = loginService.getUser(loginTryUser);
        profileCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful())
                {
                    ResponseBody userLogin = response.body();
                    proceed(context);
                }
                else
                {
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context,"Incorrect login Details", duration);
                    toast.show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                if (t instanceof SocketTimeoutException) {

                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context,"Login Service currently unavailble try again later", duration);
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

    private void proceed(Context context){
        Intent startHomeActivity = new Intent(context, MainActivity.class);
        startHomeActivity.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(startHomeActivity);
    }
}
