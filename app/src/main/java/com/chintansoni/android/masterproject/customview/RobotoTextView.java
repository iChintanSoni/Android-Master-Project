package com.chintansoni.android.masterproject.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

import com.chintansoni.android.masterproject.R;

/**
 * Created by Chintan Soni - Senior Software Engineer (Android).
 */

public class RobotoTextView extends AppCompatTextView {
    public RobotoTextView(Context context) {
        super(context);
    }

    public RobotoTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setFont(attrs);
    }

    public RobotoTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setFont(attrs);
    }

    public void setFont(AttributeSet attributeSet) {
        if (attributeSet != null) {
            TypedArray typedArray = getContext().obtainStyledAttributes(attributeSet, R.styleable.roboto);
            int font = typedArray.getInteger(R.styleable.roboto_font, 9);
            Typeface myTypeface = Typeface
                    .createFromAsset(
                            getContext().getAssets(),
                            "fonts/" + getResources().getStringArray(R.array.roboto_fonts)[font]
                    );
            setTypeface(myTypeface);
            typedArray.recycle();
        }
    }
}
