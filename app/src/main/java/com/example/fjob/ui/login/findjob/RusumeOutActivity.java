package com.example.fjob.ui.login.findjob;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.alibaba.fastjson.JSON;
import com.example.fjob.Api.entity.JobAll;
import com.example.fjob.Api.entity.Resume;
import com.example.fjob.Entity.job.ResumeEntity;
import com.example.fjob.Fragmen.ui.JobMessageFragment;
import com.example.fjob.R;
import com.example.fjob.UserViewModel;
import com.example.fjob.common.Common;
import com.example.fjob.data.model.LoginUser;
import com.example.fjob.ui.login.ButtomActivity;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RusumeOutActivity extends AppCompatActivity {
    private EditText edName,edBurth,edpolitics,edemail,edphone,edaddress,edmary;
    private EditText edqwer,deteached,edworkming,edshowyouself;
    private Button confirm;
    private DataOutputStream out;
private Socket socket;
    private SharedPreferences sp;
    private static final String TEMP_INFO="temp_info";
    private String uuid;
    private String cpnid;
private UserViewModel userViewModel;
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rusume_out);
        userViewModel= ViewModelProviders.of(this).get(UserViewModel.class);
        initView();
        initDo();
        getintent();

    }

    private void getintent() {

        LiveData<List<LoginUser>>listLiveData=userViewModel.getAllUsersLive();
        listLiveData.observe(this, new Observer<List<LoginUser>>() {
            @Override
            public void onChanged(List<LoginUser> loginUsers) {
                for (LoginUser loginUser:loginUsers){

                    username = loginUser.getUserId();
                }
            }
        });




        Intent intent=getIntent();
        uuid = intent.getStringExtra("uuid");
        cpnid = intent.getStringExtra("cpnid");


    }

    private void initDo() {

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = getSharedPreferences(TEMP_INFO, Context.MODE_PRIVATE).edit();
                editor.putString("edName", edName.getText().toString());
                editor.putString("edBurth", edBurth.getText().toString());
                editor.putString("edpolitics", edpolitics.getText().toString());
                editor.putString("edemail", edemail.getText().toString());
                editor.putString("edphone", edphone.getText().toString());
                editor.putString("edaddress", edaddress.getText().toString());

                editor.putString("edmary", edmary.getText().toString());
                editor.putString("edqwer", edqwer.getText().toString());
                editor.putString("deteached", deteached.getText().toString());
                editor.putString("edworkming", edworkming.getText().toString());
                editor.putString("edshowyouself", edshowyouself.getText().toString());


                editor.commit();
                Resume resumeEntity=new Resume();
                resumeEntity.setAddresswork(edaddress.getText().toString());
                resumeEntity.setBirthday(edBurth.getText().toString());
                resumeEntity.setEmail(edemail.getText().toString());
                resumeEntity.setIfmary(edmary.getText().toString());
                resumeEntity.setWorkming(edworkming.getText().toString());

                resumeEntity.setPhone(edphone.getText().toString());
                resumeEntity.setPolitics(edpolitics.getText().toString());
                resumeEntity.setQwer(edqwer.getText().toString());
                resumeEntity.setShowbyshelf(edshowyouself.getText().toString());
                resumeEntity.setTeached(deteached.getText().toString());

                resumeEntity.setYouname(edName.getText().toString());
                resumeEntity.setUuid(uuid);
                resumeEntity.setCpnid(cpnid);
                resumeEntity.setT1(username);
                String msg= JSON.toJSONString(resumeEntity);
//发送简历
                final RequestBody requestBody=RequestBody.create(MediaType.parse("application/json; charset=utf-8"),msg);

                Call<JobAll>task= Common.apicommont.sqveresume(requestBody);
                task.enqueue(new Callback<JobAll>() {
                    @Override
                    public void onResponse(Call<JobAll> call, Response<JobAll> response) {

                    }

                    @Override
                    public void onFailure(Call<JobAll> call, Throwable t) {

                    }
                });







            }
        });

    }

    private void initView() {
        edName=findViewById(R.id.editText_res_name1);
        edBurth = findViewById(R.id.editText_res_burth1);
        edpolitics=findViewById(R.id.editText_res_politics1);
        edemail=findViewById(R.id.editText_res_e_mail1);
        edphone=findViewById(R.id.editText_res_telphone1);

        edaddress=findViewById(R.id.editText_res_address1);
        edmary  =findViewById(R.id.editText_res_if_mary1);
        edqwer=findViewById(R.id.editText_res_qwer1);
        deteached=findViewById(R.id.editText_res_teached1);
        edworkming=findViewById(R.id.editText_find_work_dear1);

        edshowyouself=findViewById(R.id.editText_show_youself1);

        confirm=findViewById(R.id.button_res_confirm1);



        sp = getSharedPreferences(TEMP_INFO, Context.MODE_PRIVATE);
        String content1 = sp.getString("edName", "");
        String content2 = sp.getString("edBurth", "");
        String content3 = sp.getString("edpolitics", "");
        String content4 = sp.getString("edemail", "");
        String content5 = sp.getString("edphone", "");
        String content6 = sp.getString("edaddress", "");
        String content7 = sp.getString("edmary", "");
        String content8 = sp.getString("edqwer", "");
        String content9 = sp.getString("deteached", "");
        String content10 = sp.getString("edworkming", "");
        String content11 = sp.getString("edshowyouself", "");
        edName.setText(content1);
        edBurth.setText(content2);
        edpolitics.setText(content3);
        edemail.setText(content4);
        edphone.setText(content5);

        edaddress.setText(content6);
        edmary.setText(content7);
        edqwer.setText(content8);
        deteached.setText(content9);
        edworkming.setText(content10);
        edshowyouself.setText(content11);



    }
}
