package ru.samsung.taphamster;

import android.content.Context;
import android.graphics.Color;
import android.widget.ImageView;

import androidx.constraintlayout.widget.ConstraintLayout;

public class Ghost {
    ImageView img;
    int x;
    int y;

    Ghost(ConstraintLayout layout, int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
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
        x+=10;
        y+=10;
        img.setX(x);
        img.setY(y);
    }
}
