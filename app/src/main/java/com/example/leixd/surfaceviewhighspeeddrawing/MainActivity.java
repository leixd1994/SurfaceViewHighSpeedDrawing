package com.example.leixd.surfaceviewhighspeeddrawing;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);


        final MySurfaceView mySurfaceView = (MySurfaceView)findViewById(R.id.surfaceview);
        mySurfaceView.init(metrics,800,800);
        mySurfaceView.setLineSize(5);              //设置线宽
//        final Handler handler=new Handler(){
//            @Override
//            public void handleMessage(Message msg) {
//
//                switch (msg.what)
//                {
//                    case 1:
//                        float []t=new float[5];
//                        for (int t0=0;t0<5;t0++)
//                        {
//                            t[t0]=114;
//
//                        }
//                        mySurfaceView.update(t)
//                        ;break;
//                    case 2:
//                        float []t3=new float[5];
//                        for (int t0=0;t0<5;t0++)
//                        {
//                            t3[t0]=414;
//
//                        }
//                        mySurfaceView.update(t3)
//                        ;break;
//
//                }
//
//                super.handleMessage(msg);
//            }
//        };
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                while (true) {
//                    handler.sendEmptyMessage(1);
//                    try {
//                        Thread.sleep(200);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    handler.sendEmptyMessage(2);
//                }
//            }
//        }).start();


    }
}
