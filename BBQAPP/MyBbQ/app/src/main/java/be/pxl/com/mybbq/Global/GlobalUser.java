package be.pxl.com.mybbq.Global;

import android.content.Context;
import android.util.Property;

import java.util.Properties;

public class GlobalUser {
    private static final GlobalUser ourInstance = new GlobalUser();

    private String userName;
    private Properties prop;

    public void setProp(Properties prop) {
        this.prop = prop;
    }

    public static GlobalUser getInstance() {
        return ourInstance;
    }

    private GlobalUser() {
        setProp(new Properties());
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName(){
        return userName;
    }
}
