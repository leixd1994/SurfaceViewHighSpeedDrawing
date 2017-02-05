package com.example.leixd.surfaceviewhighspeeddrawing;

import android.content.Context;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by leixd on 17-2-5.
 */

public class FrontSurfaceView extends SurfaceView implements SurfaceHolder.Callback{
    FrontSurfaceView(Context context, AttributeSet attributeSet)
    {
        super(context,attributeSet);
    }
    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

    }
}
