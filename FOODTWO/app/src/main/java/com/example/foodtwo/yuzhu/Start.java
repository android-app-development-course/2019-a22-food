package com.example.foodtwo.yuzhu;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodtwo.Bomb.Person;
import com.example.foodtwo.R;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
//import com.sina.weibo.sdk.api.*;
//import com.sina.weibo.sdk.common.UiError;
//import com.sina.weibo.sdk.openapi.IWBAPI;
//import com.sina.weibo.sdk.openapi.WBAPIFactory;
import java.lang.String.*;

public class Start extends AppCompatActivity {
    private ImageView taste;
    private ImageView bowl;
    private ImageView rice;
    private TextView food;



//    //在微博开发平台为应用申请的App Key
//    private static final String APP_KY = "2998417758";
//    //在微博开放平台设置的授权回调页
//    private static final String REDIRECT_URL = "http://api.weibo.com/oauth2/default.html";
//    //在微博开放平台为应用申请的高级权限
//    private static final String SCOPE =
//            "email,direct_messages_read,direct_messages_write,"
//                    + "friendships_groups_read,friendships_groups_write,statuses_to_me_read,"
//                    + "follow_app_official_microblog," + "invitation_write";
//    private IWBAPI mWBAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        setContentView(R.layout.activity_start);
        taste = findViewById(R.id.taste);
        bowl = findViewById(R.id.bowl);
        rice = findViewById(R.id.rice);
        food = findViewById(R.id.food_name);

        ObjectAnimator bowlMove = ObjectAnimator.ofFloat(bowl,"rotation",0f,360f).setDuration(1100);
        bowlMove.start();
        ObjectAnimator riceMove = ObjectAnimator.ofFloat(rice,"translationY",0f,510f).setDuration(1100);
        riceMove.setInterpolator(new AccelerateDecelerateInterpolator());
        riceMove.start();
        taste.setVisibility(View.INVISIBLE);
        food.setVisibility(View.INVISIBLE);
        riceMove.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                taste.setVisibility(View.VISIBLE);
                food.setVisibility(View.VISIBLE);
                moveIn();
            }
        });
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Start.this, Loggin.class);
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(Start.this).toBundle());
//                overridePendingTransition(R.transition.in_anim,R.transition.out_anim);
//                finish();
//                overridePendingTransition(R.transition.in_anim,R.transition.out_anim);
//                getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);

            }
        },4000);

    }
    public void moveIn(){
        AlphaAnimation alphaAnimation=new AlphaAnimation(0.0f, 1.0f);
        alphaAnimation.setDuration(2500);
        taste.startAnimation(alphaAnimation);
        food.startAnimation(alphaAnimation);
    }
}
