package com.example.foodtwo.Activity;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.foodtwo.Bomb.Person;
import com.example.foodtwo.yuzhu.ImageAdapter;
import android.widget.Toast;

import com.example.foodtwo.R;
import com.example.foodtwo.yuzhu.Sendone;
import com.github.siyamed.shapeimageview.CircularImageView;
import com.winfo.photoselector.PhotoSelector;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;
import cn.bmob.v3.listener.UploadFileListener;

import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListFragment extends Fragment {
    private  TabLayout mTabLayout;
    private  ViewPager mViewPager;
    FragmentPagerAdapter mAdapter;//这你都有。。
    private Toolbar toolbar;
    private AppCompatActivity mActivity;
    private TextView username;
    private Button setting;
    private String getUsername;
    private CircularImageView ivUserProfilePhoto;
    private static final int SINGLE_CODE = 1;//单选
    private ArrayList<String> images;
    private ImageAdapter imAdapter;
    private TextView postAll;

    public static ListFragment instantiation(int position) {
        ListFragment fragment = new ListFragment();
        Bundle args = new Bundle();
        args.putInt("position", position);
        fragment.setArguments(args);
        return fragment;
    }

//    private RecyclerView[] listViews;
    private RecyclerView listView;
//    private ViewPager viewPager;
    public ListFragment() {
        // Required empty public constructor
//        listViews[0]=new RecyclerView(getContext());
//        listViews[1]=new RecyclerView(getContext());
//        listViews[2]=new RecyclerView(getContext());
//
//        listViews[0].setBackgroundColor(Color.RED);
//        listViews[1].setBackgroundColor(Color.GRAY);
//        listViews[2].setBackgroundColor(Color.BLUE);

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final BmobQuery<Person>bmobQuery=new BmobQuery<Person>();
        Intent intent=getActivity().getIntent();

        String passingID=intent.getStringExtra("passingID");
        if(passingID!=null){
            bmobQuery.getObject(passingID, new QueryListener<Person>() {
                @Override
                public void done(Person bmobUser, BmobException e) {
                    if(e==null){
                        getUsername=bmobUser.getUsername();
                        username.setText(bmobUser.getUsername());//获取登录名
                        //获取头像
                        //关注者
                    }
                }
            });

        }

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity=(AppCompatActivity)getActivity();
    }

    @Override
    public void onResume() {
        super.onResume();
//        toolbar = (Toolbar)mActivity.findViewById(R.id.toolbar);
//        toolbar.setTitle("App Title"); //设置Toolbar标题
//        toolbar.setSubtitle("Sub Title"); //设置Toolbar 副标题
//        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view=inflater.inflate(R.layout.fragment_list,container,false);
        mTabLayout = (TabLayout) view.findViewById(R.id.tablayout);
        toolbar=(Toolbar)view.findViewById(R.id.toolbar);
        username=(TextView)view.findViewById(R.id.username);

        postAll=(TextView)view.findViewById(R.id.postAll);

        setting=(Button)view.findViewById(R.id.setting);

        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(getActivity(),SettingActivity.class);
                startActivity(intent);
            }
        });
        //切换头像

        ivUserProfilePhoto=view.findViewById(R.id.ivUserProfilePhoto);
        Person currentUser=Person.getCurrentUser(Person.class);
        BmobFile touFile=currentUser.getTouxiang();
        if (touFile!=null){
            String url=touFile.getFileUrl();
            File qfile=new File(url);
            Glide.with(getContext())
                    .load(url)
                    .listener(new RequestListener<Drawable>() {

                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                            Log.d("Wain","加载失败 errorMsg:"+(e!=null?e.getMessage():"null"));
                            return false;
                        }
                        @Override
                        public boolean onResourceReady(final Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                            Log.d("Wain","成功  Drawable Name:"+resource.getClass().getCanonicalName());
                            return false;
                        }
                    })
                    .into(ivUserProfilePhoto);
        }




        ivUserProfilePhoto.setOnClickListener(new View.OnClickListener() {
            final String[] option=new String[]{"切换头像","取消"};
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
                builder.setItems(option, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                       //打开相册
                        //单选
                        if(i==0){
                            PhotoSelector.builder()
                                    .setSingle(true)
                                    .start(getActivity(), SINGLE_CODE);
                        }

                    }
                });
          builder.show();


            }
        });



//       StatusBarUtil.setColor(getActivity(),getResources().getColor(R.color.Purple)); youcuo

//        mTabLayout.addTab(mTabLayout.newTab().setText("我发布的"));
//        mTabLayout.addTab(mTabLayout.newTab().setText("我喜欢的"));
//
//        viewPager= view.findViewById(R.id.viewpager);
//        viewPager.setAdapter(new ViewPagerAdapter());

        List<Fragment>fragements=new ArrayList<Fragment>();
        fragements.add(new ListFragment1());
        fragements.add(new ListFragment1());



        mViewPager=(ViewPager)view.findViewById(R.id.viewPager);


        FragmentManager man =this.getChildFragmentManager();
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);

        mAdapter=new FragmentAdapter(man,fragements);

        mViewPager.setAdapter(mAdapter);
//
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setTabsFromPagerAdapter(mAdapter);
        mTabLayout.setTabTextColors(getResources().getColor(R.color.White),getResources().getColor(R.color.White));



        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case SINGLE_CODE:
                //单选的话 images就只有一条数据直接get(0)即可
                images = data.getStringArrayListExtra(PhotoSelector.SELECT_RESULT);

                String image=images.get(0);
                File file=new File(image);
                // Bitmap bitmap=
                //     ivUserProfilePhoto.setImageBitmap();
                Glide.with(getContext()).load(file).into(ivUserProfilePhoto);//加载到imageview上

                final BmobFile bombFile = new BmobFile(file);
                bombFile.uploadblock(new UploadFileListener() {
                    @Override
                    public void onProgress(Integer value) {
                        super.onProgress(value);
                        Toast.makeText(getContext(), "当前加载中："+value, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void done(BmobException e) {
                        if (e == null) {
                            Toast.makeText(getContext(), "上传成功" + bombFile.getFileUrl(), Toast.LENGTH_SHORT).show();
                            //获取当前用户
                             Person user=Person.getCurrentUser(Person.class);
                            user.setTouxiang(bombFile);

                            user.update(user.getObjectId(), new UpdateListener() {

                                @Override
                                public void done(BmobException e) {
                                    if (e == null) {
                                        Toast.makeText(getContext(), "更新成功！", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(getContext(), "创建数据失败：" + e.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
//                            new SaveListener<String>() {
//                                @Override
//                                public void done(String objectId, BmobException e) {
//                                    if (e == null) {
//                                        Toast.makeText(getContext(), "切换成功，返回objectId为：" + objectId, Toast.LENGTH_SHORT).show();
//                                    } else {
//                                        Toast.makeText(getContext(), "创建数据失败：" + e.getMessage(), Toast.LENGTH_SHORT).show();
//                                    }
//                                }
//                            }
                        } else {
                            Toast.makeText(getContext(),  "失败！" + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
             //   Toast.makeText(getContext(),"切换成功",Toast.LENGTH_SHORT).show();
                //  imAdapter.refresh(images);
                break;
        }


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    public class FragmentAdapter extends FragmentPagerAdapter {
        private String [] title = {"我发布的","我收藏的"};
        private List<Fragment> fragmentList;
        public FragmentAdapter(FragmentManager fm, List<Fragment> fragmentList) {
            super(fm);
            this.fragmentList = fragmentList;
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);//或返回具体的fragment并传值
        }
        @Override
        public int getCount() {
            return fragmentList.size();
        }
        @Override
        public CharSequence getPageTitle(int position) {
            return title[position];
        }
    }


//    public class ViewPagerAdapter extends PagerAdapter {
//        ViewPagerAdapter(){
//
//        }
//
//        @Override
//        public int getCount() {
//            return 0;
//        }
//
//        @Override
//        public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
//            return false;
//        }
//
//        @NonNull
//        @Override
//        public Object instantiateItem(@NonNull ViewGroup container, int position) {
//            return super.instantiateItem(container, position);
//        }
//
//        @Override
//        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
//            super.destroyItem(container, position, object);
//        }
//    }

}
