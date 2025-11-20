package ru.samsung.taphamster;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    static int screenWidth, screenHeight;
    int counter = 0;
    Handler handler;
    ConstraintLayout constraintLayout;
    TextView textView;
    TextView textTimer;
    ImageView imgHamster;
    Ghost[] ghost = new Ghost[25];
    long startTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        constraintLayout = findViewById(R.id.main);
        textView = findViewById(R.id.textBottom);
        textTimer = findViewById(R.id.textTime);
        imgHamster = findViewById(R.id.imgHamster);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        screenWidth = displayMetrics.widthPixels;
        screenHeight = displayMetrics.heightPixels;

        for (int i = 0; i < ghost.length; i++) {
            int x = new Random().nextInt(screenWidth-200)+100;
            int y = new Random().nextInt(screenHeight-200)+100;
            int w = new Random().nextInt(100)+100;
            int h = new Random().nextInt(100)+100;
            ghost[i] = new Ghost(constraintLayout, x, y, w, h);
        }

        imgHamster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter++;
                String ending;
                if(counter%10>1 && counter%10<5 && counter/10%10!=1)
                    ending = " раза";
                else
                    ending = " раз";
                String s = "Потрогано "+counter+ending;
                textView.setText(s);
            }
        });

        handler = new Handler(Looper.getMainLooper());
        startTime = System.currentTimeMillis();
        update();
    }

    void moveGhosts(){
        for (int i = 0; i < ghost.length; i++) {
            ghost[i].move();
        }
    }

    void update(){
        Runnable runnable = new Runnable(){
            @Override
            public void run() {
                updateTime();
                moveGhosts();
                handler.postDelayed(this, 16);
            }
        };
        handler.post(runnable);
    }

    void updateTime(){
        long currentTime = System.currentTimeMillis() - startTime;
        long timeSecundes = currentTime/1000;
        String timeHour = ""+timeSecundes/60/60;
        String timeMin = ":"+timeSecundes/60%60/10 + timeSecundes/60%60%10;
        String timeSec = ":" + timeSecundes%60/10 + timeSecundes%60%10;
        textTimer.setText(timeHour+timeMin+timeSec);
        //textTimer.setText(String.format("%s%s%s", timeHour, timeMin, timeSec));

    }
}