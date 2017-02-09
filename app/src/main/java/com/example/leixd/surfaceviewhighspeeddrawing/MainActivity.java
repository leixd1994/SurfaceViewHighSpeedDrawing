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
                     Thread.sleep(10);
                 } catch (InterruptedException e) {
                     e.printStackTrace();
                 }
             }
double i=1;
                 frontSurfaceView.setStartX(20);
             while (true) {

                 int []point=new int[10];
                 for (int i2=0;i<10;i++) {
                     point[i2]=(int) (Math.sin(Math.toRadians(i)) * 300) + 300;
                     i = i + 1;
                 }
                 frontSurfaceView.update(point);

             }
         }
     }).start();


    }
}
