package com.example.view;

import android.graphics.drawable.ColorDrawable;

public class DividerDrawable extends ColorDrawable {

    private int height;

    public DividerDrawable(int color, int height) {
        super(color);
        this.height = height;
    }

    @Override
    public int getIntrinsicHeight() {
        return height;
    }
}
