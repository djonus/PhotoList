package lt.danjon.task.ui.catalog;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import lt.danjon.task.R;
import lt.danjon.task.ui.model.Photo;

public class PhotosAdapter extends RecyclerView.Adapter<PhotosAdapter.PhotoViewHolder> {

    public interface ItemClickListener {
        void onItemClick(View view);
    }

    private List<Photo> data;
    private ItemClickListener itemClickListener;

    public PhotosAdapter(List<Photo> data, ItemClickListener clickListener) {
        this.data = data;
        this.itemClickListener = clickListener;
    }

    @Override
    public PhotoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewGroup v = (ViewGroup) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_photo, parent, false);
        v.setOnClickListener(view -> itemClickListener.onItemClick(view));
        return new PhotoViewHolder(v);
    }

    @Override
    public void onBindViewHolder(PhotoViewHolder holder, int position) {
        holder.bind(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    public void add(List<Photo> photos) {
        if (data != null) {
            data.addAll(photos);
        } else {
            data = photos;
        }
    }

    public void clear() {
        if (data != null) data.clear();
    }

    public Photo getItem(int position) {
        return data.get(position);
    }

    public static class PhotoViewHolder extends RecyclerView.ViewHolder {

        //would use suppord data binding for more complex layouts
        @BindView(R.id.tv_title) TextView tvTitle;
        @BindView(R.id.iv_photo) ImageView ivPhoto;

        public PhotoViewHolder(ViewGroup layout) {
            super(layout);
            ButterKnife.bind(this, layout);
        }

        public void bind(Photo photo) {
            String title = TextUtils.isEmpty(photo.title) ?
                    itemView.getResources().getString(R.string.empty_title) : photo.title;
            tvTitle.setText(title);
            // TODO: 30/04/2017 Wrap image loader to be easily replaced if needed
            Glide.with(itemView.getContext())
                    .load(photo.url)
                    .centerCrop()
                    .crossFade()
                    .into(ivPhoto);
        }
    }
}
