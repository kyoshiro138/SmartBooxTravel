package com.smartboox.travel.core.view.button;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.Button;

import com.smartboox.travel.utils.FontUtil;

public class MaterialButton extends Button {
    private static final String FONT_PATH = "fonts/roboto_medium.ttf";
    private static final float FONT_SIZE = 14f;
    private static final int MIN_WIDTH = 88;

    public MaterialButton(Context context) {
        super(context);
        initView(context);
    }

    public MaterialButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public MaterialButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    protected void initView(Context context) {
        Typeface typeface = FontUtil.loadFont(context, FONT_PATH);
        setTypeface(typeface);

        setAllCaps(true);
        setTextSize(TypedValue.COMPLEX_UNIT_SP, FONT_SIZE);
        setMinimumWidth(MIN_WIDTH);
    }
}