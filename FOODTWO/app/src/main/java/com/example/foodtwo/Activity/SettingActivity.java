package com.example.foodtwo.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.foodtwo.Bomb.Person;
import com.example.foodtwo.R;
import com.example.foodtwo.yuzhu.Send;
import com.example.foodtwo.yuzhu.Start;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

public class SettingActivity extends AppCompatActivity  {
    private Button button_exit;
    private Button change_pwd;
    private EditText oldpwd;
    private EditText newpwd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        //数据库
        Bmob.initialize(this, "d122d352185fc32299b18f706b1cdf78");

        button_exit = (Button) findViewById(R.id.button6);
        button_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SettingActivity.this, Start.class);
                startActivity(intent);
            }
        });
        //修改密码
        change_pwd = findViewById(R.id.button7);
        oldpwd = findViewById(R.id.oldpwd);
        newpwd = findViewById(R.id.newpwd);
        change_pwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Person person = Person.getCurrentUser(Person.class);
                person.updateCurrentUserPassword(oldpwd.getText().toString(), newpwd.getText().toString(), new UpdateListener() {
                    @Override
                    public void done(BmobException e) {
                        if (e == null) {
                            Toast.makeText(SettingActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(SettingActivity.this, "修改失败" + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }

                });
            }
        });
    }
}
