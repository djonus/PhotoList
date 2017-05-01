package lt.danjon.task.ui.catalog;

import java.util.ArrayList;
import java.util.List;

import lt.danjon.task.data.Photos;
import lt.danjon.task.data.model.FlickrPhoto;
import lt.danjon.task.ui.ListLoader;
import lt.danjon.task.ui.model.Photo;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.subjects.PublishSubject;
import rx.subjects.Subject;

public class PhotosListLoader implements ListLoader<Photo> {

    private static final String URL_PATTERN = "https://farm%d.staticflickr.com/%s/%s_%s.jpg";
    private static final int PER_PAGE = 20;

    private int page = 0;

    private final Photos photos;
    private final Subject<List<Photo>, List<Photo>> subject;
    private final List<Photo> cache = new ArrayList<>();
    boolean hasMore = true;

    public PhotosListLoader(Photos photos) {
        this.photos = photos;
        this.subject = PublishSubject.create();
    }

    @Override
    public void refresh() {
        cache.clear();
        page = 0;
        load(page, PER_PAGE);
    }

    @Override
    public boolean more() {
        if (hasMore) {
            page++;
            load(page, PER_PAGE);
            return true;
        }
        return false;
    }

    @Override
    public Observable<List<Photo>> observe() {
        //list() is used to maintain scroll position when initiating RecyclerViews adapter,
        //but if maintaining scroll position is not important initial data set could be provided by
        //using concat() and emitting cache first
        return subject.asObservable();
    }

    @Override
    public List<Photo> list() {
        return new ArrayList<>(cache);
    }

    private void load(int page, int perPage) {
        hasMore = false;
        photos.load(page, perPage).observeOn(AndroidSchedulers.mainThread())
                .subscribe(photos -> {
                    hasMore = photos.list().size() >= PER_PAGE;
                    List<Photo> results = toUiData(photos.list());
                    cache.addAll(results);
                    subject.onNext(results);
                }, subject::onError);
    }

    private List<Photo> toUiData(List<FlickrPhoto> data) {
        List<Photo> photos = new ArrayList<>();

        if (data != null) for (int i = 0; i < data.size(); i++) {
            photos.add(new Photo(getFlickrPhotoUrl(data.get(i)), data.get(i).getTitle()));
        }

        return photos;
    }

    private String getFlickrPhotoUrl(FlickrPhoto photo) {
        return String.format(URL_PATTERN, photo.getFarm(), photo.getServer(), photo.getId(),
                photo.getSecret());
    }
}
