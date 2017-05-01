package lt.danjon.task.ui.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import lt.danjon.task.R;

public class AspectRatioLayout extends FrameLayout {

    private float aspectRatio;

    public AspectRatioLayout(Context context) {
        this(context, null);
    }

    public AspectRatioLayout(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.getTheme().obtainStyledAttributes(attrs,
                R.styleable.AspectRatioLayout, 0, 0);

        try {
            aspectRatio = a.getFloat(R.styleable.AspectRatioLayout_aspectRatio, 0);
        } finally {
            a.recycle();
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (aspectRatio != 0f) {
            int height = (int) (MeasureSpec.getSize(widthMeasureSpec) * aspectRatio);
            int heightSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);
            super.onMeasure(widthMeasureSpec, heightSpec);
        } else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }

    public void setAspectRatio(float aspectRatio) {
        this.aspectRatio = aspectRatio;
        invalidate();
    }
}
