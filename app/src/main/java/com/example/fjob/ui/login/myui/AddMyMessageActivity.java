package com.example.fjob.ui.login.myui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fjob.Api.Api;
import com.example.fjob.Entity.job.JobMessageAll;
import com.example.fjob.R;
import com.google.gson.Gson;

import java.net.HttpURLConnection;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddMyMessageActivity extends AppCompatActivity {
private Button buttonAddAllMessage;
private EditText jobname,condition1,condition2,jobpay,editworkshow,cpnmae,address,workXondition;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_message);
        initView();
        doView();



    }



    private void initView() {
        jobname=findViewById(R.id.edit_add_job_name);
        condition1=findViewById(R.id.editText_condition_one);
        condition2=findViewById(R.id.editText_condition_two);
        jobpay=findViewById(R.id.edit_add_job_pay);
        editworkshow=findViewById(R.id.edit_work_show);
        cpnmae=findViewById(R.id.editText_cpn_name);
        address=findViewById(R.id.edit_cpn_address);
        workXondition=findViewById(R.id.editText_condition_three);
        //.button

        buttonAddAllMessage=findViewById(R.id.button_add_all_confirm);

    }
    private void doView() {

        buttonAddAllMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String jobname1=jobname.getText().toString().trim();
                String condition11=condition1.getText().toString().trim();
                String condition22=condition2.getText().toString().trim();
                String jobpay1=jobpay.getText().toString().trim();
                String workshow1=editworkshow.getText().toString().trim();
                String cpnname=cpnmae.getText().toString().trim();
                String address1=address.getText().toString().trim();
                 String workYoqiu=workXondition.getText().toString().trim();

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http:/172.20.10.3:8080")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                Api api = retrofit.create(Api.class);
                JobMessageAll jobMessageAll=new JobMessageAll(jobname1
                ,null
                        ,null
                        ,null
                        ,null
                        ,null
                        ,jobpay1
                        ,condition11
                        ,condition22
                        , workYoqiu
                        ,null
                        ,workshow1
                , null
                ,null,cpnname,address1);

                Gson gson =new Gson();
                String jsontr=gson.toJson(jobMessageAll);
                final RequestBody requestBody=RequestBody.create(MediaType.parse("application/json; charset=utf-8"),jsontr);
                Call<JobMessageAll> task=api.addMsgsAll(requestBody);
                task.enqueue(new Callback<JobMessageAll>() {
                    @Override
                    public void onResponse(Call<JobMessageAll> call, Response<JobMessageAll> response) {
                        int code=response.code();
                        Log.d("add","code------->"+code);

                        if (code== HttpURLConnection.HTTP_OK){
                            Log.d("add","成功--------------------->"+response.body());

                        }
                        Toast.makeText(getApplicationContext(),"提交成功",Toast.LENGTH_SHORT).show();
                     AddMyMessageActivity.this.finish();
                        //返回 主界面

                    }

                    @Override
                    public void onFailure(Call<JobMessageAll> call, Throwable t) {
                        Log.d("add","失败--------------------->"+t.toString());
                        Toast.makeText(getApplicationContext(),"失败",Toast.LENGTH_LONG).show();
                    }
                });










              //  ........................
            }
        });


    }
}
