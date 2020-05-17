package com.example.fjob.Fragmen.ui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.example.fjob.Api.ApiRetrofit;
import com.example.fjob.Entity.usermessage.CpnMessage;
import com.example.fjob.Fragmen.ui.activity.PersonMsgActivity;
import com.example.fjob.NotificationsViewModel;
import com.example.fjob.R;
import com.example.fjob.tableDo.CpnViewModel;
import com.example.fjob.ui.login.LoginActivity;
import com.example.fjob.ui.login.myui.AddMyMessageActivity;
import com.example.fjob.ui.login.myui.RusumeActivity;
import com.squareup.picasso.Picasso;

import java.util.List;


public class MyFragment extends Fragment {
private Button buttonQuite;
private ConstraintLayout constraintLayout;
    private NotificationsViewModel notificationsViewModel;
    private ImageView myimg;
//    popuwindow.....
    private Context mContext;
    private TextView nckname,tnumber;
    private ImageView imageshead;
private CpnViewModel cpnViewModel;
List<CpnMessage>cpnMessageList;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel = ViewModelProviders.of(this).get(NotificationsViewModel.class);
        return inflater.inflate(R.layout.fragment_notifications, container, false);
}



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
cpnViewModel=ViewModelProviders.of(this).get(CpnViewModel.class);
        initview();
adapterself();
        //我的简历
        constraintLayout=requireActivity().findViewById(R.id.constrain_my_message);
        constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(requireActivity(), RusumeActivity.class);
                startActivity(intent);
            }
        });



        buttonQuite=requireActivity().findViewById(R.id.btn_quit_my);
        buttonQuite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(requireActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });


    }

    private void adapterself() {
 tnumber=requireActivity().findViewById(R.id.t_number);
        nckname=requireActivity().findViewById(R.id.t_name);
imageshead=requireActivity().findViewById(R.id.images_head);


        cpnMessageList=cpnViewModel.list();
        if (cpnMessageList.size()!=0){
            for (CpnMessage cpnMessage:cpnMessageList){

                Picasso.get().load(ApiRetrofit.URL + cpnMessage.getIcon()).into(imageshead);
                nckname.setText(cpnMessage.getNickname());
                tnumber.setText(cpnMessage.getPhonenumber());

            }}


        LocalBroadcastManager broadcastManager = LocalBroadcastManager.getInstance(getActivity());
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.ACTION_FASONG");//建议把它写一个公共的变量，这里方便阅读就不写了
        BroadcastReceiver Receive =new BroadcastReceiver() {

            @Override
            public void onReceive(Context context, Intent intent) {
                intent.getStringExtra("msg");


                Picasso.get().load(ApiRetrofit.URL + intent.getStringExtra("url")).into(imageshead);
                nckname.setText(intent.getStringExtra("username"));
                tnumber.setText(intent.getStringExtra("phone"));





            }
        };
        broadcastManager.registerReceiver(Receive, intentFilter);

    }

//    个人信息



    private void initview() {
        myimg=requireActivity().findViewById(R.id.images_head);
        myimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                  Intent intent=new Intent(requireActivity(), PersonMsgActivity.class);
                  startActivity(intent);
            }
        });
    }
}