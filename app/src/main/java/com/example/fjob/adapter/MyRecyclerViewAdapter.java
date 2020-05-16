package com.example.fjob.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fjob.Entity.RegisterEntity;
import com.example.fjob.Entity.job.ResumeEntity;
import com.example.fjob.R;

import java.util.ArrayList;
import java.util.List;

public  class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.RecyclerHolder>{

    private Context mContext;
    private List<RegisterEntity> dataList = new ArrayList<>();
    private onRecyclerItemClickerListener mListener;

    public MyRecyclerViewAdapter(RecyclerView recyclerView) {
        this.mContext = recyclerView.getContext();
    }
    /**
     * 增加点击监听
     */
    public void setItemListener(onRecyclerItemClickerListener mListener) {
        this.mListener = mListener;
    }
    /**
     * 设置数据源
     */
    public void setData(List<RegisterEntity> dataList) {
        if (null != dataList) {
            this.dataList.clear();
            this.dataList.addAll(dataList);
            notifyDataSetChanged();
        }
    }
    public List<RegisterEntity> getDataList() {
        return dataList;
    }
    @NonNull
    @Override
    public MyRecyclerViewAdapter.RecyclerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) { ;
        View view=LayoutInflater.from(mContext).inflate(R.layout.cell_car_wchact,parent,false);
        return  new RecyclerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyRecyclerViewAdapter.RecyclerHolder holder, int position) {
       RegisterEntity registerEntity=dataList.get(position);
    //  holder.textViewContent.setText(dataList.get(position));
        holder.textViewContent.setText(registerEntity.getPassword());
       // holder.textViewContent.setOnClickListener(getOnClickListener(position));
    }
    private View.OnClickListener getOnClickListener(final int position) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener && null != v) {
                    mListener.onRecyclerItemClick(v, dataList.get(position), position);
                }
            }
        };
    }
    @Override
    public int getItemCount() {
     return dataList.size();
    }

    public class RecyclerHolder extends RecyclerView.ViewHolder {
        private TextView textViewPeople,textViewContent;
        private ImageView imageView;
        public RecyclerHolder(@NonNull View itemView) {
            super(itemView);
            textViewContent=itemView.findViewById(R.id.textView_chat_content);
            textViewPeople=itemView.findViewById(R.id.textView_chat_people);
            imageView=itemView.findViewById(R.id.imageView_chat_image);

        }
    }

    /**
     * 点击监听回调接口
     */
    public interface onRecyclerItemClickerListener {
        void onRecyclerItemClick(View view, Object data, int position);
    }
}