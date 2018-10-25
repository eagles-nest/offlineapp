package com.evans.offlineapp;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText uname,pass;
    private Button button;
    private String username,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        uname=(EditText)findViewById(R.id.txtusername);
        pass=(EditText)findViewById(R.id.txtpassword);
        button=(Button)findViewById(R.id.button);

    }
    //checks if internet connection available
    public boolean checkInternet(Context context){
        ConnectivityManager cm
                =(ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
        return isConnected;
    }
    //login
    public void login(View view){
        String method="login";
        username=uname.getText().toString().trim();
        password=pass.getText().toString().trim();
        boolean isConnect=checkInternet(MainActivity.this);
        if(isConnect){
            // has internet
            BackgroundTask backgroundTask = new BackgroundTask(MainActivity.this);
            backgroundTask.execute(method,username,password);

        }else{
            //no internet
            Toast.makeText(MainActivity.this, "No internet", Toast.LENGTH_LONG).show();
        }
    }

}
