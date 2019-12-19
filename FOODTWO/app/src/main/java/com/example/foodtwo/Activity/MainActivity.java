package com.example.foodtwo.Activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.foodtwo.R;
import com.example.foodtwo.yuzhu.Sendone;

import java.util.List;


public class MainActivity extends AppCompatActivity {

    private TabFragment2 tabFragment2;
    private TabFragment1 tabFragment1;
    private ListFragment listFragment;

    private RadioButton radioButton1;
    private RadioButton radioButton2;
    private RadioButton radioButton3;


    private FragmentManager manager=getSupportFragmentManager();
    private Fragment currentFragment=new Fragment();
    private TabFragment1 TabFragment1=new TabFragment1();
    private TabFragment2 TabFragment2=new TabFragment2();
    private ListFragment ListFragment=new ListFragment();
    private FloatingActionButton floatingActionButton;

    private Button jiahao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);//去标题
        setContentView(R.layout.activity_main);
        floatingActionButton=(FloatingActionButton)findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, Sendone.class);
                startActivity(intent);
            }
        });


//        //透明状态栏
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//        //透明导航栏
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//        //透明导航栏




//        requestWindowFeature(Window.FEATURE_NO_TITLE);//隐藏标题


//        toolbar=(Toolbar)findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);




//        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
//        mToolbar.setTitle("App Title"); //设置Toolbar标题
//        mToolbar.setSubtitle("Sub Title"); //设置Toolbar 副标题
//        mToolbar.setLogo(R.mipmap.ic_launcher);//设置Toolbar的Logo
//        mToolbar.setNavigationIcon(R.mipmap.abc_ic_ab_back_mtrl_am_alpha);
//        setSupportActionBar(mToolbar);


//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,  WindowManager.LayoutParams.FLAG_FULLSCREEN);//设置全屏




//        StatusBarCompat.setStatusBarColor(this, 000000);





        if(listFragment==null){
            listFragment = new ListFragment();
        }


        select(1);
        initView();




//        jiahao=(Button)findViewById(R.id.jiahao);
//        jiahao.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent=new Intent(MainActivity.this, Send.class);
//                startActivity(intent);
//            }
//        });







    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (getSupportFragmentManager().getFragments() != null && getSupportFragmentManager().getFragments().size() > 0) {
            List<Fragment> fragments = getSupportFragmentManager().getFragments();
            for (Fragment mFragment : fragments) {
                mFragment.onActivityResult(requestCode, resultCode, data);
            }
        }
    }


    private void initView(){
        radioButton1=(RadioButton)findViewById(R.id.map_tab);
        Drawable drawable1=getResources().getDrawable(R.drawable.icon_map_default);
        drawable1.setBounds(0,0,60,60);
        radioButton1.setCompoundDrawables(null,drawable1,null,null);


        radioButton1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                radioButton3.setChecked(true);
                if(radioButton1.isChecked()==true){
                    Drawable drawable1=getResources().getDrawable(R.drawable.aixin_pressed);
                    drawable1.setBounds(0,0,60,60);;
                    radioButton1.setCompoundDrawables(null,drawable1,null,null);
                }else {
                    Drawable drawable1=getResources().getDrawable(R.drawable.icon_map_default);
                    drawable1.setBounds(0,0,60,60);
                    radioButton1.setCompoundDrawables(null,drawable1,null,null);
                }
            }
        });









        radioButton2=(RadioButton)findViewById(R.id.mine_tab);
        Drawable drawable3=getResources().getDrawable(R.mipmap.icon_mine_default);
        drawable3.setBounds(0,0,60,60);
        radioButton2.setCompoundDrawables(null,drawable3,null,null);
        radioButton2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                radioButton3.setChecked(true);
                if(radioButton2.isChecked()==true){
                    Drawable drawable3=getResources().getDrawable(R.mipmap.wode_pressed);
                    drawable3.setBounds(0,0,60,60);
                    radioButton2.setCompoundDrawables(null,drawable3,null,null);
                }else {
                    Drawable drawable3=getResources().getDrawable(R.mipmap.icon_mine_default);
                    drawable3.setBounds(0,0,60,60);
                    radioButton2.setCompoundDrawables(null,drawable3,null,null);
                }
            }
        });



        radioButton3=(RadioButton)findViewById(R.id.info_tab);
        Drawable drawable2=getResources().getDrawable(R.mipmap.faxian_pressed);
        drawable2.setBounds(0,0,60,60);
        radioButton3.setCompoundDrawables(null,drawable2,null,null);
        radioButton3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(radioButton3.isChecked()==true){
                    Drawable drawable2=getResources().getDrawable(R.mipmap.faxian_pressed);
                    drawable2.setBounds(0,0,60,60);
                    radioButton3.setCompoundDrawables(null,drawable2,null,null);
                }else {
                    Drawable drawable2=getResources().getDrawable(R.mipmap.icon_info_default);
                    drawable2.setBounds(0,0,60,60);
                    radioButton3.setCompoundDrawables(null,drawable2,null,null);
                }
            }
        });





        radioButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                select(2);

            }
        });

        radioButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                select(0);
            }
        });

        radioButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                select(1);//首页



            }
        });




    }

    public void select(int i){
     //   FragmentManager fragmentManager=getSupportFragmentManager();
    //    FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        //转场//fragmentTransaction.setCustomAnimations(R.anim.)

        switch (i){
            case 0:
//            if(listFragment==null){
//                listFragment = new ListFragment();
//            }
//                fragmentTransaction.replace(R.id.fragment_container,listFragment);
                showFragment(ListFragment);
                break;
            case 1://首页
//                if(tabFragment2==null){
//                    tabFragment2=new TabFragment2();
//                }
//                fragmentTransaction.replace(R.id.fragment_container,tabFragment2);
                showFragment(TabFragment2);
                break;
            case 2://喜欢
//                if(tabFragment1==null){
//                    tabFragment1=new TabFragment1();
//                }
//                fragmentTransaction.replace(R.id.fragment_container,tabFragment1);
                showFragment(TabFragment1);
                break;
                default:break;
        }
       // fragmentTransaction.commit();
    }

private void showFragment (Fragment fragment){
        if(currentFragment!=fragment){
            FragmentTransaction transaction=manager.beginTransaction();
            transaction.hide(currentFragment);
            currentFragment=fragment;
            if(fragment.isAdded()==false){
                transaction.add(R.id.fragment_container,fragment).show(fragment).commit();
            }else {
                transaction.show(fragment).commit();
            }
        }
}
}
