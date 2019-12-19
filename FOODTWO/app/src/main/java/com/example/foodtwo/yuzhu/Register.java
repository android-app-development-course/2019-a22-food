package com.example.foodtwo.yuzhu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.foodtwo.Bomb.Person;
import com.example.foodtwo.Bomb.User;
import com.example.foodtwo.R;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class Register extends AppCompatActivity {
private EditText Username;
private EditText Password;
private EditText mail;
private String sUsername;
    private String sPassword;
    private String smail;
    private Button registerButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Username=(EditText)findViewById(R.id.editText);
        Password=(EditText)findViewById(R.id.editText2);
     //   mail=(EditText)findViewById(R.id.editText3);

        View decorView=Register.this.getWindow().getDecorView();
       decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
       // sUsername=Username.getText().toString();
       // sPassword=Password.getText().toString();
       // smail=mail.getText().toString();
        //Bomb初始化
        Bmob.initialize(this, "d122d352185fc32299b18f706b1cdf78");

        registerButton=(Button)findViewById(R.id.button4);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //创建person表并且添加一条数据
               // User user=new User ();
                Person user=new Person();
                user.setUsername(Username.getText().toString());
                user.setPassword(Password.getText().toString());
               // user.setEmail(mail.getText().toString());
//                user.signUp(new SaveListener<String>() {
//                    @Override
//                    public void done(String objectId, BmobException e) {

                user.signUp(new SaveListener<User>() {
                    @Override
          public void done(User user, BmobException e) {
                        if(e==null){
                            Toast.makeText(Register.this, "注册成功！", Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(Register.this,Loggin.class);
                            startActivity(intent);
                        }else{
                            Toast.makeText(Register.this, "创建数据失败：" + e.getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    }
                });
            }
        });



    }
}
