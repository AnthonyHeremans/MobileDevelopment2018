package be.pxl.com.mybbq.CreateEvent.Interfaces;

import android.content.Context;

import be.pxl.com.mybbq.CreateEvent.Model.CreateEvent;

public interface ICreateEventService {
    void callLoginService(CreateEvent createEvent, final Context context);
}
