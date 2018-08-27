package be.pxl.com.mybbq.Newsfeed.Interfaces;

import java.util.List;

import be.pxl.com.mybbq.Newsfeed.Models.EventClass;
import retrofit2.Call;
import retrofit2.http.GET;

public interface IRetrofitNewsfeedService {
    @GET("EventClass")
    Call<List<EventClass>> getAllNewsfeeds();
}
