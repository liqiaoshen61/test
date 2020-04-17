package com.example.loginapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.example.loginapp.Util;

public class HomePageActivity extends AppCompatActivity {
    private Button logoutBtn;
    private TextView welcomeText;
    String userName =null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        logoutBtn= (Button) findViewById(R.id.logout);
        welcomeText= (TextView) findViewById(R.id.welcomeText);
        userName = Util.get(HomePageActivity.this,"userName","").toString();
        welcomeText.setText("欢迎用户:"+userName);
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startFromIntent  = getIntent();
                if(startFromIntent.getStringExtra("Start_From").equals("BootingActivity")){
                    Intent loginActivityIntent = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(loginActivityIntent);
                }else{
                    Intent loginActivityIntent = new Intent();
                    loginActivityIntent.putExtra("USER_NAME", userName);
                    setResult(RESULT_OK, loginActivityIntent);
                    Util.put(HomePageActivity.this,"logIn",false);
                    Util.put(HomePageActivity.this,"userName","");
                    finish();
                }

            }
        });

    }
}
