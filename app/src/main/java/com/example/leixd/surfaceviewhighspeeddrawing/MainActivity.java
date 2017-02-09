package com.example.leixd.surfaceviewhighspeeddrawing;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
private FrontSurfaceView frontSurfaceView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);


        final MySurfaceView mySurfaceView = (MySurfaceView)findViewById(R.id.surfaceview);

        frontSurfaceView=(FrontSurfaceView)findViewById(R.id.frontview) ;
        mySurfaceView.init(metrics,800,800);
        mySurfaceView.setLineSize(5);              //设置线宽

     new Thread(new Runnable() {              //启动新线程等待FrontSurfaceView创建完毕后，向其发送update指令。
         @Override
         public void run() {
             while (frontSurfaceView.isCreated==false) {
                 try {
                     Thread.sleep(100);
                 } catch (InterruptedException e) {
                     e.printStackTrace();
                 }
             }

             int []t=new int[20];
             for (int i=0;i<20;i++)
             {t[i]=200;}
             int []t2=new int[20];
             for (int i=0;i<20;i++)
             {t2[i]=400;}
             int []t3=new int[20];
             for (int i=0;i<20;i++)
             {t3[i]=600;}
             frontSurfaceView.update(t);
             try {
                 Thread.sleep(1000);
             } catch (InterruptedException e) {
                 e.printStackTrace();
             }
             frontSurfaceView.update(t2);
             try {
                 Thread.sleep(1000);
             } catch (InterruptedException e) {
                 e.printStackTrace();
             }
             frontSurfaceView.update(t3);
         }
     }).start();


    }
}
