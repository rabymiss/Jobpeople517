package com.example.fjob.ui.login.findjob;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fjob.Api.Api;
import com.example.fjob.Api.ApiRetrofit;
import com.example.fjob.Api.entity.JobAll;
import com.example.fjob.Entity.RegisterEntity;
import com.example.fjob.Entity.job.JobMessageAll;
import com.example.fjob.Entity.job.JobResultEntity;
import com.example.fjob.Entity.job.ReturnJobMessageAll;
import com.example.fjob.JobViewModel;
import com.example.fjob.R;
import com.example.fjob.common.Common;
import com.example.fjob.tableDo.MsgViewModel;
import com.example.fjob.ui.login.RegisterActivity;
import com.example.fjob.ui.login.myui.RusumeActivity;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.io.DataOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class JobMessageAllActivity extends AppCompatActivity {
private JobViewModel jobViewModel;
private TextView textViewjobname,condition1,condition2,conditon3,jobpay,workshow,cpnname,cpnaddress;
private ImageView icon;
    private Button buttonAddAMsg,buttonchact;
    private String   TAG="加载Msgs";
private List<JobAll.DataBean>data3=new ArrayList<>();
   private  LiveData<List<JobMessageAll> >cpnNameList;
    private MsgViewModel msgViewModel;
    private List<String>stringList=new ArrayList<>();
//发送简历
private Button button,button2;

    private DataOutputStream out;
    private Socket socket;


    private List<ReturnJobMessageAll.DataBean> data=new ArrayList<>();
    private List<JobResultEntity.DataBean> data2=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_job_messagek);
        //发送部件
        jobViewModel=ViewModelProviders.of(this).get(JobViewModel.class);
        msgViewModel=ViewModelProviders.of(this).get(MsgViewModel.class);
stringList.clear();
      findCpn();





        buttonchact=findViewById(R.id.button_wchact_add_message);
        buttonchact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
                   //投递简历
        buttonAddAMsg=findViewById(R.id.button_send_office);
//
        buttonAddAMsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(JobMessageAllActivity.this, RusumeOutActivity.class);
       intent.putExtra("uuid",stringList.get(0));
       intent.putExtra("cpnid",stringList.get(1));
                startActivity(intent);


                //..............................................................................
          }
       });
    }
//匹配公司
    private void findCpn() {
        icon=findViewById(R.id.imageView_show_cpn);
        textViewjobname=findViewById(R.id.show_jobname);
        condition1=findViewById(R.id.show_content1);
        condition2=findViewById(R.id.show_content2);
        jobpay  =findViewById(R.id.show_jobpay);
        workshow=findViewById(R.id.show_work_content_show);
        cpnname=findViewById(R.id.show_cpnname);
        cpnaddress=findViewById(R.id.show_address_cpn);
        conditon3=findViewById(R.id.show_content3);

     //网络加载
        Intent intent=getIntent();
        final String uuid=intent.getStringExtra("uuid");

        RegisterEntity registerEntity=new RegisterEntity(uuid,null);
        Gson gson=new Gson();
        String jsonstr=gson.toJson(registerEntity);
        final RequestBody requestBody=RequestBody.create(MediaType.parse("application/json; charset=utf-8"),jsonstr);

        Call<JobAll>task= Common.apicommont.adapterjobbyid(requestBody);
        task.enqueue(new Callback<JobAll>() {
            @Override
            public void onResponse(Call<JobAll> call, Response<JobAll> response) {
                JobAll jobAll=response.body();

             data3.add(jobAll.getData());
                for (JobAll.DataBean gt:data3){
                    textViewjobname.setText(gt.getJobName());
                    condition1.setText(gt.getConditionTwo());
                    condition2.setText(gt.getConditionTwo());
                    jobpay.setText(gt.getJobPay());
                    workshow.setText(gt.getWorkContentShow());
                    cpnname.setText(gt.getCpnName1());
                    cpnaddress.setText(gt.getDizhi());
                    conditon3.setText(gt.getCondition3());
                    Picasso.get().load(ApiRetrofit.URL+gt.getCpnImage()).into(icon);
                    stringList.add(gt.getGood1());
                    stringList.add(gt.getGood2());

                }


            }

            @Override
            public void onFailure(Call<JobAll> call, Throwable t) {
                Toast.makeText(getApplicationContext(),t.toString(),Toast.LENGTH_LONG).show();
            }
        });


    }
}
