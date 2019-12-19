package com.example.foodtwo.Activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.foodtwo.Bomb.Person;
import com.example.foodtwo.Bomb.Post;
import com.example.foodtwo.Bomb.PostLike;
import com.example.foodtwo.R;
import com.loopj.android.image.SmartImageView;
import com.yalantis.phoenix.PullToRefreshView;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class ListFragmentLike extends Fragment {
    private RecyclerView listView;
    private SwipeRefreshLayout swipeRefresh;
    private PullToRefreshView mPullToRefreshView;
    private List<Post> list;
    private TextView yonghuming;
    private TextView wenzineirong;
    private TextView tag;
   private SmartImageView neirong;
    private String imageUrl;
    private Bitmap bitmap;
    private ImageView touxiang;

   private ArrayList<Bitmap>arrayList=null;
    private int i=0;
    public ListFragmentLike() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list1, container, false);
        //Bomb初始化
        Bmob.initialize(getContext(), "d122d352185fc32299b18f706b1cdf78");
        mPullToRefreshView = (PullToRefreshView) view.findViewById(R.id.pull_to_refresh);
        mPullToRefreshView.setOnRefreshListener(new PullToRefreshView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPullToRefreshView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        queryPage();
                        mPullToRefreshView.setRefreshing(false);
                    }
                }, 500);
            }
        });
        listView = (RecyclerView) view.findViewById(R.id.listView);
        listView.setLayoutManager(new LinearLayoutManager(getContext()));
        //   listView.setAdapter(new ListFragment1.MyAdapter());
        queryPage();

//        swipeRefresh=(SwipeRefreshLayout)view.findViewById(R.id.swipe_refresh);
//        swipeRefresh.setColorSchemeColors(getResources().getColor(R.color.colorPrimaryDark));
//        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//
//            }
//        });


        return view;
    }


    private class MyViewHolder extends RecyclerView.ViewHolder  {
        public MyViewHolder(View itemView) {
            super(itemView);
      //      itemView.setOnClickListener(this);
            View button = itemView.findViewById(R.id.button3);
            yonghuming = (TextView) itemView.findViewById(R.id.yonghuming);
            wenzineirong = (TextView) itemView.findViewById(R.id.wenzineirong);
            neirong = (SmartImageView) itemView.findViewById(R.id.neirong);
            tag=(TextView)itemView.findViewById(R.id.button2);
            touxiang=(ImageView)itemView.findViewById(R.id.touxiang);
            //neirong=(SmartImageView)itemView.findViewById(R.id.neirong);
            button.setBackgroundResource(R.drawable.ic_heart_red);
        }



    }


    private class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {
        private List<Post> list = new ArrayList<>();
    //    private ArrayList<Bitmap>arrayList=new ArrayList<>();

        public MyAdapter(List<Post> list) {
            this.list = list;
        //    this.arrayList=arrayList;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            LayoutInflater inflater = getLayoutInflater();
            View view = inflater.inflate(R.layout.list_item, viewGroup, false);
            MyViewHolder viewHolder = new MyViewHolder(view);
            return viewHolder;
        }


        @Override
        public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int i) {
            Post postItem = list.get(i);

            yonghuming.setText(postItem.getAuthor().getUsername());
           wenzineirong.setText(postItem.getText());
           tag.setText(postItem.getTag());
            BmobFile bmobfile = postItem.getPicture();
            String url=bmobfile.getFileUrl();
            neirong.setImageUrl(url);

            BmobFile touFile=postItem.getAuthor().getTouxiang();
            if (touFile!=null){
                String uurl=touFile.getFileUrl();
                Glide.with(getContext())
                        .load(uurl)
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
                        .into(touxiang);
            }

        }

    @Override
    public int getItemCount() {
        return list.size();
    }

}

    //查询所有数据

    public void queryPage(){
        Person user=Person.getCurrentUser(Person.class);
      //  BmobUser user=BmobUser.getCurrentUser(BmobUser.class);
        BmobQuery<Post>list=new BmobQuery<>();
        //查询存在“objectId”字段的数据。
    //  list.addWhereExists("objectId");
      //  list.addWhereEqualTo("Author",user);//查询当前用户的所有帖子
      list.addWhereEqualTo("like",true);
        list.addWhereEqualTo("IdLike",user.getObjectId());
       // list.addWhereEqualTo("Author",user);//查询当前用户的所有帖子
        list.order("-updatedAt");
        list.include("Author");// 希望在查询帖子信息的同时也把发布人的信息查询出来
        //获取查询数据
        list.findObjects(new FindListener<Post>() {
            @Override
            public void done(List<Post> list, BmobException e) {
                if (e == null) {
                        listView.setAdapter(new MyAdapter(list));
                        Toast.makeText(getActivity(), "更新列为" + list.size() + "条", Toast.LENGTH_LONG).show();
                    }
                else {
                    Toast.makeText(getActivity(), "查询失败"+e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }













//获取bitmap格式的图片
public Bitmap getPicture(String path){
        Bitmap bm=null;
        try{
            URL url=new URL(path);
            URLConnection connection=url.openConnection();
            connection.connect();
            InputStream inputStream=connection.getInputStream();
            bm=BitmapFactory.decodeStream(inputStream);

        }catch (MalformedURLException e){
            e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return  bm;
}




}
