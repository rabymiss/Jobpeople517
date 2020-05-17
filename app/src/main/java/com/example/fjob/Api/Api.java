package com.example.fjob.Api;

import com.example.fjob.Api.entity.JobAll;
import com.example.fjob.Entity.job.JobMessage;
import com.example.fjob.Entity.job.JobMessageAll;
import com.example.fjob.Entity.job.JobResultEntity;
import com.example.fjob.Entity.RegisterEntity;
import com.example.fjob.Entity.job.ReturnJobMessageAll;
import com.example.fjob.Entity.usermessage.CpnMessage;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;

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
    //忘记密码

    @POST("user/login/forget")
    Call<RegisterEntity> loginphfor(@Body RequestBody requestBody);

    @POST("user/login/for")
    Call<RegisterEntity> LoginResultfor(@Body RequestBody requestBody);
    //寻找工作详情
    @POST("find/message/byid")
    Call<JobAll> adapterjobbyid(@Body RequestBody requestBody);
    @POST("save/resume")
    Call<JobAll> sqveresume(@Body RequestBody requestBody);
//上传图片
    @Multipart
    @POST("updown12")
    Call<RegisterEntity>load(@Part MultipartBody.Part part, @PartMap Map<String,String> params);
    //上传公司所有信息
    @POST("add/cpn")
    Call<CpnMessage>addcpn(@Body RequestBody requestBody);
    @POST("find/cpn")
    Call<List<CpnMessage>>findcpn(@Body RequestBody requestBody);
}
