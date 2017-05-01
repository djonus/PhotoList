package lt.danjon.task.ui.widgets;

import android.support.v7.widget.RecyclerView;

public abstract class BottomScrollListener extends RecyclerView.OnScrollListener {

    @Override
    public void onScrollStateChanged(RecyclerView rv, int state) {
        super.onScrollStateChanged(rv, state);
        if (rv.getLayoutManager().getItemCount() > 0 && rv.getChildAdapterPosition(rv.getLayoutManager().getChildAt(rv.getLayoutManager().getChildCount() - 1)) == rv.getAdapter().getItemCount() - 1) {
            onScrolledToBottom();
        }
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
    }

    public abstract void onScrolledToBottom();
}
