package com.enterprise.squatt.kp_color_thermometer;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by LudovicM on 08/10/2018.
 */

public class RoomScript extends AppCompatActivity {
    final String NAMEACTIVITY = "RoomScript Activity";

    private View viewTemperature = null;

    List<String> shades;
    int countDownInterval = 100;
    int millisInFuture = 10000;
    int[] timer = new int[] { 10000, 5000, 2500 };

    int startColor = 0;
    int endColor = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.room_script);

        // get parameter from parent
        Intent intent = getIntent();
        ArrayList<String> listColors = intent.getStringArrayListExtra(MainActivity.EXTRA_COLOR_LIST);
        shades = listColors;
        intent.getStringArrayListExtra(MainActivity.EXTRA_COLOR_LIST);

        // connect front with back
        viewTemperature = findViewById(R.id.viewTemperature);

        // initialize loop color
        millisInFuture = chooseTimeTransition();
        String colorOne = chooseShade(shades);
        String colorTwo = chooseShade(shades);
        startColor = Color.parseColor(colorOne);
        endColor = Color.parseColor(colorTwo);
        shadesTransition();
    }

    void shadesTransition() {
        new CountDownTimer(millisInFuture, countDownInterval) {

            public void onTick(long millisUntilFinished) {
                int diff = (int) millisUntilFinished / countDownInterval;
                int colorStep = computeTransitionColorStep(diff, startColor, endColor);
                viewTemperature.setBackgroundColor(colorStep);
            }

            public void onFinish() {
                millisInFuture = chooseTimeTransition();
                startColor = endColor;
                //String colorOne = chooseShade(shades);
                String colorTwo = chooseShade(shades);
                //startColor = Color.parseColor(colorOne);
                endColor = Color.parseColor(colorTwo);
                shadesTransition();
            }
        }.start();
    }

    int computeTransitionColorStep(double diff, int currentColor, int colorToReach) {
        double ratio = diff / countDownInterval;

        int RClose = (colorToReach >> 16) & 0xff;
        int GClose = (colorToReach >>  8) & 0xff;
        int BClose = (colorToReach      ) & 0xff;

        int R = (currentColor >> 16) & 0xff;
        int G = (currentColor >>  8) & 0xff;
        int B = (currentColor      ) & 0xff;

        int red = (int)Math.abs((ratio * R) + ((1 - ratio) * RClose));
        int green = (int)Math.abs((ratio * G) + ((1 - ratio) * GClose));
        int blue = (int)Math.abs((ratio * B) + ((1 - ratio) * BClose));

        return Color.rgb(red, green, blue);
    }

    int chooseTimeTransition() {
        Random rand = new Random();
        int n = rand.nextInt(timer.length);
        return timer[n];
    }

    String chooseShade(List<String> shades) {
        Random rand = new Random();
        int n = rand.nextInt(shades.size());
        return shades.get(n);
    }
}
