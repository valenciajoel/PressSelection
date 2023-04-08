package com.juego.seleccion1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

public class Circle extends View {
    private Paint paint;
    private Animation animation;

    public Circle(Context context, int randomColor){
        super(context);
        init();
    }

    private void init(){
        paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        paint.setColor(Color.BLACK);


        animation = AnimationUtils.loadAnimation(getContext(),R.anim.circle_enter_animation);
    }

    public void setColor(int color){
        paint.setColor(color);
    }

    protected void onDraw(Canvas canvas){
        int x = getWidth() / 2;
        int y = getHeight() / 2;
        int radius = Math.min(x,y);

        canvas.drawCircle(x,y, radius, paint);
    }

    public void startAnimation(){
        startAnimation(animation);
    }

    public void startExitAnimation(){
        animation = AnimationUtils.loadAnimation(getContext(), R.anim.circle_exit_animation);
    }

    public void startNonWinnerAnimation() {
    }
}
