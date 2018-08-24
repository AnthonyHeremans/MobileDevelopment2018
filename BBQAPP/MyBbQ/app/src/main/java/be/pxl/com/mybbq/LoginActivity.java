package be.pxl.com.mybbq;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        this.loginService = new LoginService(getApplicationContext());
        //this.loginService.setContext(getApplicationContext());

    }

    public void loginClick(View view) {
        //Clicked on login
        String username = loginField.getText().toString();
        String password = passwordField.getText().toString();
        if (!username.trim().equals("") && !password.trim().equals("")) {
            login(username, password);
        } else {
            Context context = getApplicationContext();
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, R.string.PleaseFillInAllFields, duration);
            toast.show();
        }
    }
    public void login(String username, String password) {
       // ILoginService loginService = new LoginService();
        loginService.callLoginService(username, password, getApplicationContext());
        //Check if user exsist in API
//        Intent startHomeActivity = new Intent(getApplicationContext(), MainActivity.class);
//        startHomeActivity.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        getApplicationContext().startActivity(startHomeActivity);
//        Intent intent = new Intent(LoginActivity.this,
//                MainActivity.class);
//        startActivity(intent);
    }

    public void loginClickOther(View view) {

    }


}
