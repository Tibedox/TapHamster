package ru.samsung.taphamster;

import static ru.samsung.taphamster.MainActivity.*;

import android.content.Context;
import android.graphics.Color;
import android.widget.ImageView;

import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.Random;

public class Ghost {
    ImageView img;
    int x;
    int y;
    int width;
    int stepX = 10;

    public Ghost(ConstraintLayout layout, int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;

        stepX = new Random().nextInt(10)+1;
        img = new ImageView(layout.getContext());
        ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams(width, height);
        img.setLayoutParams(params);
        img.setScaleType(ImageView.ScaleType.FIT_XY);
        img.setImageResource(R.drawable.ghost);
        layout.addView(img);
        img.setX(x);
        img.setY(y);
    }

    void move(){
        x += stepX;
        if(x>screenWidth-width || x<0) {
            stepX = -stepX;
        }
        //y+=10;
        img.setX(x);
        img.setY(y);
    }
}
