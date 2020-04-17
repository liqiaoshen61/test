package com.example.loginapp;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.Toast;

public class BootingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_booting);

        Thread myThread = new Thread() {
            @Override
            public void run() {
                try {
                    sleep(3000);//使程序休眠一秒
                    if((Boolean) Util.get(BootingActivity.this,"logIn",false)){
                        Intent homepageActivityIntent = new Intent(getApplicationContext(), HomePageActivity.class);
                        homepageActivityIntent.putExtra("Start_From", "BootingActivity");
                        startActivity(homepageActivityIntent);
                        finish();
                    }else{
                        Intent loginActivityIntent = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(loginActivityIntent);
                        finish();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        myThread.start();//启动线程
    }
}
