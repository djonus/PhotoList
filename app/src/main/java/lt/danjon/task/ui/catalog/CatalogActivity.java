package lt.danjon.task.ui.catalog;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.trello.rxlifecycle.android.ActivityEvent;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import lt.danjon.task.R;
import lt.danjon.task.TestApp;
import lt.danjon.task.ui.ListLoader;
import lt.danjon.task.ui.RxCompatActivity;
import lt.danjon.task.ui.model.Photo;
import lt.danjon.task.ui.widgets.BottomScrollListener;
import lt.danjon.task.ui.widgets.ItemMarginDecoration;
import rx.android.schedulers.AndroidSchedulers;

public class CatalogActivity extends RxCompatActivity implements PhotosAdapter.ItemClickListener{

    @Inject ListLoader<Photo> listLoader;

    @BindView(R.id.list) RecyclerView list;
    @BindView(R.id.swiperefresh) SwipeRefreshLayout svipeRefresh;

    private PhotosAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((TestApp) getApplication()).getAppComponent().inject(this);

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        list.setLayoutManager(new GridLayoutManager(this, getSpanCount()));
        list.setItemAnimator(new DefaultItemAnimator());
        int itemMargin = getResources().getDimensionPixelSize(R.dimen.item_margin);
        list.addItemDecoration(new ItemMarginDecoration(itemMargin));
        list.addOnScrollListener(new BottomScrollListener() {
            @Override
            public void onScrolledToBottom() {
                if (listLoader.more()) progress(true);
            }
        });

        svipeRefresh.setOnRefreshListener(this::refresh);

        //listLoader.list() is used to init adapter to maintain scroll position out of the box
        adapter = new PhotosAdapter(listLoader.list(), this);
        list.setAdapter(adapter);

        listLoader.observe()
                .observeOn(AndroidSchedulers.mainThread())
                .compose(bindUntilEvent(ActivityEvent.DESTROY))
                .subscribe(photos -> {
                    addPhotos(photos);
                    progress(false);
                }, throwable -> {
                    progress(false);
                    error(throwable);
                });

        if (null == savedInstanceState) {
            listLoader.refresh();
        }
    }

    @Override
    public void onItemClick(View view) {
        // TODO: 01/05/2017 Use shared element transition
//        View image = view.findViewById(R.id.iv_photo);
//        View title = view.findViewById(R.id.tv_title);
//
//        Pair<View, String> pair0 = Pair.create(view, "container");
//        Pair<View, String> pair1 = Pair.create(image, "photo");
//        Pair<View, String> pair2 = Pair.create(title, "title");
//        ActivityOptionsCompat options = ActivityOptionsCompat.
//                makeSceneTransitionAnimation(this, pair0, pair1, pair2);

        int position = list.getChildAdapterPosition(view);
        Photo photo = adapter.getItem(position);
        Intent intent = new Intent(this, PreviewActivity.class);
        intent.putExtra(PreviewActivity.EXTRA_PHOTO, photo);

//        startActivity(intent, options.toBundle());
        startActivity(intent);
    }

    private void addPhotos(List<Photo> photos) {
        adapter.add(photos);
        int count = adapter.getItemCount();
        adapter.notifyItemRangeInserted(count, count + photos.size());
    }

    private void refresh() {
        progress(true);
        adapter.clear();
        adapter.notifyDataSetChanged();
        listLoader.refresh();
    }

    private void error(Throwable throwable) {
        Toast.makeText(this, throwable.getMessage(), Toast.LENGTH_SHORT).show();
    }

    private void progress(boolean show) {
        svipeRefresh.setRefreshing(show);
    }

    private int getSpanCount() {
        return getResources().getConfiguration().orientation ==
                Configuration.ORIENTATION_LANDSCAPE ? 4 : 2;
    }
}
