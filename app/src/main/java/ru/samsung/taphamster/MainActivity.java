package ru.samsung.taphamster;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    int counter = 0;
    ConstraintLayout constraintLayout;
    TextView textView;
    ImageView imgHamster;
    Ghost[] ghost = new Ghost[5];

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
        imgHamster = findViewById(R.id.imgHamster);
        ghost[0] = new Ghost(constraintLayout, 500, 1000, 200, 200);
        ghost[1] = new Ghost(constraintLayout, 50, 10, 100, 100);
        ghost[2] = new Ghost(constraintLayout, 700, 1500, 250, 180);
        ghost[3] = new Ghost(constraintLayout, 500, 0, 150, 210);
        ghost[4] = new Ghost(constraintLayout, 320, 230, 180, 140);

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
                move();
            }
        });
    }

    void move(){
        for (int i = 0; i < 5; i++) {
            ghost[i].move();
        }
    }
}