package com.example.foodtwo.Test;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.RadioButton;

import com.example.foodtwo.Activity.ListFragment;
import com.example.foodtwo.Activity.TabFragment1;
import com.example.foodtwo.Activity.TabFragment2;
import com.example.foodtwo.R;


public class MainActivityTEst extends FragmentActivity implements CompoundButton.OnCheckedChangeListener, View.OnClickListener {

    private TabFragment2 tabFragment2;
    private TabFragment1 tabFragment1;
    private ListFragment listFragment;

    private RadioButton radioButton1;
    private RadioButton radioButton2;
    private RadioButton radioButton3;

    private FrameLayout mContainer;


    private Button jiahao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(listFragment==null){
            listFragment = new ListFragment();
        }


      //  select(1);
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





//        radioButton1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                select(2);
//
//            }
//        });
//
//        radioButton2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                select(0);
//            }
//        });
//
//        radioButton3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                select(1);//首页
//
//
//
//            }
//        });


        mContainer=(FrameLayout)findViewById(R.id.fragment_container);
        radioButton1.setOnCheckedChangeListener(this);
        radioButton2.setOnCheckedChangeListener(this);
        radioButton3.setOnCheckedChangeListener(this);
        radioButton1.setOnClickListener(this);
        radioButton2.setOnClickListener(this);
        radioButton3.setOnClickListener(this);

    }
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            Fragment fragment = (Fragment) mFragmentPagerAdapter
                    .instantiateItem(mContainer, buttonView.getId());
            mFragmentPagerAdapter.setPrimaryItem(mContainer, 0, fragment);
            mFragmentPagerAdapter.finishUpdate(mContainer);
        }
    }
    private FragmentPagerAdapter mFragmentPagerAdapter = new FragmentPagerAdapter(
            getSupportFragmentManager()) {
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case R.id.info_tab:
                    return FragmentTest.instantiation(2);
                case R.id.map_tab:
                    return FragmentTest.instantiation(3);
                default:
                    return FragmentTest.instantiation(1);
            }
        }
        @Override
        public int getCount() {
            return 3;
        }
    };
    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
    }




//    public void select(int i){
//        FragmentManager fragmentManager=getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
//
//        switch (i){
//            case 0:
//            if(listFragment==null){
//                listFragment = new ListFragment();
//            }
//                fragmentTransaction.replace(R.id.fragment_container,listFragment);
//            break;
//            case 1://首页
//                if(tabFragment2==null){
//                    tabFragment2=new TabFragment2();
//                }
//                fragmentTransaction.replace(R.id.fragment_container,tabFragment2);
//                break;
//            case 2://喜欢
//                if(tabFragment1==null){
//                    tabFragment1=new TabFragment1();
//                }
//                fragmentTransaction.replace(R.id.fragment_container,tabFragment1);
//                break;
//                default:break;
//        }
//        fragmentTransaction.commit();
//    }






}
