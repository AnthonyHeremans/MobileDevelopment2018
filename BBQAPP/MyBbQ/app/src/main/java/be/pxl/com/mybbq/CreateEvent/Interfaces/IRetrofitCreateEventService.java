package be.pxl.com.mybbq.CreateEvent.Interfaces;

import be.pxl.com.mybbq.CreateEvent.Model.CreateEvent;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface IRetrofitCreateEventService {
    @POST("EventClass/createEvent")
    Call<ResponseBody> PostNewEvent(@Body CreateEvent createEvent);
}
