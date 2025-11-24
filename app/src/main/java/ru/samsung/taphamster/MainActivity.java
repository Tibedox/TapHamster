package ru.samsung.taphamster;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
    TextView textTop;
    TextView textView;
    TextView textTimer;
    ImageView imgHamster;
    Ghost[] ghost = new Ghost[25];
    Hamsty hamsty;
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
        Toast.makeText(this, "onCreate", Toast.LENGTH_SHORT).show();
        constraintLayout = findViewById(R.id.main);
        textTop = findViewById(R.id.textTop);
        textView = findViewById(R.id.textBottom);
        textTimer = findViewById(R.id.textTime);
        imgHamster = findViewById(R.id.imgHamster);

        String top = getIntent().getStringExtra("name");
        top += ", "+textTop.getText().toString();
        textTop.setText(top);

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
        hamsty = new Hamsty(constraintLayout);

        hamsty.getImg().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, IntroActivity.class);
                startActivity(intent);
                finish();
            }
        });

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
        hamsty.move();
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

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this, "onStart", Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this, "onResume", Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(this, "onPause", Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(this, "onStop", Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "onDestroy", Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        Toast.makeText(this, "onRestart", Toast.LENGTH_SHORT).show();
    }
}