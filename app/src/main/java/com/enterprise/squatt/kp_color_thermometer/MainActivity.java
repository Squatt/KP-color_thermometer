package com.enterprise.squatt.kp_color_thermometer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_COLOR_LIST = "com.enterprise.squatt.COLOR_LIST";

    private Button room1Button = null;
    private Button room2Button = null;
    private Button room3Button = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        // Get UI button
        room1Button = findViewById(R.id.buttonRoom1);
        room2Button = findViewById(R.id.buttonRoom2);
        room3Button = findViewById(R.id.buttonRoom3);

        room1Button.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v)
            {
                // Initialize colors for Room One
                Intent intent = new Intent(MainActivity.this, RoomScript.class);

                ArrayList<String> colors = new ArrayList<>();
                colors.add("#FF9000");
                colors.add("#FFBA00");
                colors.add("#FFC800");

                intent.putStringArrayListExtra(EXTRA_COLOR_LIST, colors);

                startActivity(intent);
            }
        });

        room2Button.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v)
            {
                // Initialize colors for Room Two
                Intent intent = new Intent(MainActivity.this, RoomScript.class);

                ArrayList<String> colors = new ArrayList<>();
                colors.add("#FF9000");
                colors.add("#FF6500");
                colors.add("#FF4D00");

                intent.putStringArrayListExtra(EXTRA_COLOR_LIST, colors);

                startActivity(intent);
            }
        });

        room3Button.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v)
            {
                // Initialize colors for Room Three
                Intent intent = new Intent(MainActivity.this, RoomScript.class);

                ArrayList<String> colors = new ArrayList<>();
                colors.add("#FF9000");
                colors.add("#FFD300");
                colors.add("#FFE800");

                intent.putStringArrayListExtra(EXTRA_COLOR_LIST, colors);

                startActivity(intent);
            }
        });

    }
}
