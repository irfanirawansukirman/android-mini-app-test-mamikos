package id.pamoyanan_dev.l_extras.util.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

//Replace RelativeLayout with any layout of your choice
public class FrameSquareLayout extends FrameLayout {

    public FrameSquareLayout(Context context) {
        super(context);
    }


    public FrameSquareLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public FrameSquareLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


// here we are returning the width in place of height, so width = height
// you may modify further to create any proportion you like ie. height = 2*width etc

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
        int size = MeasureSpec.getSize(widthMeasureSpec);
        setMeasuredDimension(size, size);
    }

}

