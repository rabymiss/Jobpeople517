package com.example.fjob.text;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fjob.Entity.ChatEntity;
import com.example.fjob.R;
import com.example.fjob.adapter.ChatAdapter;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;


public class TestActivity extends AppCompatActivity {
    private ChatAdapter chatAdapter;
    private RecyclerView recyclerView;
    private Socket socket;
    private DataInputStream in;
    private List<ChatEntity> allmsg=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        recyclerView=findViewById(R.id.chat_recycle);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        chatAdapter=new ChatAdapter();

        init();


}

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
                        ChatEntity chatEntity=new ChatEntity();
                        chatEntity.setContent(msg);
                        allmsg.add(chatEntity);
                        Log.d("消息","............."+allmsg);
                      ;
                        runOnUiThread(new Runnable() {
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