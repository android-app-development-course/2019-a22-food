package com.example.foodtwo.yuzhu;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.ContentUris;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.foodtwo.Activity.MainActivity;
import com.example.foodtwo.Bomb.Person;
import com.example.foodtwo.Bomb.Post;
import com.example.foodtwo.yuzhu.ImageAdapter;
import com.example.foodtwo.Image.PhotoTest;
import com.example.foodtwo.R;
import com.winfo.photoselector.PhotoSelector;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.datatype.BmobPointer;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UploadFileListener;

public class Sendone extends AppCompatActivity {
    private File outputImage;
    public  static final int TAKE_PHOTO=4;
    public  static final int CROP_PHOTO=2;
    public  static final int CHOOSE_PHOTO=5;

    //导入的
    private static final int SINGLE_CODE = 1;//单选
    private static final int CROP_CODE = 3;//剪切裁剪
    private ArrayList<String> images;
    private ImageAdapter mAdapter;
    private ImageView imageView;
    private  Uri resultUri;

    private ImageView picture;//保存照片
    private Uri imageUri=null;
    private ImageView addPhoto;
    private Button openAlbum;
    private Button button;
    private Button test;//测试按钮
    private EditText text;
    private EditText tag;
    String mpath=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sendone);
        final Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//设置返回按钮


        //新加的
       imageView = findViewById(R.id.imageview);//用于剪切的暂时不用
        PhotoSelector.builder()
                .setSingle(true)//单选，裁剪都是单选
                .setCrop(true)//是否裁剪
                .setCropMode(PhotoSelector.CROP_RECTANG)//设置裁剪模式 矩形还是圆形
                .setStatusBarColor(ContextCompat.getColor(Sendone.this, R.color.colorAccent))
                .setToolBarColor(ContextCompat.getColor(Sendone.this, R.color.colorAccent))
                .setBottomBarColor(ContextCompat.getColor(Sendone.this, R.color.colorAccent))
                .setStatusBarColor(ContextCompat.getColor(Sendone.this, R.color.colorAccent))
                .start(Sendone.this, CROP_CODE);

//        RecyclerView rvImage = findViewById(R.id.rv_image);
//        rvImage.setLayoutManager(new GridLayoutManager(this, 3));
//        mAdapter = new ImageAdapter(this);
//        rvImage.setAdapter(mAdapter);


        text=(EditText)findViewById(R.id.text) ;
        tag=(EditText)findViewById(R.id.tag);


        //Bomb初始化
        Bmob.initialize(this, "d122d352185fc32299b18f706b1cdf78");
        picture=(ImageView)findViewById(R.id.picture);

        button=(Button)findViewById(R.id.button);//发送按钮
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //保存该条数据
                Person user=Person.getCurrentUser(Person.class);
                final Post post = new Post();
                post.setTag(tag.getText().toString());
                post.setText(text.getText().toString());
                post.setAuthor(user);
                   if(imageUri==null) {
              //  final ArrayList<String> AllpicPath = mAdapter.getmImages();
              //  final String picPath = AllpicPath.get(0);//单选

                       try {
                           File file = new File(new URI(resultUri.toString()));
                           final BmobFile bombFile = new BmobFile(file);
                           bombFile.uploadblock(new UploadFileListener() {
                               @Override
                               public void done(BmobException e) {
                                   if (e == null) {
                                       Toast.makeText(Sendone.this, "上传成功" + bombFile.getFileUrl(), Toast.LENGTH_SHORT).show();
                                       post.setPicture(bombFile);
                                       post.save(new SaveListener<String>() {
                                           @Override
                                           public void done(String objectId, BmobException e) {
                                               if (e == null) {
                                                   Toast.makeText(Sendone.this, "相册添加数据成功，返回objectId为：" + objectId, Toast.LENGTH_SHORT).show();
                                                   Intent intent=new Intent(Sendone.this, MainActivity.class);
                                                   startActivity(intent);
                                               } else {
                                                   Toast.makeText(Sendone.this, "创建数据失败：" + e.getMessage(), Toast.LENGTH_SHORT).show();
                                               }
                                           }
                                       });
                                   } else {
                                       Toast.makeText(Sendone.this,  "失败！" + e.getMessage(), Toast.LENGTH_SHORT).show();
                                   }
                               }

                               @Override
                               public void onProgress(Integer value) {
                                   super.onProgress(value);

                                   Toast.makeText(Sendone.this, "请勿离开页面，加载上传中，当前：" + value, Toast.LENGTH_LONG).show();
                               }
                           });
                       }catch (URISyntaxException e){

                       }

             //   final BmobFile bombFile = new BmobFile(new File(picPath));

                       //            //单开子线程完成从网络端获取图片
//           new Thread(new Runnable() {
//                @Override
//                public void run() {


//                    try {
//                        Thread.sleep(2000);
//                    }catch (InterruptedException e){
//                        e.printStackTrace();
//                    }
//                }
//           });




            }
                else if(imageUri!=null){
                    final String picPath = outputImage.getAbsolutePath();

                    final BmobFile bombFile = new BmobFile(new File(picPath));
                       //            //单开子线程完成从网络端获取图片
            new Thread(new Runnable() {
                @Override
                public void run() {
                    bombFile.uploadblock(new UploadFileListener() {
                        @Override
                        public void done(BmobException e) {
                            if (e == null) {
                                Toast.makeText(Sendone.this, "上传成功" + bombFile.getFileUrl(), Toast.LENGTH_SHORT).show();
                                post.setPicture(bombFile);
                                post.save(new SaveListener<String>() {
                                    @Override
                                    public void done(String objectId, BmobException e) {
                                        if (e == null) {
                                            Toast.makeText(Sendone.this, "相机添加数据成功，返回objectId为：" + objectId, Toast.LENGTH_SHORT).show();
                                        } else {
                                            Toast.makeText(Sendone.this, "创建数据失败：" + e.getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                            } else {
                                Toast.makeText(Sendone.this, picPath + "失败！" + e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });


                    try {
                        Thread.sleep(2000);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            });


              }


            }
        });




        //openAlbum=(Button)findViewById(R.id.button5);//打开相册
//        addPhoto=(ImageView) findViewById(R.id.addPhoto);
//        addPhoto.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                //单选
//                PhotoSelector.builder()
//                        .setSingle(true)
//                        .start(Sendone.this, SINGLE_CODE);
                //
                //单选后剪裁 裁剪的话都是针对一张图片所以要设置setSingle(true)
//                PhotoSelector.builder()
//                        .setSingle(true)//单选，裁剪都是单选
//                        .setCrop(true)//是否裁剪
//                        .setCropMode(PhotoSelector.CROP_RECTANG)//设置裁剪模式 矩形还是圆形
//                        .setStatusBarColor(ContextCompat.getColor(Sendone.this, R.color.colorAccent))
//                        .setToolBarColor(ContextCompat.getColor(Sendone.this, R.color.colorAccent))
//                        .setBottomBarColor(ContextCompat.getColor(Sendone.this, R.color.colorAccent))
//                        .setStatusBarColor(ContextCompat.getColor(Sendone.this, R.color.colorAccent))
//                        .start(Sendone.this, CROP_CODE);
//            }
//        });

//        addPhoto.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                //创建File对象，用于存储拍照后的图片
//                outputImage=new File(Environment.getExternalStorageDirectory(),"output_image.jpg");
//                try{
//                    if(outputImage.exists()){
//                        outputImage.delete();
//                    }
//                    outputImage.createNewFile();
//                }catch (IOException e){
//                    e.printStackTrace();
//                }
//                if(Build.VERSION.SDK_INT>=24){
//                    imageUri= FileProvider.getUriForFile(Sendone.this,
//                            "com.example.foodtwo.fileprovider",outputImage);
//                }else {
//                    imageUri=Uri.fromFile(outputImage);
//                }
//                //启动相机程序
//                Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                intent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);
//                startActivityForResult(intent,TAKE_PHOTO);
//            }
//        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
        switch (requestCode){

//            case TAKE_PHOTO:
//                if(resultCode==RESULT_OK){
//                    Intent intent = new Intent("com.android.camera.action.CROP");
//                    //以下两行代码适配Android 7.0 解决了无法加载图片的问题
//                    intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
//                    intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
//                    intent.setDataAndType(imageUri,"image/*");
//                    intent.putExtra("scale",true);
//                    intent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);
//                    startActivityForResult(intent,CROP_PHOTO);
//                }
//                break;
//            case CROP_PHOTO:
//                if(resultCode == RESULT_OK){
//                    // 因为imageUri是Uri类型的，需要转换才能被decodeStream使用
//                    // 使用getContentResolver()
//                    // 因为在Android系统里面，数据库是私有的。
//                    // 一般情况下外部应用程序是没有权限读取其他应用程序的数据。
//                    // 如果你想公开你自己的数据，你有两个选择：
//                    // 你可以创建你自己的内容提供器（一个ContentProvider子类）或者
//                    // 你可以给已有的提供器添加数据-如果存在一个控制同样类型数据的内容提供器且你拥有写的权限。
//                    // 外界的程序通过ContentResolver接口可以访问ContentProvider提供的数据，
//                    // 在Activity当中通过getContentResolver()可以得到当前应用的 ContentResolver实例
//                    try {
//                        Toast.makeText(Sendone.this, "到这", Toast.LENGTH_SHORT).show();
//                        //解析成Bitmap
//                        Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
//                        picture.setImageBitmap(bitmap);
//
//                        Toast.makeText(Sendone.this, "success!"+imageUri, Toast.LENGTH_SHORT).show();
//                    } catch (FileNotFoundException e) {
//                        e.printStackTrace();
//                    }
//                }
//                break;
//            case CHOOSE_PHOTO:
//                if(resultCode==RESULT_OK){
//                    if(Build.VERSION.SDK_INT>=19){
//                        handleImageOnKitKat(data);
//                    }else {
//                        handleImageBeforeKitKat(data);
//                    }
//                }
//                break;
            case CROP_CODE:
                //获取到裁剪后的图片的Uri进行处理
                resultUri = PhotoSelector.getCropImageUri(data);
                Glide.with(this).load(resultUri).into(imageView);
                break;
//            case SINGLE_CODE:
//                //单选的话 images就只有一条数据直接get(0)即可
//                images = data.getStringArrayListExtra(PhotoSelector.SELECT_RESULT);
//                mAdapter.refresh(images);
//                break;
            default:
                break;
        }

    }

    private void openAlbum(){
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        startActivityForResult(intent,CHOOSE_PHOTO);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 1:
                if(grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    openAlbum();
                }else {
                    Toast.makeText(this,"denied the permission",Toast.LENGTH_SHORT).show();
                }
                break;
            default:
        }
    }

    @TargetApi(19)
    private void handleImageOnKitKat(Intent data){
        String imagePath=null;
        Uri uri=data.getData();
        if(DocumentsContract.isDocumentUri(this,uri)){
            String docId=DocumentsContract.getDocumentId(uri);
            if("com.android.providers.media.documents".equals(uri.getAuthority())){
                String id=docId.split(":")[1];
                String selection=MediaStore.Images.Media._ID+"="+id;
                imagePath=getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,selection);
            }else if("com.android.providers.downloads.documents".equals(uri.getAuthority())){
                Uri contentUri= ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"),Long.valueOf(docId));
                imagePath=getImagePath(contentUri,null);
            }
        }else if("content".equalsIgnoreCase(uri.getScheme())){
            imagePath=getImagePath(uri,null);

        }else if("file".equalsIgnoreCase(uri.getScheme())){
            imagePath=uri.getPath();
        }
        displayImage(imagePath);
    }
    private void handleImageBeforeKitKat(Intent data){
        Uri uri=data.getData();
        String imagePath=getImagePath(uri,null);
        displayImage(imagePath);
    }
    private String getImagePath(Uri uri,String selection){
        String path=null;
        Cursor cursor=getContentResolver().query(uri,null,selection,null,null);
        if(cursor!=null){
            if(cursor.moveToFirst()){
                path=cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }
    private void displayImage(String imagePath){
        if(imagePath!=null){
            Bitmap bitmap=BitmapFactory.decodeFile(imagePath);
            picture.setImageBitmap(bitmap);
        }else {
            Toast.makeText(this,"failed to get Image",Toast.LENGTH_SHORT).show();
        }
    }
}
