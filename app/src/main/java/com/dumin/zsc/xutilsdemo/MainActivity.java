package com.dumin.zsc.xutilsdemo;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    textView.setText("handleMessage方法！");//更新UI
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.tv);
        post();//调用post方法
        handleMessage();//调用handleMessage方法
    }
    public void handleMessage()
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);//在子线程有一段耗时操作,比如请求网络
                    mHandler.sendEmptyMessage(0);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    public void post()
    {
        new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);//在子线程有一段耗时操作,比如请求网络
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            textView.setText("post 方法！");//更新UI
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
