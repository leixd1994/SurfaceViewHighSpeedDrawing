package com.example.leixd.surfaceviewhighspeeddrawing;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Toast;

/**
 * Created by 雷晓东 on 17-1-30.    //此SurfaceView用来绘制背景网格
 */

public class MySurfaceView extends SurfaceView implements SurfaceHolder.Callback {
    private SurfaceHolder surfaceHolder;
    private Paint paint,paintGreen;

    private DisplayMetrics metrics;             //屏幕相关的数据


    private float []TotalPoint2;    

    Bitmap backgroundbitmap;             //背景Bitmap
    private Canvas canvas,surfacecanvas;

    public MySurfaceView(Context context, AttributeSet attributeSet)
    {
        super(context,attributeSet);
        surfaceHolder=getHolder();
        surfaceHolder.addCallback(this);

    }

    public void init(DisplayMetrics metrics,int weight,int height)            //得到metrics并初始化控件
    {
        paint=new Paint();
        paint.setColor(Color.WHITE);
        paintGreen=new Paint();
        paintGreen.setColor(Color.GREEN);
        this.metrics=metrics;



    }
    public Boolean setLineSize(float size)   //设置线宽
    {
        paintGreen.setStrokeWidth(size);
        return true;
    }

    public Boolean update(float []point)            //更新心电图的部分
    {

        return true;
    }


    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        backgroundbitmap=Bitmap.createBitmap(this.getWidth(),this.getHeight(), Bitmap.Config.ARGB_8888);  //建立内存中的Bitmap，先将背景绘制到内存中，再一次性绘制到屏幕上，提高效率。


        canvas=new Canvas();
        canvas.setBitmap(backgroundbitmap);
        canvas.drawColor(Color.BLACK);  //绘制黑色背景
        while(metrics==null);   //等待主函数传入metrics

        final float test= TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_MM,5,metrics);  //获得5mm对应的像素数量
        float drawy=0;
        while (drawy<=canvas.getHeight()) {                                //绘制网格竖线
            canvas.drawLine(0, drawy, canvas.getWidth(), drawy, paint);
            drawy=drawy+test;
        }
        drawy=0;
        while (drawy<=canvas.getWidth()) {                               //绘制网格横线
            canvas.drawLine(drawy, 0, drawy, canvas.getHeight(), paint);
            drawy=drawy+test;
        }
        surfacecanvas=surfaceHolder.lockCanvas();
        surfacecanvas.drawBitmap(backgroundbitmap,0,0,paint);    //将内存中的图像绘制到屏幕上
        surfaceHolder.unlockCanvasAndPost(surfacecanvas);


    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }
}
