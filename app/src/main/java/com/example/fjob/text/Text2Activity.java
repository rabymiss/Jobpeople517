package com.example.fjob.text;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fjob.R;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class Text2Activity extends AppCompatActivity {
    private Button button;
    private TextView textView;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text2);
        button = findViewById(R.id.button5asd);
        textView = findViewById(R.id.textView2asd);
        editText = findViewById(R.id.editTextasd);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            //监听指定服务器ip
                            Socket socket = new Socket("172.20.10.3", 8081);
                            //输出流

                            OutputStream os = socket.getOutputStream();
                            //写入数据
                            DataOutputStream dataOutputStream=new DataOutputStream(os);
                            dataOutputStream.writeUTF(editText.getText().toString());
//                            os.w(editText.getText().toString());
                         //   System.out.println(editText.getText().toString());
                            os.flush();
                            socket.shutdownOutput();
                            //输入流
                            InputStream is = socket.getInputStream();
                            //解析返回的数据
                         DataInputStream dataInputStream=new DataInputStream(is);
                      final String msg=dataInputStream.readUTF();
//                            BufferedReader br = new BufferedReader(reader);
//                            String s = "";
//                            final StringBuffer stringBuffer = new StringBuffer();
//                            while ((s = br.readLine()) != null) {
//                                stringBuffer.append(s);
//                            }
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    textView.setText(msg);
                                    System.out.println(msg);
                                }
                            });
//                            br.close();
//                            reader.close();
                            is.close();
                            os.close();
                            socket.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });
    }


}



//import android.annotation.SuppressLint;
//import android.app.Service;
//import android.os.Bundle;
//import android.os.Handler;
//import android.os.Message;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.example.fjob.R;
//
//import java.io.DataInputStream;
//import java.io.DataOutputStream;
//import java.io.IOException;
//import java.net.InetAddress;
//import java.net.Socket;
//import java.net.UnknownHostException;
//
//public class Text2Activity extends AppCompatActivity {
//
//    //IP地址和端口号
//    public static String IP_ADDRESS = "";
//    public static int PORT = 8080;
//    //三个控件
//    EditText et_message = null;  //需要发送的内容
//    Button bt_getAdress = null;   //获取本机IP地址
//    Button bt_connect = null;   //连接并发送
//    Button bt_startServer = null;   //启动服务端
//    TextView tv_adress = null;   //ip地址
//    TextView tv_reply = null;   //服务器回复的消息
//    //handler
//    Handler handler = null;
//    Socket soc = null;
//    DataOutputStream dos = null;
//    DataInputStream dis = null;
//    String messageRecv = null;
//
//    @SuppressLint("HandlerLeak")
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_text2);
//        et_message = findViewById(R.id.et_message);
//        bt_getAdress = findViewById(R.id.bt_getAdress);
//        bt_connect = findViewById(R.id.bt_connect);
//        bt_startServer = findViewById(R.id.bt_startServer);
//
//        tv_adress = findViewById(R.id.tv_adress);
//        tv_reply = findViewById(R.id.tv_reply);
//        bt_getAdress.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        try {
//                            InetAddress addr= null;
//                            try {
//                                addr = InetAddress.getLocalHost();
//                            } catch (UnknownHostException e) {
//                                e.printStackTrace();
//                            }
//                            //  InetAddress addr = InetAddress.getLocalHost();
//                 System.out.println("local host:"+addr);
//
//                            final InetAddress finalAddr = addr;
//                            runOnUiThread(new Runnable() {
//                     @Override
//                     public void run() {
//                         tv_adress.setText(finalAddr.toString().split("/")[1]);
//                       //  tv_adress.setText(addr.toString());
//                       // tv_adress.setText(addr.toString().split("/")[1]);
//                     }
//                 });
//               //   runOnUiThread(() -> tv_adress.setText(addr.toString().split("/")[1]));
//                        }catch (UnknownError e){
//                            e.printStackTrace();
//                        }
//
//
//                    }
//                }).start();
//            }
//        });
////        bt_getAdress.setOnClickListener(v -> {
////            new Thread(() -> {
////                try {
////                    InetAddress addr = InetAddress.getLocalHost();
////                    System.out.println("local host:"+addr);
////                    runOnUiThread(() -> tv_adress.setText(addr.toString().split("/")[1]));
////                } catch (UnknownHostException e) {
////                    e.printStackTrace();
////                }
////            }).start();
////        });
//
////        bt_startServer.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                new Thread(new Runnable() {
////                    @Override
////                    public void run() {
////
////                 new    Server().startService()).start();
////                    }
////                }).start();
////            }
////        });
//
////        bt_startServer.setOnClickListener(v -> {
////            new Thread(() -> new Server().startService()).start();
////            Toast.makeText(Text2Activity.this,"服务已启动",Toast.LENGTH_SHORT).show();
////        });
//
//        bt_connect.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                IP_ADDRESS = tv_adress.getText().toString();
//         new ConnectionThread(et_message.getText().toString()).start();
//            }
//        });
////        bt_connect.setOnClickListener(v -> {
////            IP_ADDRESS = tv_adress.getText().toString();
////            new ConnectionThread(et_message.getText().toString()).start();
////        });
//        handler =new Handler(){
//
//            @Override
//            public void handleMessage(Message msg){
//                Bundle b = msg.getData();  //获取消息中的Bundle对象
//         String str = b.getString("data");  //获取键为data的字符串的值
//          tv_reply.append(str);
//
//            }
//        };
//
////        handler = new Handler(msg -> {
////            Bundle b = msg.getData();  //获取消息中的Bundle对象
////            String str = b.getString("data");  //获取键为data的字符串的值
////            tv_reply.append(str);
////            return false;
////        });
//    }
//
//    //新建一个子线程，实现socket通信
//    class ConnectionThread extends Thread {
//        String message = null;
//
//        public ConnectionThread(String msg) {
//            message = msg;
//        }
//
//        @Override
//        public void run() {
//            if (soc == null) {
//                try {
//                    //Log.d("socket","new socket");
////                    if ("".equals(IP_ADDRESS)) {
////                        return;
////                    }
//                    soc = new Socket("172.20.10.3", PORT);
//                    //获取socket的输入输出流
//                    dis = new DataInputStream(soc.getInputStream());
//                    dos = new DataOutputStream(soc.getOutputStream());
//                } catch (IOException e) {
//                    // TODO Auto-generated catch block
//                    e.printStackTrace();
//                }
//            }
//            try {
//                dos.writeUTF(message);
//                dos.flush();
//                messageRecv = dis.readUTF();//如果没有收到数据，会阻塞
//                Message msg = new Message();
//                Bundle b = new Bundle();
//                b.putString("data", messageRecv);
//                msg.setData(b);
//                handler.sendMessage(msg);
//            } catch (IOException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
//        }
//    }
//
//}
