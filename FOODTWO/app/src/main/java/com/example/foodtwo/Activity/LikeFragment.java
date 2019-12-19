package com.example.foodtwo.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.foodtwo.R;
import com.example.foodtwo.yuzhu.Sendone;

import java.util.ArrayList;
import java.util.List;

/**
 *第二个页卡
 * A simple {@link Fragment} subclass.
 */
public class LikeFragment extends Fragment {
//    private  ViewPager mViewPager;
//   // private Button jiahao;
//    FragmentPagerAdapter mAdapter;//这你都有。。
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        View view=inflater.inflate(R.layout.fragment_tab2, container, false);
//        List<Fragment>fragements=new ArrayList<Fragment>();
//
//        fragements.add(new ListFragmentLike());
//        mViewPager=(ViewPager)view.findViewById(R.id.viewPager);
//        FragmentManager man =this.getChildFragmentManager();
//        mAdapter=new FragmentAdapter(man,fragements);
//        mViewPager.setAdapter(mAdapter);
////        jiahao=(Button)view.findViewById(R.id.jiahao);
////        jiahao.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View view) {
////                Intent intent=new Intent(getActivity(),Sendone.class);
////                startActivity(intent);
////            }
////        });
//
//        return view;
//    }
//
//
//    public class FragmentAdapter extends FragmentPagerAdapter {
//        private List<Fragment> fragmentList;
//        public FragmentAdapter(FragmentManager fm, List<Fragment> fragmentList) {
//            super(fm);
//            this.fragmentList = fragmentList;
//        }
//
//        @Override
//        public Fragment getItem(int position) {
//            return fragmentList.get(position);//或返回具体的fragment并传值
//        }
//        @Override
//        public int getCount() {
//            return fragmentList.size();
//        }
//
//    }
}
