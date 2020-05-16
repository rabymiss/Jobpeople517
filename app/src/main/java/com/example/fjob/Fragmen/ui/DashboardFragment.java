package com.example.fjob.Fragmen.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.fastjson.JSON;
import com.example.fjob.Dao.LoginDao;
import com.example.fjob.DashboardViewModel;
import com.example.fjob.Entity.ChatEntity;
import com.example.fjob.Entity.job.JobMessage;
import com.example.fjob.Entity.job.ResumeEntity;
import com.example.fjob.Entity.job.ReturnResume;
import com.example.fjob.JobViewModel;
import com.example.fjob.R;
import com.example.fjob.ResumeViewModel;
import com.example.fjob.adapter.ChatAdapter;
import com.example.fjob.adapter.MyRecyclerViewAdapter;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class DashboardFragment extends Fragment {
private ChatAdapter chatAdapter;
private RecyclerView recyclerView;
private Socket socket;
private DataInputStream in;
private ChatAdapter.MyViewHolder myViewHolder;
private ResumeViewModel resumeViewModel;
  private   List<ChatEntity> allmsg=new ArrayList<>();
    public  DashboardFragment(){



        setHasOptionsMenu(true);
    }

    private DashboardViewModel dashboardViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) { dashboardViewModel = ViewModelProviders.of(this).get(DashboardViewModel.class);
       return inflater.inflate(R.layout.fragment_dashboard, container, false);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        recyclerView=requireActivity().findViewById(R.id.chat_recycle);
        resumeViewModel= ViewModelProviders.of(requireActivity()).get(ResumeViewModel.class);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));
       chatAdapter=new ChatAdapter();







        init();
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(requireActivity(), DividerItemDecoration.VERTICAL);
    }
    //分割线
    private void init() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    socket=new Socket("172.20.10.3",8081);
                    for (;;){

                        in=new DataInputStream(socket.getInputStream());
                        final String msg=in.readUTF();
                        Log.d("输入","...................."+msg);
                        ReturnResume returnResume= JSON.parseObject(msg,ReturnResume.class);
                      ReturnResume.DataBean dataBean=returnResume.getData();
                      ResumeEntity resumeEntity1=new ResumeEntity();
                        resumeEntity1.setEmail(dataBean.getEmail());
                        resumeEntity1.setAddressWork(dataBean.getAddressWork());
                        resumeEntity1.setBirthday(dataBean.getBirthday());
                        resumeEntity1.setIfMary(dataBean.getIfMary());
                        resumeEntity1.setPhone(dataBean.getPhone());
                        resumeEntity1.setQwer(dataBean.getQwer());
                        resumeEntity1.setPolitics(dataBean.getPolitics());
                        resumeEntity1.setTeached(dataBean.getTeached());
                        resumeEntity1.setShowbyshelf(dataBean.getShowbyshelf());
                        resumeEntity1.setWorkming(dataBean.getWorkming());
                        resumeEntity1.setYouname(dataBean.getYouname());

                        ChatEntity chatEntity=new ChatEntity();
                        chatEntity.setContent(dataBean.getYouname());
                        chatEntity.setPeople(dataBean.getWorkming());
                        allmsg.add(chatEntity);
                    //    Log.d("消息","............."+resumeEntity1);

                     requireActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {


                                chatAdapter.setMessage(allmsg);
                                chatAdapter.notifyDataSetChanged();
                                recyclerView.setAdapter(chatAdapter);
                            }
                        });



                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        }).start();

    }


}