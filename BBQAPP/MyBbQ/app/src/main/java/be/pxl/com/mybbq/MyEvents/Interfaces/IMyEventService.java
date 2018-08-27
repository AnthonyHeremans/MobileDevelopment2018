package be.pxl.com.mybbq.MyEvents.Interfaces;

import android.app.Activity;
import android.widget.ListView;

import java.util.List;

import be.pxl.com.mybbq.MyEvents.Models.myEventClass;

public interface IMyEventService {
    void callNewsfeedMessageService(final Activity context, final ListView lst);
    void proceed(final List<myEventClass> newsfeedMessageListRes, final Activity context, ListView lst);
}
