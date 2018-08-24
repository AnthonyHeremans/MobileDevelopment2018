package be.pxl.com.mybbq.Login.Interfaces;

import android.content.Context;

public interface ILoginService {
    void callLoginService(String username, String password, final Context context);
    void setContext(Context context);
}
