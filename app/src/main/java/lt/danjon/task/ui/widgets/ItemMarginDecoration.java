package lt.danjon.task.ui.widgets;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import lt.danjon.task.R;

public class ItemMarginDecoration extends RecyclerView.ItemDecoration {

    private int margin;

    public ItemMarginDecoration(int margin) {
        this.margin = margin;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                               RecyclerView.State state) {

        outRect.left = margin;
        outRect.top = margin;
        outRect.right = margin;
        outRect.bottom = margin;
    }
}
