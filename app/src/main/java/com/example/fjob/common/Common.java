package com.example.fjob.common;


import com.example.fjob.Api.Api;
import com.example.fjob.Api.ApiRetrofit;
import com.google.gson.Gson;

public class Common {
   public static final String API = "192.168.0.104";
  public   static Api apicommont= ApiRetrofit.getInstance().getService(Api.class);
  Gson gson=new Gson();
  public void cdjson(){



  }

}
