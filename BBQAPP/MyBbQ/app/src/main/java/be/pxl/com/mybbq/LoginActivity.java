package be.pxl.com.mybbq;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import be.pxl.com.mybbq.Global.GlobalUser;
import be.pxl.com.mybbq.Login.Interfaces.ILoginService;
import be.pxl.com.mybbq.Login.Services.LoginService;
import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.TxtLogin)
    EditText loginField;
    @BindView(R.id.TxtPassword)
    EditText passwordField;

    private ILoginService loginService;

    //singleton
    private GlobalUser globalUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        setGlobalUser(GlobalUser.getInstance());
        this.loginService = new LoginService(getApplicationContext());
    }

    public void loginClick(View view) {
        //Clicked on login
        String username = loginField.getText().toString();
        String password = passwordField.getText().toString();
        if (!username.trim().equals("") && !password.trim().equals("")) {
            globalUser.setUserName(username);
            login(username, password);
        } else {
            Context context = getApplicationContext();
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, R.string.PleaseFillInAllFields, duration);
            toast.show();
        }
    }
    public void login(String username, String password) {
        loginService.callLoginService(username, password, getApplicationContext());
    }

    public void setGlobalUser(GlobalUser globalUser) {
        this.globalUser = globalUser;
    }

}
