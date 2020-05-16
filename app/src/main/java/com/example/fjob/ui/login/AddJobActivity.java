package com.example.fjob.ui.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fjob.Api.Api;
import com.example.fjob.Entity.job.JobMessage;
import com.example.fjob.JobViewModel;
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

public class AddJobActivity extends AppCompatActivity {
    private JobViewModel jobViewModel;
    private Button buttonConfirm;
    private EditText editTextCpn,editTextJob,editTextConditionOne,editTextConditionTwo,editTextJobPay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_job);
        jobViewModel= ViewModelProviders.of(this).get(JobViewModel.class);
        buttonConfirm=findViewById(R.id.button_add_confirm);
        editTextCpn=findViewById(R.id.editText_cpn_add);
        editTextJob=findViewById(R.id.editText_job);
        editTextConditionOne=findViewById(R.id.editText_condition_one);
        editTextConditionTwo=findViewById(R.id.editText_condition_two);
        editTextJobPay=findViewById(R.id.editText_job_pay);
        //buttonConfirm.setEnabled(false);
        //自动调出键盘

        buttonConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cpn = editTextCpn.getText().toString().trim();
                String job = editTextJob.getText().toString().trim();
                final String condition1 = editTextConditionOne.getText().toString().trim();
                String condition2 = editTextConditionTwo.getText().toString().trim();
                String pay = editTextJobPay.getText().toString().trim();
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http:/172.20.10.3:8080")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                Api api = retrofit.create(Api.class);

                JobMessage jobMessage=new JobMessage(condition1,condition2,job,pay,cpn);
                Gson gson =new Gson();
                String jsontr=gson.toJson(jobMessage);
                final RequestBody requestBody=RequestBody.create(MediaType.parse("application/json; charset=utf-8"),jsontr);
                Call<JobMessage>task=api.postJobRegister(requestBody);
                task.enqueue(new Callback<JobMessage>() {
                    @Override
                    public void onResponse(Call<JobMessage> call, Response<JobMessage> response) {
                        int code=response.code();
                        Log.d("add","code------->"+code);

                        if (code== HttpURLConnection.HTTP_OK){
                            Log.d("add","成功--------------------->"+response.body());
                            Intent intent=new Intent(AddJobActivity.this, ButtomActivity.class);
                            startActivity(intent);
                        }
                        //返回 主界面

                }

                    @Override
                    public void onFailure(Call<JobMessage> call, Throwable t) {
                        Log.d("add","失败--------------------->"+t.toString());
                        Toast.makeText(getApplicationContext(),"失败",Toast.LENGTH_LONG).show();
                    }
                });
                Intent intent =new Intent(AddJobActivity.this,ButtomActivity.class);
                startActivity(intent);
//..................................

            }
        });





    }
}
