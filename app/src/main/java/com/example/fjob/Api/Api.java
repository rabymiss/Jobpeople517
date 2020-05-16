package com.example.fjob.Api;

import com.example.fjob.Entity.job.JobMessage;
import com.example.fjob.Entity.job.JobMessageAll;
import com.example.fjob.Entity.job.JobResultEntity;
import com.example.fjob.Entity.RegisterEntity;
import com.example.fjob.Entity.job.ReturnJobMessageAll;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Api {
    @POST("user/register")
    Call<RegisterEntity>posterRegister(@Body RequestBody requestBody);

    @POST("add/job")
    Call<JobMessage>postJobRegister(@Body RequestBody requestBody);
    @GET("find/job")
    Call<JobResultEntity> getJson();
    @POST("user/login")
    Call<RegisterEntity>LoginResult(@Body RequestBody requestBody);

    //加载全部公司信息
    @GET("find/all/message")
    Call<ReturnJobMessageAll>findMsgsAll();
    //上传所有信息
    @POST("add/job/message")
    Call<JobMessageAll>addMsgsAll(@Body RequestBody requestBody);
}
