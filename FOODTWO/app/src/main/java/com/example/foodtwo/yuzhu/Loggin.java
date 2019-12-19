package com.example.foodtwo.yuzhu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.transition.Fade;
import android.transition.Slide;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodtwo.Activity.MainActivity;
import com.example.foodtwo.Bomb.Person;
import com.example.foodtwo.R;
import com.example.foodtwo.Test.MainActivityTEst;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;


public class Loggin extends AppCompatActivity {
    public String passingID;
    private EditText account;
    private EditText password;
    private Button login;
    private Button logout;
    private TextView register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loggin);

        Bmob.initialize(this, "d122d352185fc32299b18f706b1cdf78");

        Slide slide = new Slide(Gravity.RIGHT);
        slide.setDuration(500);
        getWindow().setEnterTransition(slide);
        getWindow().setExitTransition(new Fade());

        //跳转到注册
        register=(TextView)findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(Loggin.this,Register.class);
                startActivity(intent);
            }
        });

      //  logout =  findViewById(R.id.logout);
        account =  findViewById(R.id.account);//账号(邮箱)
        password =  findViewById(R.id.pwd);//密码
        login =  findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Person userlogin=new Person ();
                userlogin.setUsername(account.getText().toString());
                userlogin.setPassword(password.getText().toString());
                userlogin.login(new SaveListener<BmobUser>() {

                    @Override
                    public void done(BmobUser bmobUser, BmobException e) {

                        if(e==null){
                            passingID=bmobUser.getObjectId();
                            Toast.makeText(Loggin.this,bmobUser.getUsername()+"登录成功", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(Loggin.this, MainActivity.class);
                            intent.putExtra("passingID",bmobUser.getObjectId());
                            startActivity(intent);
                        }else {
                            Log.e("登录失败", "原因: ", e);
                        }
                    }
                });

            }
        });
    }
}
