package com.example.loginapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";

    private final int REQUEST_CODE = 0;
    private Button regBtn;
    private Button loginBtn;
    private EditText count;
    private EditText pwd;
    private TextView state;
    private CheckBox checkbox;

    private List<User> userList;
    private List<User> dataList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        regBtn= (Button) findViewById(R.id.regin);
        loginBtn= (Button) findViewById(R.id.login);
        count= (EditText) findViewById(R.id.count);
        pwd= (EditText) findViewById(R.id.pwd);
        state= (TextView) findViewById(R.id.state);
        checkbox =(CheckBox)findViewById(R.id.checkbox);

        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=count.getText().toString().trim();
                String pass=pwd.getText().toString().trim();

                User user=new User();

                if (name.trim().equals("") ||pass.trim().equals("") ) {
                    Toast.makeText(LoginActivity.this, "输入不能为空", Toast.LENGTH_SHORT).show();
                }else{
                    user.setUsername(name);
                    user.setUserpwd(pass);
                    int result=SqliteDB.getInstance(getApplicationContext()).saveUser(user);
                    if (result==1){
                        state.setText("注册成功！");
                    }else  if (result==-1)
                    {
                        state.setText("用户名已经存在！");
                    }
                    else
                    {
                        state.setText("！");
                    }

                }
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=count.getText().toString().trim();
                String pass=pwd.getText().toString().trim();
                state.setText("");
                //userList=SqliteDB.getInstance(getApplicationContext()).loadUser();
                int result=SqliteDB.getInstance(getApplicationContext()).Quer(pass,name);
                if (result==1)
                {
                    count.setText("");
                    pwd.setText("");
                    Intent HomePageActivityIntent = new Intent(getApplicationContext(), HomePageActivity.class);
                    HomePageActivityIntent.putExtra("Start_From", "LoginActivity");
                    startActivityForResult(HomePageActivityIntent,REQUEST_CODE);
                    Util.put(LoginActivity.this,"logIn",true);
                    Util.put(LoginActivity.this,"userName",name);
                }
                else if (result==0){
                    state.setText("用户名不存在！");

                }
                else if(result==-1)
                {
                    state.setText("密码错误！");
                }
/*                for (User user : userList) {

                    if (user.getUsername().equals(name))
                    {
                        if (user.getUserpwd().equals(pass))
                        {
                            state.setText("登录成功！");

                        }else {
                            state.setText("密码错误！");
                        }
                    }
                    else {
                        state.setText("用户名不存在！");
                    }
                }*/
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_CODE) {
                if(checkbox.isChecked())
                    count.setText(data.getExtras().getString("USER_NAME") );
            }
        }
    }





}
