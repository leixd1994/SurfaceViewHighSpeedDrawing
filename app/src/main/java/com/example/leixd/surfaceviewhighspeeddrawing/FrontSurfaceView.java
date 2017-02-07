package com.example.leixd.surfaceviewhighspeeddrawing;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.os.CpuUsageInfo;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by leixd on 17-2-5.   //此类为划绿线线函数
 */

public class FrontSurfaceView extends SurfaceView implements SurfaceHolder.Callback{
    private SurfaceHolder surfaceHolder;
    private Canvas canvas;
    private Paint paint;
    private int pxBetweenPoint;               //点之间的间隔像素
    private int TotalPoint=600;   // 默认一个屏幕显示的点数
    int currentx=0;//绘图时的x坐标
    int lastdrawy=0;// 上次的y坐标
    public boolean isCreated=false;     //外部借口，判断控件是否已经创建完毕。
    private int surfacewidth,surfaceHeight;    //绘图控件的宽度和高度
    FrontSurfaceView(Context context, AttributeSet attributeSet)
    {
        super(context,attributeSet);
        surfaceHolder=getHolder();
        surfaceHolder.addCallback(this);
       paint=new Paint();
        paint.setColor(Color.GREEN);
        paint.setStrokeWidth(8);      //绿线粗度
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
public void update(final int []point)
{

    new Thread(new Runnable() {                   //开启新线程，避免阻塞主线程，让界面流畅。
        @Override
        public void run() {
            currentx=currentx+point.length;
            int pointnumber=point.length;
            int drawx=currentx;
            canvas=surfaceHolder.lockCanvas(new Rect(drawx,0,drawx+pointnumber*pxBetweenPoint,surfaceHeight));
           for (int t:point)
           {
               Log.e("Tag",""+t);
               canvas.drawLine(drawx,lastdrawy,drawx+pxBetweenPoint,t,paint);
               drawx=drawx+pxBetweenPoint;
               lastdrawy=t;
           }
            surfaceHolder.unlockCanvasAndPost(canvas);
        }
    }).start();

}

}
