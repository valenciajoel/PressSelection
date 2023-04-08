package com.juego.seleccion1;

import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private FrameLayout mainLayout;
    private Random random;
    private List<Circle> circles;
    private int screenWidth;
    private int screenHeight;
    private int numCircles;
    private CountDownTimer countDownTimer;
    private boolean multipleWinners;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainLayout = findViewById(R.id.main_layout);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        screenHeight = displayMetrics.heightPixels;
        screenWidth = displayMetrics.widthPixels;
        numCircles = 0;
        circles = new ArrayList<>();
        multipleWinners = false;

        mainLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    Circle circle = new Circle(MainActivity.this, getRandomColor());
                    circles.add(circle);
                    mainLayout.addView(circle);
                    circle.startAnimation();
                }
                return true;
            }
        });
    }

    private int getRandomColor() {
        Random random = new Random();
        return Color.rgb(random.nextInt(256), random.nextInt(256), random.nextInt(256));
    }

    private void startCountDown(){
        countDownTimer = new CountDownTimer(5000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
            }

            @Override
            public void onFinish() {
                selectWinners();
            }
        }.start();
    }

    private void selectWinners() {
        int numWinners = multipleWinners ? 2 : 1;
        List<Integer> selectedIndexes = new ArrayList<>();
        random = new Random();

        while (selectedIndexes.size() < numWinners) {
            int index = random.nextInt(numCircles);
            if (!selectedIndexes.contains(index)) {
                selectedIndexes.add(index);
            }
        }

        for (int i = 0; i < numCircles; i++) {
            Circle circle = circles.get(i);
            if (selectedIndexes.contains(i)) {
                circle.startExitAnimation();
            } else {
                circle.startNonWinnerAnimation();
            }
        }
    }
}

