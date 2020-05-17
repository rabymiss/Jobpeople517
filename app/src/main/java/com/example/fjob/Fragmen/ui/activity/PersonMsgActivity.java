package com.example.fjob.Fragmen.ui.activity;


import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;


import com.bumptech.glide.Glide;
import com.example.fjob.Api.ApiRetrofit;
import com.example.fjob.Entity.RegisterEntity;
import com.example.fjob.Entity.usermessage.CpnMessage;
import com.example.fjob.R;
import com.example.fjob.UserViewModel;
import com.example.fjob.common.Common;
import com.example.fjob.data.model.LoginUser;
import com.example.fjob.img.BaseActivity;

import com.example.fjob.tableDo.CpnViewModel;
import com.example.fjob.tool.Validator;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PersonMsgActivity extends BaseActivity {
    private static final String TAG ="PersonMessageActivity" ;
    private EditText edName, edBurth, edemail, edphone, edaddress, edwork;
    private EditText edshowyouself;
    private ImageView imageViewIcon;
    private Button confirm,photo,cameler;



    List<String>listall=new ArrayList<>();
    private UserViewModel userViewmodel;
    LiveData<List<LoginUser>>liveData;

    private static final String TEMP_INFO = "temp_info";
    private SharedPreferences sp;
    private String url;
    Common common;
    List<String>list=new ArrayList<>();
    List<String>listnet=new ArrayList<>();
    List<String>listcpnimg=new ArrayList<>();

//    List<CpnMessage>listcpn;..................................................................

//    popuwindow.....
private Context mContext;
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA };
    private static String[] PERMISSIONS_STORAGES = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };



  private  ImageView imageView;

    private File tempFile = null;
    private Uri uri01;
    private String fileName;
    List<CpnMessage>cpnMessageList;


    private CpnMessage cpnMessage=new CpnMessage();
    private CpnViewModel cpnViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cpn_msg);
        verifyStoragePermissions(this);
        userViewmodel= ViewModelProviders.of(this).get(UserViewModel.class);
        imageView=findViewById(R.id.imageView_pco);
        cpnViewModel=ViewModelProviders.of(this).get(CpnViewModel.class);
        inoitView();
        initDo();
        livedata();
        list.clear();
        listcpnimg.clear();
        listnet.clear();
        getintent();
        findself();
        adaptercpn();
        mContext = this;
    }

    private void adaptercpn() {
        cpnMessageList=cpnViewModel.list();
        if (cpnMessageList.size()!=0){
            for (CpnMessage cpnMessage:cpnMessageList){

                edName.setText(cpnMessage.getNickname());

                SharedPreferences.Editor editor=getSharedPreferences(TEMP_INFO,Context.MODE_PRIVATE).edit();
                editor.clear();
                editor.putString("usn",cpnMessage.getWorkin());
editor.putString("imgnet",cpnMessage.getIcon());
                editor.apply();
                edemail.setText(cpnMessage.getEmail());
                edphone.setText(cpnMessage.getPhonenumber());

                edaddress.setText(cpnMessage.getAddressp());
                edshowyouself.setText(cpnMessage.getShowyou());
                if (cpnMessage.getIcon()!=null){
                    listcpnimg.add(cpnMessage.getIcon());
                }
                Picasso.get().load(ApiRetrofit.URL+cpnMessage.getIcon()).into(imageViewIcon);
            }}


    }

    private void findself() {
        liveData=userViewmodel.getAllUsersLive();
        liveData.observe(this, new Observer<List<LoginUser>>() {
            @Override
            public void onChanged(List<LoginUser> loginUsers) {
                for (LoginUser loginUser:loginUsers){

                    SharedPreferences.Editor editor=getSharedPreferences(TEMP_INFO,Context.MODE_PRIVATE).edit();
                    editor.clear();
                    list.add(loginUser.getUserId());
                    editor.putString("username",loginUser.getUserId());
                    editor.apply();

                }
            }
        });



    }

    private void getintent() {




    }

    //提交信息
    public void onclickedimg(View view){






        sp=getSharedPreferences(TEMP_INFO,Context.MODE_PRIVATE);

        if (sp.getString("img","")==null&&sp.getString("imgnet","")==null){
            Toast.makeText(getApplicationContext(), "请上传头像", Toast.LENGTH_SHORT).show();

        }else {
            SharedPreferences.Editor editor = getSharedPreferences(TEMP_INFO, Context.MODE_PRIVATE).edit();
//            editor.clear();
//            editor.putString("edName", edName.getText().toString());
//            if (Validator.isEMAIN(edemail.getText().toString())){
//
//
//            }else {
//                edemail.setError("格式不正确");
//                edemail.requestFocus();
//                return;
//            }

//            if (Validator.isMobileNumber(edphone.getText().toString())){
////                        editor.putString("edphone", edphone.getText().toString());
//
//            }else {
//                edphone.setError("号码格式不正确");
//                edphone.requestFocus();
//                return;
//            }



            cpnMessage.setAddressp(edaddress.getText().toString());
//



                cpnMessage.setIcon(sp.getString("img",""));


            cpnMessage.setEmail( edemail.getText().toString());
            cpnMessage.setNickname(edName.getText().toString());
            cpnMessage.setPhonenumber(edphone.getText().toString());
            cpnMessage.setShowyou(edshowyouself.getText().toString());
            cpnMessage.setWorkin(list.get(0));
            cpnMessage.setUsername(list.get(0));


            //广播
            Intent intent = new Intent();
            intent.setAction("android.intent.action.ACTION_FASONG");//用隐式意图来启动广播
            intent.putExtra("username", cpnMessage.getNickname());
            intent.putExtra("url", cpnMessage.getIcon());
            intent.putExtra("phone",cpnMessage.getPhonenumber());
            LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(intent);





            cpnViewModel.deleteall();

            cpnViewModel.insert(cpnMessage);
            Gson gson = new Gson();
            String jsonstr = gson.toJson(cpnMessage);
            final RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonstr);
            Call<CpnMessage>task=Common.apicommont.addcpn(requestBody);
            task.enqueue(new Callback<CpnMessage>() {
                @Override
                public void onResponse(Call<CpnMessage> call, Response<CpnMessage> response) {



                    Toast.makeText(getApplicationContext(), "保存成功", Toast.LENGTH_SHORT).show();

                    PersonMsgActivity.this.finish();
                }

                @Override
                public void onFailure(Call<CpnMessage> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), t.toString(), Toast.LENGTH_SHORT).show();
                }
            });






        }
















    }
    private void livedata() {









        liveData=userViewmodel.getAllUsersLive();
        liveData.observe(this, new Observer<List<LoginUser>>() {
            @Override
            public void onChanged(List<LoginUser> loginUsers) {
                for (LoginUser loginUser:loginUsers){

                    SharedPreferences.Editor editor=getSharedPreferences(TEMP_INFO,Context.MODE_PRIVATE).edit();
                    editor.clear();
                    list.add(loginUser.getUserId());
                    editor.putString("username",loginUser.getUserId());
                    editor.apply();

                }
            }
        });
    }

    private void inoitView() {
        edName = findViewById(R.id.edit_res_name);
        edaddress = findViewById(R.id.edit_res_burth);
        edemail = findViewById(R.id.edit_res_e_mail);
        edphone = findViewById(R.id.edit_res_telphone);
        edshowyouself = findViewById(R.id.edit_show_you);
        confirm = findViewById(R.id.button_res_confirm);
        imageViewIcon=findViewById(R.id.imageView_pco);




    }

    private void initDo() {
//
    }
//    popuwindow......................................
//    ............................
//    选择照片

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }
    private void initPopWindow(View v) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_popup, null, false);
        ImageView btn_xixi = view.findViewById(R.id.btn_xixi);
        ImageView btn_hehe =  view.findViewById(R.id.btn_hehe);
        //1.构造一个PopupWindow，参数依次是加载的View，宽高
//        final PopupWindow popWindow = new PopupWindow(view,
//                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        final PopupWindow popWindow = new PopupWindow(view,
                1500, 200, true);
        popWindow.setAnimationStyle(R.anim.anim_pop);  //设置加载动画

        //这些为了点击非PopupWindow区域，PopupWindow会消失的，如果没有下面的
        //代码的话，你会发现，当你把PopupWindow显示出来了，无论你按多少次后退键
        //PopupWindow并不会关闭，而且退不出程序，加上下述代码可以解决这个问题
        popWindow.setTouchable(true);
        popWindow.setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
                // 这里如果返回true的话，touch事件将被拦截
                // 拦截后 PopupWindow的onTouchEvent不被调用，这样点击外部区域无法dismiss
            }
        });
        popWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));    //要为popWindow设置一个背景才有效


        //设置popupWindow显示的位置，参数依次是参照View，x轴的偏移量，y轴的偏移量
        popWindow.showAsDropDown(v, -400, 1500);

//        设置popupWindow里的按钮的事件
        btn_xixi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //检查是否已经获得相机的权限
                if(verifyPermissions(PersonMsgActivity.this,PERMISSIONS_STORAGE[2]) == 0){

                    ActivityCompat.requestPermissions(PersonMsgActivity.this, PERMISSIONS_STORAGE, 3);
                }else{
                    //已经有权限
                    toCamera();  //打开相机
                }

                popWindow.dismiss();



            }
        });
        btn_hehe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toPicture();
                popWindow.dismiss();
            }
        });
    }
    public void seletephoto(View view){

        initPopWindow(view);

    }
    public static void verifyStoragePermissions(Activity activity) {
        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(activity, PERMISSIONS_STORAGES, REQUEST_EXTERNAL_STORAGE);
        }

    }







    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //判断返回码不等于0
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode != RESULT_CANCELED) {    //RESULT_CANCELED = 0(也可以直接写“if (requestCode != 0 )”)
            //读取返回码
            switch (requestCode) {
                case 100:   //相册返回的数据（相册的返回码）
                    listall.clear();
                    uri01=data.getData();
                    listall.add(uri01.toString());

                    System.out.println("------------------");
                    String[] arr={MediaStore.Images.Media.DATA};
                    Cursor cursor=managedQuery(uri01,arr,null,null,null);
                    int img_index=cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);

                    cursor.moveToFirst();
                    String pa=cursor.getString(img_index);
                    String a=arr[0];
                    String x=cursor.getString(cursor.getColumnIndex(a));
                    File tempFile =new File(x.trim());
                    fileName = tempFile.getName();

                    listall.add(fileName);


                    File file=new File(pa);
                    listall.add(pa);

                    ContentResolver contentResolver=this.getContentResolver();
                    try {
                        Bitmap bitmap=BitmapFactory.decodeStream(contentResolver.openInputStream(uri01));



                        Glide.with(this).load(bitmap).into(imageView);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }

                    sp=getSharedPreferences(TEMP_INFO, Context.MODE_PRIVATE);
                    String  usn=sp.getString("username","");



                    File files=new File(pa);
                    Map<String,String>params12=new HashMap<>();
                    params12.put("description",usn);

//.......................................
                    RequestBody body=RequestBody.create(MediaType.parse("image/jpeg"),files);

                    MultipartBody.Part part=MultipartBody.Part.createFormData("files", fileName,body);

                   Call<RegisterEntity>task=Common.apicommont.load(part,params12);
                   task.enqueue(new Callback<RegisterEntity>() {
                       @Override
                       public void onResponse(Call<RegisterEntity> call, Response<RegisterEntity> response) {
                           RegisterEntity registerEntity=response.body();

                           SharedPreferences.Editor editor=getSharedPreferences(TEMP_INFO,Context.MODE_PRIVATE).edit();
editor.putString("img",registerEntity.getUsername());
editor.apply();
                           System.out.println("返回的照片------------------"+registerEntity.getUsername());
                       }

                       @Override
                       public void onFailure(Call<RegisterEntity> call, Throwable t) {

                       }
                   });
//......................




                    break;
                case 101:  //相机返回的数据（相机的返回码）
                    listall.clear();
                    try {
                        tempFile = new File(Environment.getExternalStorageDirectory(), "fileImg.jpg");  //相机取图片数据文件
                        Uri uri02 = Uri.fromFile(tempFile);   //图片文件
                        listall.add(uri02.toString());
                        listall.add(tempFile.getName());
                        System.out.println("----"+tempFile.getName());
                        Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(uri02));
                        imageView.setImageBitmap(bitmap);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }
    }

    //跳转相册
    private void toPicture() {
        Intent intent = new Intent(Intent.ACTION_PICK);  //跳转到 ACTION_IMAGE_CAPTURE
        intent.setType("image/*");
        startActivityForResult(intent,100);

    }

    //跳转相机
    private void toCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);  //跳转到 ACTION_IMAGE_CAPTURE
        //判断内存卡是否可用，可用的话就进行存储
        //putExtra：取值，Uri.fromFile：传一个拍照所得到的文件，fileImg.jpg：文件名
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(Environment.getExternalStorageDirectory(), "fileImg.jpg")));
        startActivityForResult(intent, 101); // 101: 相机的返回码参数（随便一个值就行，只要不冲突就好）
    }
}

