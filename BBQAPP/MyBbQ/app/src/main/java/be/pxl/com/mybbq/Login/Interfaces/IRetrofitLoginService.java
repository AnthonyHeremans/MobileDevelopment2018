package be.pxl.com.mybbq.Login.Interfaces;

import be.pxl.com.mybbq.Login.Models.User;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface IRetrofitLoginService {
    @POST("User/getUser")
   // Call<User> getUser(@Body User user);
    Call<ResponseBody> getUser(@Body User user);

   // Call<ResponseBody> findUser(@Query("username") String username, @Query("password") String password);
}
