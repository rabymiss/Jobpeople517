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
import com.example.fjob.ui.login.AddJobActivity;
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
    public  HomeFragment(){



        setHasOptionsMenu(true);
    }



    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_job, container, false);
    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        jobViewModel= ViewModelProviders.of(requireActivity()).get(JobViewModel.class);
        recyclerView =requireActivity().findViewById(R.id.recycler_view_job_show);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));
        myAdapter=new MyAdapter();
        jobAdapter =new JobAdapter();






        //分割线
        dividerItemDecoration=new DividerItemDecoration(requireActivity(),DividerItemDecoration.VERTICAL);
        fileJobs=jobViewModel.getAllJobsLive();//+
        fileJobs.observe(requireActivity(), new Observer<List<JobMessage>>() {
            @Override
            public void onChanged(List<JobMessage> jobMessages) {

                recyclerView.addItemDecoration(dividerItemDecoration);

                // int temp =myAdapter.getItemCount();
                myAdapter.setAllJobs(jobMessages);
                myAdapter.notifyDataSetChanged();

            }
        });
        recyclerView.setAdapter(myAdapter);//????

//滑动删除
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.START|ItemTouchHelper.END) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            //    JobMessage delete=allJobs.get(         viewHolder.getAdapterPosition()   );
              //  Log.d("s","消息..................."+delete.toString());

                Toast.makeText(requireActivity(),"啥也没发生",Toast.LENGTH_SHORT).show();
//            JobMessage d=allJobs.get(viewHolder.getAdapterPosition());
//           jobViewModel.deleteJobs(d);

//                Snackbar.make(requireActivity().findViewById(R.id.nav_host_fragment),"删除了一个词汇",Snackbar.LENGTH_SHORT)
//                     .setAction("撤销", new View.OnClickListener() {
//                          @Override
//                          public void onClick(View view) {
////                               undoaction=true;
////                              jobViewModel.insertJobs();
//                           }
//                        }).show();
            }
        }).attachToRecyclerView(recyclerView);



    }



    //...............................................搜索..............
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.search_menu,menu);
        SearchView searchView = (SearchView) menu.findItem(R.id.app_bar_search).getActionView();
        searchView.setMaxWidth(500);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                String pattern = newText.trim();
                fileJobs.removeObservers(requireActivity());
                fileJobs = jobViewModel.findAllJobsLive(pattern);
                fileJobs.observe(requireActivity(), new Observer<List<JobMessage>>() {
                    @Override
                    public void onChanged(List<JobMessage> msg) {
                        int temp = myAdapter.getItemCount();
                        myAdapter.setAllJobs(msg);
                        if (temp!=msg.size()) {

                            myAdapter.notifyDataSetChanged();
                        }
                    }
                });
                return true;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {

            case R.id.search_menu_job:
                Intent intent=new Intent(requireActivity(), AddJobActivity.class);
                startActivity(intent);
                break;

            case R.id.menu_bar_refresh_job:

                jobViewModel.deleteAllJobs();
                myAdapter.notifyDataSetChanged();
//加载招聘信息

            Retrofit retrofit  =new Retrofit.Builder()
                    .baseUrl("http:/172.20.10.3:8080")
                    .addConverterFactory(GsonConverterFactory.create()).build();
            Api api  =  retrofit.create(Api.class);
            Call<JobResultEntity> task=api.getJson();
            task.enqueue(new Callback<JobResultEntity>() {
                @Override
                public void onResponse(Call<JobResultEntity> call, Response<JobResultEntity> response) {
                    //  Log.d(TAG,"Json-->"+response.code());


                    if (response.code()== HttpsURLConnection.HTTP_OK){
                        Log.d(TAG,"成功"+response.body());
                        JobResultEntity jobResultEntity=response.body();

                        data2.addAll(jobResultEntity.getData());


                        for (  JobResultEntity.DataBean dataBean:data2  ){

                            JobMessage jobMessage=new JobMessage(dataBean.getJobConditionOne()
                                    ,dataBean.getJobConditionTwo()
                                    ,dataBean.getJobName()
                                    ,dataBean.getJobPay()
                                    ,dataBean.getCpnName());
                            Log.d(TAG,"data------------------------------"+jobMessage.getCpnName());

                            jobViewModel.insertJobs(jobMessage);
                            myAdapter.notifyDataSetChanged();
                        }

                        Log.d(TAG,"data------------------------------"+data);

                    }

                }

                @Override
                public void onFailure(Call<JobResultEntity> call, Throwable t) {
                    Log.d(TAG,"失败-->"+t.toString());
                    Toast.makeText(requireActivity(),t.toString(),Toast.LENGTH_SHORT).show();
                }
            });



                //..................

                break;
                default:
        }
      return super.onOptionsItemSelected(item);
    }

//..................................................................

//menu 的功能



}