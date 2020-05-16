package com.example.fjob;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fjob.Api.ApiRetrofit;
import com.example.fjob.Entity.job.JobMessage;
import com.example.fjob.Entity.job.JobResultEntity;
import com.example.fjob.ui.login.findjob.JobMessageAllActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    List<JobMessage>allJobs =new ArrayList<>();

    public void setAllJobs(List<JobMessage> allJobs) {
        this.allJobs = allJobs;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //加载卡片

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        final View itemView = layoutInflater.from(parent.getContext()).inflate(R.layout.cell_card_job_show1, parent, false);

//卡片点击事件
        final MyViewHolder holder = new MyViewHolder(itemView);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

              String  allMessage= holder.img.getText().toString();
               // Toast.makeText(itemView.getContext(),allMessage,Toast.LENGTH_SHORT).show();
               Intent intent = new Intent(view.getContext(), JobMessageAllActivity.class);
                intent.putExtra("uuid",allMessage);

              holder.itemView.getContext().startActivity(intent);
            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        //绑定匹配 将输入文本加入卡片
        JobMessage jobMessage=allJobs.get(position);

        holder.textViewCpn.setText(jobMessage.getCpnName());
        holder.textViewJob.setText(jobMessage.getJobName());
        holder.textViewConditionOne.setText(jobMessage.getJobConditionOne());
        holder.textViewConditionTwo.setText(jobMessage.getJobConditionTwo());
        holder.textViewJobPay.setText(jobMessage.getJobPay());
        holder.img.setText(jobMessage.getUuid());
        Picasso.get().load(ApiRetrofit.URL+jobMessage.getImage()).into(holder.imageView);




    }

    @Override
    public int getItemCount() {
        return allJobs.size();
    }

    public void setData(JobResultEntity jobResultEntity) {
    }

    class  MyViewHolder extends RecyclerView.ViewHolder {
        TextView textViewCpn,textViewJob,textViewConditionOne,
                textViewConditionTwo,textViewDate,textViewJobPay
                ,img;
        ImageView imageView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewCpn=itemView.findViewById(R.id.textView_cpn_name_add);
            textViewJob=itemView.findViewById(R.id.textView_job_add);
            textViewConditionOne=itemView.findViewById(R.id.textView_job_condition_one_add);
            textViewConditionTwo=itemView.findViewById(R.id.textView_Job_condition_two_add);
            textViewJobPay=itemView.findViewById(R.id.textView_job_pay_add);

            textViewDate=itemView.findViewById(R.id.text_uuid);
            img=itemView.findViewById(R.id.text_celljob_ima);
            imageView=itemView.findViewById(R.id.imageView_job_show);
        }
    }

}
