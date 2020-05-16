package com.example.fjob.ui.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fjob.Api.Api;
import com.example.fjob.Entity.RegisterEntity;
import com.example.fjob.R;
import com.example.fjob.UserViewModel;
import com.example.fjob.data.model.LoginUser;
import com.google.gson.Gson;

import java.net.HttpURLConnection;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RegisterActivity extends AppCompatActivity {
private TextView textViewAccount,textViewPassword,textViewPasswordConfirm;
private Button buttonConfirmRegister;
private UserViewModel userViewModel;
    private static final String TAG ="Regi";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        buttonConfirmRegister=findViewById(R.id.button_confirm_register);
        textViewAccount=findViewById(R.id.text_account_register);
        textViewPassword=findViewById(R.id.text_account_register1);
        textViewPasswordConfirm=findViewById(R.id.text_password_password_register_comfirm);
        userViewModel=ViewModelProviders.of(this).get(UserViewModel.class);

        buttonConfirmRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String account = textViewAccount.getText().toString().trim();
                String password1 = textViewPassword.getText().toString().trim();
                String password2 = textViewPasswordConfirm.getText().toString().trim();


                //加载工作信息
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http:/172.20.10.3:8080")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                if (password1.equals(password2)) {
//                  Toast.makeText(getApplicationContext(),"注册成功",Toast.LENGTH_LONG).show();
                  Intent intent=new Intent(RegisterActivity.this,LoginActivity.class);
                        startActivity(intent);
                    final RegisterEntity registerEntity=new RegisterEntity(account,password1);

                    Api api=retrofit.create(Api.class);

                    Gson gson=new Gson();
                    String jsonstr=gson.toJson(registerEntity);
                    final RequestBody requestBody=RequestBody.create(MediaType.parse("application/json; charset=utf-8"),jsonstr);
                  Call<RegisterEntity>task=api.posterRegister(requestBody);
                  task.enqueue(new Callback<RegisterEntity>() {
                      @Override
                      public void onResponse(Call<RegisterEntity> call, Response<RegisterEntity> response) {
                          int a=response.code();
                          if (a== HttpURLConnection.HTTP_OK){
                              Log.d(TAG,"Json-->"+response.body());
                              Toast.makeText(getApplicationContext(),"注册成功",Toast.LENGTH_LONG).show();
                              Intent intent=new Intent(RegisterActivity.this,LoginActivity.class);
                              startActivity(intent);
                          }

                      }

                      @Override
                      public void onFailure(Call<RegisterEntity> call, Throwable t) {
                          Log.d(TAG,"失败"+t.toString());
                      }
                  });



              }else {
                  Toast.makeText(getApplicationContext(),"两次密码不一致，请重新输入",Toast.LENGTH_LONG).show();
              }

            }
        });


    }
}
