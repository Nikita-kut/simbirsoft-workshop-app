package com.nikita.kut.android.simbirsoft_workshop.util;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MaxCountLayoutManager extends LinearLayoutManager {

    private int maxCount = -1;

    public MaxCountLayoutManager(Context context) {
        super(context);
    }

    public MaxCountLayoutManager(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
    }

    public MaxCountLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void setMaxCount(int maxCount) {
        this.maxCount = maxCount;
    }

    @Override
    public void setMeasuredDimension(int widthSize, int heightSize) {
        int maxHeight = getMaxHeight();
        if (maxHeight > 0 && maxHeight < heightSize) {
            super.setMeasuredDimension(widthSize, maxHeight);
        }
        else {
            super.setMeasuredDimension(widthSize, heightSize);
        }
    }

    private int getMaxHeight() {
        if (getChildCount() == 0 || maxCount <= 0) {
            return 0;
        }

        View child = getChildAt(0);
        int height = child.getHeight();
        final RecyclerView.LayoutParams lp = (RecyclerView.LayoutParams) child.getLayoutParams();
        height += lp.topMargin + lp.bottomMargin;
        return height*maxCount+getPaddingBottom()+getPaddingTop();
    }
}
