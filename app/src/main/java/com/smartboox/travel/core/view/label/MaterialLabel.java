package com.smartboox.travel.core.view.label;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.TextView;

import com.smartboox.travel.R;
import com.smartboox.travel.utils.FontUtil;

public class MaterialLabel extends TextView {
    private static final String FONT_PATH_ROBOTO_LIGHT = "fonts/roboto_light.ttf";
    private static final String FONT_PATH_ROBOTO_MEDIUM = "fonts/roboto_medium.ttf";
    private static final String FONT_PATH_ROBOTO_REGULAR = "fonts/roboto_regular.ttf";

    private static final int DISPLAY_TYPE_DISPLAY_4 = 1;
    private static final int DISPLAY_TYPE_DISPLAY_3 = 2;
    private static final int DISPLAY_TYPE_DISPLAY_2 = 3;
    private static final int DISPLAY_TYPE_DISPLAY_1 = 4;
    private static final int DISPLAY_TYPE_HEADLINE = 5;
    private static final int DISPLAY_TYPE_TITLE = 6;
    private static final int DISPLAY_TYPE_SUBHEAD = 7;
    private static final int DISPLAY_TYPE_BODY_2 = 8;
    private static final int DISPLAY_TYPE_BODY_1 = 9;
    private static final int DISPLAY_TYPE_CAPTION = 10;

    private static final int FONT_TYPE_ROBOTO_LIGHT = 1;
    private static final int FONT_TYPE_ROBOTO_REGULAR = 2;
    private static final int FONT_TYPE_ROBOTO_MEDIUM = 3;

    private Context mContext;

    public MaterialLabel(Context context) {
        super(context);
        initView(context);
    }

    public MaterialLabel(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
        initAttributes(context, attrs);
    }

    public MaterialLabel(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
        initAttributes(context, attrs);
    }

    protected void initView(Context context) {
        mContext = context;
    }

    private void initAttributes(Context context, AttributeSet attrs) {
        TypedArray displayTypeAttr = context.obtainStyledAttributes(attrs, R.styleable.MaterialLabel);
        int displayType = displayTypeAttr.getInt(R.styleable.MaterialLabel_display_type, 0);
        int fontType = displayTypeAttr.getInt(R.styleable.MaterialLabel_material_font, 0);
        displayTypeAttr.recycle();

        if (displayType != 0) {
            setTypeface(getFontByDisplayType(displayType));
            setTextSize(TypedValue.COMPLEX_UNIT_SP, getFontSizeByDisplayType(displayType));
        }
        if (fontType != 0) {
            setTypeface(getFont(fontType));
        }
    }

    private Typeface getFontByDisplayType(int displayType) {
        switch (displayType) {
            case DISPLAY_TYPE_DISPLAY_4:
                return FontUtil.loadFont(mContext, FONT_PATH_ROBOTO_LIGHT);
            case DISPLAY_TYPE_DISPLAY_3:
                return FontUtil.loadFont(mContext, FONT_PATH_ROBOTO_REGULAR);
            case DISPLAY_TYPE_DISPLAY_2:
                return FontUtil.loadFont(mContext, FONT_PATH_ROBOTO_REGULAR);
            case DISPLAY_TYPE_DISPLAY_1:
                return FontUtil.loadFont(mContext, FONT_PATH_ROBOTO_REGULAR);
            case DISPLAY_TYPE_HEADLINE:
                return FontUtil.loadFont(mContext, FONT_PATH_ROBOTO_REGULAR);
            case DISPLAY_TYPE_TITLE:
                return FontUtil.loadFont(mContext, FONT_PATH_ROBOTO_MEDIUM);
            case DISPLAY_TYPE_SUBHEAD:
                return FontUtil.loadFont(mContext, FONT_PATH_ROBOTO_REGULAR);
            case DISPLAY_TYPE_BODY_2:
                return FontUtil.loadFont(mContext, FONT_PATH_ROBOTO_MEDIUM);
            case DISPLAY_TYPE_BODY_1:
                return FontUtil.loadFont(mContext, FONT_PATH_ROBOTO_REGULAR);
            case DISPLAY_TYPE_CAPTION:
                return FontUtil.loadFont(mContext, FONT_PATH_ROBOTO_REGULAR);
            default:
                return FontUtil.loadFont(mContext, FONT_PATH_ROBOTO_REGULAR);
        }
    }

    private float getFontSizeByDisplayType(int displayType) {
        switch (displayType) {
            case DISPLAY_TYPE_DISPLAY_4:
                return 112f;
            case DISPLAY_TYPE_DISPLAY_3:
                return 56f;
            case DISPLAY_TYPE_DISPLAY_2:
                return 45f;
            case DISPLAY_TYPE_DISPLAY_1:
                return 34f;
            case DISPLAY_TYPE_HEADLINE:
                return 24f;
            case DISPLAY_TYPE_TITLE:
                return 20f;
            case DISPLAY_TYPE_SUBHEAD:
                return 16f;
            case DISPLAY_TYPE_BODY_2:
                return 14f;
            case DISPLAY_TYPE_BODY_1:
                return 14f;
            case DISPLAY_TYPE_CAPTION:
                return 12f;
            default:
                return 14f;
        }
    }

    private Typeface getFont(int fontType) {
        switch (fontType) {
            case FONT_TYPE_ROBOTO_LIGHT:
                return FontUtil.loadFont(mContext, FONT_PATH_ROBOTO_LIGHT);
            case FONT_TYPE_ROBOTO_REGULAR:
                return FontUtil.loadFont(mContext, FONT_PATH_ROBOTO_REGULAR);
            case FONT_TYPE_ROBOTO_MEDIUM:
                return FontUtil.loadFont(mContext, FONT_PATH_ROBOTO_MEDIUM);
            default:
                return FontUtil.loadFont(mContext, FONT_PATH_ROBOTO_REGULAR);
        }
    }
}
