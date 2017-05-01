package lt.danjon.task.ui.catalog;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import butterknife.BindView;
import butterknife.ButterKnife;
import lt.danjon.task.R;
import lt.danjon.task.ui.model.Photo;

public class PreviewActivity extends AppCompatActivity {

    public static final String EXTRA_PHOTO = "image_url";

    @BindView(R.id.iv_preview) ImageView ivPreview;
    @BindView(R.id.tv_title) TextView tvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview);
        ButterKnife.bind(this);

        Photo photo = (Photo) getIntent().getSerializableExtra(EXTRA_PHOTO);

        // TODO: 01/05/2017 Use shared element transition
//        supportPostponeEnterTransition();
        Glide.with(this)
                .load(photo.url)
                .fitCenter()
//                .listener(new GlideListener())
                .into(ivPreview);

        tvTitle.setText(TextUtils.isEmpty(photo.title) ?
                getString(R.string.empty_title) : photo.title);

        ivPreview.setOnClickListener(v -> close());
    }

    @Override
    public void onBackPressed() {
        close();
    }

    private void close() {
        supportFinishAfterTransition();
    }

    private class GlideListener implements RequestListener<String, GlideDrawable> {
        @Override
        public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
            return false;
        }

        @Override
        public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
            supportStartPostponedEnterTransition();
            return false;
        }
    }
}
