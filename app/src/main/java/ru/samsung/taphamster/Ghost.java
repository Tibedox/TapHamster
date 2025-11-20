package ru.samsung.taphamster;

import static ru.samsung.taphamster.MainActivity.*;

import android.widget.ImageView;

import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.Random;

public class Ghost {
    ImageView img;
    int x, y;
    int width, height;
    int stepX, stepY;

    public Ghost(ConstraintLayout layout, int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        stepX = new Random().nextInt(21)-10;
        stepY = new Random().nextInt(21)-10;

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
        y += stepY;
        if(y>screenHeight-height || y<0) {
            stepY = -stepY;
        }
        img.setX(x);
        img.setY(y);
    }
}
