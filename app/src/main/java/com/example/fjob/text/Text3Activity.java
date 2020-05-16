package com.example.fjob.text;



import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ScrollView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fjob.Entity.RegisterEntity;
import com.example.fjob.R;
import com.example.fjob.adapter.MyRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;


public class Text3Activity extends AppCompatActivity {
    private RecyclerView recyclerView;
private MyRecyclerViewAdapter myRecyclerViewAdapter;
   private List<RegisterEntity>  dataList = new ArrayList<>();
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text3);
        recyclerView=findViewById(R.id.chat_recycle11);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
         dataList = new ArrayList<>();
        RegisterEntity registerEntity=new RegisterEntity("12321","2333");
        dataList.add(registerEntity);
          Log.d("asd","------------------" +dataList);
          System.out.println("数据"+dataList);
      myRecyclerViewAdapter =new MyRecyclerViewAdapter(  recyclerView );
        recyclerView.setAdapter(myRecyclerViewAdapter);
        myRecyclerViewAdapter.setData(dataList);
         myRecyclerViewAdapter.notifyDataSetChanged();
         initView();

}

    private void initView() {
        myRecyclerViewAdapter.setItemListener(new MyRecyclerViewAdapter.onRecyclerItemClickerListener() {
            @Override
            public void onRecyclerItemClick(View view, Object data, int position) {
                List<RegisterEntity>  dataList = new ArrayList<>();
              //  myRecyclerViewAdapter.getDataList().set(dataList.get(position),data+"213213");
                myRecyclerViewAdapter.notifyItemChanged(position);
                Toast.makeText(getApplicationContext(),"asdasd",Toast.LENGTH_SHORT).show();
            }
        });


    }


}