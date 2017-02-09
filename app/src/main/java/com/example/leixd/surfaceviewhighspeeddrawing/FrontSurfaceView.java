package com.example.leixd.surfaceviewhighspeeddrawing;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.os.CpuUsageInfo;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayDeque;

/**
 * Created by leixd on 17-2-5.   //此类为划绿线线函数
 */

public class FrontSurfaceView extends SurfaceView implements SurfaceHolder.Callback{
    private SurfaceHolder surfaceHolder;
    private Canvas canvas;
    private Paint paint;
    private int pxBetweenPoint;               //点之间的间隔像素
    private int TotalPoint=600;   // 默认一个屏幕显示的点数
    private int Startx=0;           //起始点坐标
    int currentx=0;//绘图时的x坐标
    int lastdrawy=0;// 上次的y坐标
    public boolean isCreated=false;     //外部借口，判断控件是否已经创建完毕。
    private int surfacewidth,surfaceHeight;    //绘图控件的宽度和高度
    private boolean isUpdateing=false;         //表示是否正在更新线程
    private ArrayDeque arrayDeque;            //表示需要绘制但尚未绘制的点队列
    private int linewight=8;                //绿线粗度
    FrontSurfaceView(Context context, AttributeSet attributeSet)
    {
        super(context,attributeSet);
        surfaceHolder=getHolder();
        surfaceHolder.addCallback(this);
       paint=new Paint();
        paint.setColor(Color.GREEN);
        paint.setStrokeWidth(linewight);      //绿线粗度
        paint.setAntiAlias(true);
        arrayDeque=new ArrayDeque();   //初始化ArrayDeque 队列
    }
    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
      this.setZOrderOnTop(true);
//this.setEGLConfigChooser(8, 8, 8, 8, 16, 0);
      this.getHolder().setFormat(PixelFormat.TRANSLUCENT);

        pxBetweenPoint=this.getWidth()/TotalPoint;     //初始化点之间的间隔
        isCreated=true;      //向外界表明控件已经创建完毕。
      surfacewidth=this.getWidth();surfaceHeight=this.getHeight();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

    }

    public void setLineWight(int t)             //设置绿线粗细
    {
        linewight=t;

            }
    public void setStartX(int t)       //设置起始点坐标
    {
        Startx=t;
        currentx=Startx;

    }
public  void  update(final int []point)                 //更新图像函数
{
    int pointnumber = point.length;

    int drawx = currentx*pxBetweenPoint;
    canvas = surfaceHolder.lockCanvas(new Rect(drawx, 0, drawx + pointnumber * pxBetweenPoint, surfaceHeight));
    canvas.drawColor(Color.WHITE);               //清空画布
    canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.SRC);

    for (int t:point)
    {

        if (drawx>1920) {currentx=Startx;drawx=currentx*pxBetweenPoint;  surfaceHolder.unlockCanvasAndPost(canvas);canvas = surfaceHolder.lockCanvas(new Rect(drawx, 0, drawx + pointnumber * pxBetweenPoint, surfaceHeight));canvas.drawColor(Color.WHITE);
            canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.SRC);   //清空画布
        }
        canvas.drawLine(drawx,lastdrawy,drawx+pxBetweenPoint,t,paint);
        drawx=drawx+pxBetweenPoint;
        lastdrawy=t;
    }
    currentx = currentx + point.length;
//Log.e("Thread Name",Thread.currentThread().getName());
    surfaceHolder.unlockCanvasAndPost(canvas);
    isUpdateing = false;
//}
}

    @Deprecated
    public  void  updateold(final int []point)                 //更新图像函数
    {


//synchronized (arrayDeque) {                        //锁定arrayDeque队列，保证线程安全
        for (int t:point)
        {
            arrayDeque.offer(t);                  //待优化
        }
        int pointnumber = point.length;
        int drawx = currentx*pxBetweenPoint;
        canvas = surfaceHolder.lockCanvas(new Rect(drawx, 0, drawx + pointnumber * pxBetweenPoint, surfaceHeight));

        while (arrayDeque.peek()!=null){
            int t=(int)arrayDeque.poll();

            canvas.drawLine(drawx, lastdrawy, drawx + pxBetweenPoint, t, paint);
            drawx = drawx + pxBetweenPoint;
            lastdrawy =t ;
        }
        currentx = currentx + point.length;
//Log.e("Thread Name",Thread.currentThread().getName());
        surfaceHolder.unlockCanvasAndPost(canvas);
        isUpdateing = false;
//}
    }


}
