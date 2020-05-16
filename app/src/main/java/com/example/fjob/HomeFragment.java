package com.example.fjob;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fjob.Api.Api;
import com.example.fjob.Entity.job.JobMessage;
import com.example.fjob.Entity.job.JobResultEntity;
import com.example.fjob.Entity.job.ReturnJobMessageAll;
import com.example.fjob.adapter.JobAdapter;
import com.example.fjob.common.Common;
import com.example.fjob.ui.login.AddJobActivity;
import com.example.fjob.views.PagerItem;
import com.example.fjob.views.SobLooperPager;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class HomeFragment extends Fragment {
    private FloatingActionButton floatingActionButton;
    private JobViewModel jobViewModel;
    private MyAdapter myAdapter;
    private JobAdapter jobAdapter;
    private RecyclerView recyclerView;
    private List<JobMessage>allJobs;
    private LiveData<List<JobMessage>> fileJobs;
    private DividerItemDecoration dividerItemDecoration;
    private Button buttonRefresh;
    private static final String TAG = "ButtomActivity";
    private List<ReturnJobMessageAll.DataBean> data=new ArrayList<>();
    private List<JobResultEntity.DataBean> data2=new ArrayList<>();
    //广告
    private SobLooperPager mLooperPager;
    private List<PagerItem> mData = new ArrayList<>();
    public  HomeFragment(){



        setHasOptionsMenu(true);
    }



    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_home, container, false);
    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        jobViewModel= ViewModelProviders.of(requireActivity()).get(JobViewModel.class);
        recyclerView =requireActivity().findViewById(R.id.recycler_view_job_show);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));
        myAdapter=new MyAdapter();
        jobAdapter =new JobAdapter();
//        广告
        initData();
        initView();
        initEven();
        //................



//
//        //分割线
//        dividerItemDecoration=new DividerItemDecoration(requireActivity(),DividerItemDecoration.VERTICAL);
//        fileJobs=jobViewModel.getAllJobsLive();//+
//        fileJobs.observe(requireActivity(), new Observer<List<JobMessage>>() {
//            @Override
//            public void onChanged(List<JobMessage> jobMessages) {
//
//                recyclerView.addItemDecoration(dividerItemDecoration);
//
//                // int temp =myAdapter.getItemCount();
//                myAdapter.setAllJobs(jobMessages);
//                myAdapter.notifyDataSetChanged();
//
//            }
//        });
//        recyclerView.setAdapter(myAdapter);//????




    }

    private void initEven() {

        if(mLooperPager != null) {
            mLooperPager.setOnItemClickListener(new SobLooperPager.OnItemClickListener() {
                @Override
                public void onItemClick(int position) {
                    Toast.makeText(getContext(),"点击了第" + (position + 1) + "个item", Toast.LENGTH_SHORT).show();
                    //todo:根据交互业务去实现具体逻辑
                }
            });
        }

    }
//
    private void initView() {
        mLooperPager = requireActivity().findViewById(R.id.sob_looper_pager);
        mLooperPager.setData(mInnerAdapter,new SobLooperPager.BindTitleListener() {
            @Override
            public String getTitle(int position) {
                return mData.get(position).getTitle();
            }
        });
    }
    private SobLooperPager.InnerAdapter mInnerAdapter = new SobLooperPager.InnerAdapter() {
        @Override
        protected int getDataSize() {
            return mData.size();
        }

        @Override
        protected View getSubView(ViewGroup container, int position) {
            ImageView iv = new ImageView(container.getContext());
            iv.setImageResource(mData.get(position).getPicResId());
            iv.setScaleType(ImageView.ScaleType.FIT_XY);
            return iv;
        }
    };
    private void initData() {
        mData.add(new PagerItem("第一个图片",R.mipmap.pic0));
        mData.add(new PagerItem("第2个图片",R.mipmap.pic1));
        mData.add(new PagerItem("第三个图片",R.mipmap.pic2));
        mData.add(new PagerItem("第4个图片",R.mipmap.pic3));
    }
//....................................................

//    //...............................................搜索..............
//    @Override
//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        super.onCreateOptionsMenu(menu, inflater);
//        inflater.inflate(R.menu.search_menu,menu);
//        SearchView searchView = (SearchView) menu.findItem(R.id.app_bar_search).getActionView();
//        searchView.setMaxWidth(500);
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                String pattern = newText.trim();
//                fileJobs.removeObservers(requireActivity());
//                fileJobs = jobViewModel.findAllJobsLive(pattern);
//                fileJobs.observe(requireActivity(), new Observer<List<JobMessage>>() {
//                    @Override
//                    public void onChanged(List<JobMessage> msg) {
//                        int temp = myAdapter.getItemCount();
//                        myAdapter.setAllJobs(msg);
//                        if (temp!=msg.size()) {
//
//                            myAdapter.notifyDataSetChanged();
//                        }
//                    }
//                });
//                return true;
//            }
//        });
//    }

//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        switch (item.getItemId()) {
//
//            case R.id.search_menu_job:
//                Intent intent=new Intent(requireActivity(), AddJobActivity.class);
//                startActivity(intent);
//                break;
//
//            case R.id.menu_bar_refresh_job:

//                jobViewModel.deleteAllJobs();
//                myAdapter.notifyDataSetChanged();
////加载招聘信息
//
//
//            Call<JobResultEntity> task= Common.apicommont.getJson();
//            task.enqueue(new Callback<JobResultEntity>() {
//                @Override
//                public void onResponse(Call<JobResultEntity> call, Response<JobResultEntity> response) {
//                    //  Log.d(TAG,"Json-->"+response.code());
//
//
//                    if (response.code()== HttpsURLConnection.HTTP_OK){
//                        Log.d(TAG,"成功"+response.body());
//                        JobResultEntity jobResultEntity=response.body();
//
//                        data2.addAll(jobResultEntity.getData());
//
//
//                        for (  JobResultEntity.DataBean dataBean:data2  ){
//
//                            JobMessage jobMessage=new JobMessage(dataBean.getJobConditionOne()
//                                    ,dataBean.getJobConditionTwo()
//                                    ,dataBean.getJobName()
//                                    ,dataBean.getJobPay()
//                                    ,dataBean.getCpnName());
//                            Log.d(TAG,"data------------------------------"+jobMessage.getCpnName());
//
//                            jobViewModel.insertJobs(jobMessage);
//                            myAdapter.notifyDataSetChanged();
//                        }
//
//                        Log.d(TAG,"data------------------------------"+data);
//
//                    }
//
//                }
//
//                @Override
//                public void onFailure(Call<JobResultEntity> call, Throwable t) {
//                    Log.d(TAG,"失败-->"+t.toString());
//                    Toast.makeText(requireActivity(),t.toString(),Toast.LENGTH_SHORT).show();
//                }
//            });
//
//
//
//                //..................
//
//                break;
//                default:

//      return super.onOptionsItemSelected(item);
//    }

//..................................................................





}