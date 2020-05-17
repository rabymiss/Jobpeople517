package com.example.fjob.Fragmen.ui.activity;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;


import com.example.fjob.R;
import com.example.fjob.UserViewModel;
import com.example.fjob.common.Common;
import com.example.fjob.data.model.LoginUser;
import com.example.fjob.tool.Validator;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PersonMsgActivity extends AppCompatActivity {
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
//    List<CpnMessage>cpnMessageList;
//LiveData<List<ImguEntity>>liveDataimg;
//    private String urlint;

//    List<CpnMessage>listcpn;
//    popuwindow.....
private Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cpn_msg);
//        imgViewmodel=ViewModelProviders.of(this).get(ImgViewmodel.class);
        userViewmodel= ViewModelProviders.of(this).get(UserViewModel.class);
//        cpnViewModel=ViewModelProviders.of(this).get(CpnViewModel.class);
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



    }

    private void findself() {

//        cpnMessageList=cpnViewModel.list();
//if (cpnMessageList.size()!=0){
//        for (CpnMessage cpnMessage:cpnMessageList){
//
//            edName.setText(cpnMessage.getNickname());
//
//            SharedPreferences.Editor editor=getSharedPreferences(TEMP_INFO,Context.MODE_PRIVATE).edit();
//            editor.clear();
//            editor.putString("usn",cpnMessage.getWorkin());
//
//            editor.apply();
//            edemail.setText(cpnMessage.getEmail());
//            edphone.setText(cpnMessage.getPhonenumber());
//
//            edaddress.setText(cpnMessage.getAddressp());
//            edshowyouself.setText(cpnMessage.getShowyou());
//            if (cpnMessage.getIcon()!=null){
//                listcpnimg.add(cpnMessage.getIcon());
//            }
//            Picasso.get().load(ApiRetrofit.URL+cpnMessage.getIcon()).into(imageViewIcon);
//        }}
    }

    private void getintent() {

//liveDataimg=imgViewmodel.list();
//liveDataimg.observe(this, new Observer<List<ImguEntity>>() {
//    @Override
//    public void onChanged(List<ImguEntity> imguEntities) {
//        for (ImguEntity imguEntity:imguEntities){
//            if (imguEntity.getUsername()!=null){
//                SharedPreferences.Editor editor=getSharedPreferences(TEMP_INFO,Context.MODE_PRIVATE).edit();
//                editor.clear();
//                editor.putString("urlint",imguEntity.getUsername());
//                if (imguEntity.getUsername()!=null){
//                    listnet.add(imguEntity.getUsername());
//                }
//                editor.apply();
//
//    Picasso.get().load(ApiRetrofit.URL+imguEntity.getUsername()).into(imageViewIcon);
//
//
//            }
//
//        }
//    }
//});


    }

    //编辑头像
    public void onclickedimg(View view){
//        Intent intent=new Intent(CpnMsgActivity.this, PhotoActivity.class);
//
//        startActivity(intent);
//CpnMsgActivity.this.finish();

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
//        confirm.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//sp=getSharedPreferences(TEMP_INFO,Context.MODE_PRIVATE);
//String uu=sp.getString("urlint","");
//                if (url==null&&uu==null){
//                    Toast.makeText(getApplicationContext(), "请上传头像", Toast.LENGTH_SHORT).show();
//
//                }else {
//                SharedPreferences.Editor editor = getSharedPreferences(TEMP_INFO, Context.MODE_PRIVATE).edit();
//                editor.clear();
//                editor.putString("edName", edName.getText().toString());
//if (Validator.isEMAIN(edemail.getText().toString())){
////    editor.putString("edemail", edemail.getText().toString());
//
//}else {
//    edemail.setError("格式不正确");
//    edemail.requestFocus();
//    return;
//}
//
//                    if (Validator.isMobileNumber(edphone.getText().toString())){
////                        editor.putString("edphone", edphone.getText().toString());
//
//                    }else {
//                        edphone.setError("号码格式不正确");
//                        edphone.requestFocus();
//                        return;
//                    }
//
//
//
//                cpnMessage.setAddressp(edaddress.getText().toString());
////
//                    if (listnet.size()!=0){
//                        cpnMessage.setIcon(listnet.get(0));
//                    }else {
//                        cpnMessage.setIcon(listcpnimg.get(0));
//                    }
//
//                cpnMessage.setEmail( edemail.getText().toString());
//                cpnMessage.setNickname(edName.getText().toString());
//                cpnMessage.setPhonenumber(edphone.getText().toString());
//                cpnMessage.setShowyou(edshowyouself.getText().toString());
//                cpnMessage.setWorkin(list.get(0));
//                cpnMessage.setUsername(list.get(0));
//
//
//             //广播
//                    Intent intent = new Intent();
//                    intent.setAction("android.intent.action.ACTION_FASONG");//用隐式意图来启动广播
//                    intent.putExtra("username", cpnMessage.getNickname());
//                    intent.putExtra("url", cpnMessage.getIcon());
//                    intent.putExtra("phone",cpnMessage.getPhonenumber());
//                    LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(intent);
//
//
//
//
//
//                    cpnViewModel.deleteall();
//
//                    cpnViewModel.insert(cpnMessage);
//                Gson gson = new Gson();
//                String jsonstr = gson.toJson(cpnMessage);
//                final RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonstr);
//                Call<CpnMessage>task=Common.apicommont.addcpn(requestBody);
//                task.enqueue(new Callback<CpnMessage>() {
//                    @Override
//                    public void onResponse(Call<CpnMessage> call, Response<CpnMessage> response) {
//
//                      System.out.println("插入-------------"+cpnViewModel.list());
//
//                        Toast.makeText(getApplicationContext(), "保存成功", Toast.LENGTH_SHORT).show();
//
//                            CpnMsgActivity.this.finish();
//                    }
//
//                    @Override
//                    public void onFailure(Call<CpnMessage> call, Throwable t) {
//                        Toast.makeText(getApplicationContext(), t.toString(), Toast.LENGTH_SHORT).show();
//                    }
//                });
//
//
//
//
//
//
//            }}
//        });
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
                Toast.makeText(mContext, "你点击了嘻嘻~", Toast.LENGTH_SHORT).show();
            }
        });
        btn_hehe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "你点击了呵呵~", Toast.LENGTH_SHORT).show();
                popWindow.dismiss();
            }
        });
    }
    public void seletephoto(View view){

        initPopWindow(view);

    }
}

