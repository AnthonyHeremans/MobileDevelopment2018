package be.pxl.com.mybbq.MyEvents.Interfaces;

import java.util.List;

import be.pxl.com.mybbq.Login.Models.User;
import be.pxl.com.mybbq.MyEvents.Models.myEventClass;
import be.pxl.com.mybbq.Newsfeed.Models.EventClass;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface IRetrofitMyEventService {
    @POST("EventClass/getEventByPerson")
    Call<List<myEventClass>> getEventByPerson(@Body String person);
}
