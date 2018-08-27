package be.pxl.com.mybbq.Newsfeed.Interfaces;

import android.app.Activity;
import android.widget.ListView;

import be.pxl.com.mybbq.Newsfeed.Models.EventClass;

public interface INewsfeedService {
    void callNewsfeedMessageService(Activity context, ListView lst);
}
