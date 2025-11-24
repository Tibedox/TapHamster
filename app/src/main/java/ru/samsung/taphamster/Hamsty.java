package ru.samsung.taphamster;

import androidx.constraintlayout.widget.ConstraintLayout;
import static ru.samsung.taphamster.MainActivity.*;

public class Hamsty extends Ghost{
    public Hamsty(ConstraintLayout layout) {
        super(layout, screenWidth/2, screenHeight/2, 300, 300);
        getImg().setImageResource(R.drawable.hamster);
    }
}
